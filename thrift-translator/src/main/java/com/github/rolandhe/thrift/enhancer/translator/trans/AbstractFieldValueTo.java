package com.github.rolandhe.thrift.enhancer.translator.trans;


import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.EnumDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * FieldValueTo 抽象实现
 *
 * @author rolandhe
 */
public abstract class AbstractFieldValueTo<T> implements
        FieldValueTo<T> {

    @Override
    public Object to(T rawValue, ThriftType thriftType, ThriftJavaIdl thriftJavaIdl) {
        byte type = thriftType.getType().getTypeValue();

        if (type == ThriftTypeNumber.BYTE || type == ThriftTypeNumber.I8) {
            return convertByte(rawValue);
        }
        if (type == ThriftTypeNumber.BOOL) {
            return convertBool(rawValue);
        }

        if (type == ThriftTypeNumber.I16) {
            return convertShort(rawValue);
        }

        if (type == ThriftTypeNumber.I32) {
            return convertInt(rawValue);
        }

        if (type == ThriftTypeNumber.I64) {
            return convertLong(rawValue);
        }

        if (type == ThriftTypeNumber.DOUBLE) {
            return convertDouble(rawValue);
        }

        if (type == ThriftTypeNumber.STRING) {
            return convertString(rawValue);
        }

        if (type == ThriftTypeNumber.BINARY) {
            return convertBinary(rawValue);
        }

        if (type == ThriftTypeNumber.LIST) {
            List list = new ArrayList();
            fillCollection(list, rawValue, thriftType.getContainedType(), thriftJavaIdl);
            return list;
        }

        if (type == ThriftTypeNumber.SET) {
            Set set = new HashSet();
            fillCollection(set, rawValue, thriftType.getContainedType(), thriftJavaIdl);
            return set;
        }


        if (type == ThriftTypeNumber.MAP) {
            Map map = new HashMap();
            fillMap(map, rawValue, thriftType.getContainedType(), thriftType.getValueType(), thriftJavaIdl);
            return map;
        }

        if (type == ThriftTypeNumber.ENUM) {
            return convertEnum(rawValue, thriftJavaIdl.getEnumByName(thriftType.getStructName()));
        }

        if (type == ThriftTypeNumber.STRUCT) {
            return to(rawValue, thriftJavaIdl, thriftType.getStructName(), false);
        }


        return new RuntimeException("don't support type of " + type);
    }

    protected abstract Object convertByte(T rawValue);

    protected abstract Object convertBool(T rawValue);

    protected abstract Object convertShort(T rawValue);

    protected abstract Object convertInt(T rawValue);

    protected abstract Object convertLong(T rawValue);

    protected abstract Object convertDouble(T rawValue);

    protected abstract Object convertString(T rawValue);

    protected abstract Object convertBinary(T rawValue);

    protected abstract int convertEnum(T rawValue, EnumDescription enumDescription);

    protected abstract void fillCollection(Collection collection, T rawValue, ThriftType elementType, ThriftJavaIdl thriftJavaIdl);

    protected abstract void fillMap(Map map, T rawValue, ThriftType keyType, ThriftType valueType, ThriftJavaIdl thriftJavaIdl);
}
