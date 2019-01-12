package com.github.rolandhe.thrift.enhancer.translator.trans;


import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.EnumDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructFieldValue;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * FieldValueFrom抽象实现
 *
 * @author rolandhe
 */
public abstract class AbstractFieldValueFrom<T> implements FieldValueFrom<T> {

  @Override
  public void from(T container, StructInstance structInstance,
      boolean enumIndex) {
    if (structInstance.getStructFieldValueList() == null
        || structInstance.getStructFieldValueList().size() == 0) {
      return;
    }
    writeStructStart(container, structInstance);
    for (StructFieldValue fieldValue : structInstance.getStructFieldValueList()) {
      writeField(container,fieldValue.getStructField().getName());
      from(container,fieldValue.getStructField().getFieldType(),fieldValue.getValue(),structInstance.getThriftJavaIdl(),enumIndex);
      writeFieldEnd(fieldValue.getStructField().getName());
    }
    writeStructEnd(container, structInstance);
  }

  private void fromMap(T container, Map map, ThriftType keyType, ThriftType valueType,
                       ThriftJavaIdl thriftJavaIdl, boolean enumIndex) {
    if(map == null){
      writeNull(container);
    }
    writeMapStart(container,map,keyType,valueType);
      map.forEach((key, value) -> {
        writeField(container,  key);
        from(container, valueType, value, thriftJavaIdl, enumIndex);
        writeFieldEnd(key);
      });
    writeMapEnd(container,map,keyType,valueType);
  }

  private void fromCollection(T container, Collection collection, ThriftType elementType,
      ThriftJavaIdl thriftJavaIdl, boolean enumIndex) {
    if(collection == null) {
      writeNull(container);
    }
    writeCollectionStart(container,collection,elementType);
      collection.forEach(value -> {
        from(container, elementType, value, thriftJavaIdl, enumIndex);
      });
    writeCollectionEnd(container,collection,elementType);
  }


  @Override
  public void   from(T container, ThriftType thriftType ,Object rawValue,
      ThriftJavaIdl thriftJavaIdl,boolean enumIndex) {
    if(rawValue == null) {
      writeNull(container);
      return;
    }
    byte type = thriftType.getType().getTypeValue();

    if (type == ThriftTypeNumber.BYTE || type == ThriftTypeNumber.I8) {
      writeByte(container, (Byte) rawValue);
      return;
    }
    if (type == ThriftTypeNumber.BOOL) {
      writeBool(container, (Boolean) rawValue);
      return;
    }

    if (type == ThriftTypeNumber.I16) {
      writeShort(container, (Short) rawValue);
      return;
    }

    if (type == ThriftTypeNumber.I32) {
      writeInt(container, (Integer) rawValue);
      return;
    }

    if (type == ThriftTypeNumber.I64) {
      writeLong(container, (Long) rawValue);
      return;
    }

    if (type == ThriftTypeNumber.DOUBLE) {
      writeDouble(container, (Double) rawValue);
      return;
    }

    if (type == ThriftTypeNumber.STRING) {
      writeString(container, (String) rawValue);
      return;
    }

    if (type == ThriftTypeNumber.BINARY) {
      writeBinary(container, (byte[]) rawValue);
      return;
    }

    if (type == ThriftTypeNumber.LIST) {
      List list = (List)rawValue;
      fromCollection(container,list,thriftType.getContainedType(),thriftJavaIdl,enumIndex);
      return;
    }

    if (type == ThriftTypeNumber.SET) {
      Set set = (Set)rawValue;
      fromCollection(container,set,thriftType.getContainedType(),thriftJavaIdl,enumIndex);
      return;
    }

    if (type == ThriftTypeNumber.MAP) {
      Map map =(Map)rawValue;
      fromMap(container, map, thriftType.getContainedType(), thriftType.getValueType(),
          thriftJavaIdl,enumIndex);
      return;
    }

    if (type == ThriftTypeNumber.ENUM) {
      if(rawValue == null){
        writeNull(container);
        return;
      }
      if(enumIndex) {
        writeInt(container,(Integer)rawValue);
        return;
      }
      EnumDescription enumDescription = thriftJavaIdl.getEnumByName(thriftType.getStructName());
      writeString(container,enumDescription.getNameByIndex((Integer)rawValue));
      return;
    }

    if (type == ThriftTypeNumber.STRUCT) {
      StructInstance structInstance =(StructInstance)rawValue;
      from(container,structInstance,enumIndex);
      return;
    }
  }

  protected abstract void writeStructStart(T container, StructInstance structInstance);

  protected abstract void writeStructEnd(T container, StructInstance structInstance);

  protected abstract void writeMapStart(T container, Map map, ThriftType keyType,
      ThriftType valueType);
  protected abstract void writeField(T container, Object fieldName);

  protected abstract void writeFieldEnd(Object fieldName);

  protected abstract void writeMapEnd(T container, Map map, ThriftType keyType,
      ThriftType valueType);

  protected abstract void writeCollectionStart(T container, Collection collection,
      ThriftType elementType);

  protected abstract void writeCollectionEnd(T container, Collection collection,
      ThriftType elementType);


  protected abstract void writeByte(T container, Byte value);

  protected abstract void writeBool(T container, Boolean value);
  protected abstract void writeShort(T container, Short value);
  protected abstract void writeInt(T container, Integer value);
  protected abstract void writeLong(T container, Long value);
  protected abstract void writeDouble(T container, Double value);
  protected abstract void writeString(T container, String value);
  protected abstract void writeBinary(T container, byte[] value);
  protected abstract void writeNull(T container);

}
