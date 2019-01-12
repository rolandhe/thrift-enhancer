package com.github.rolandhe.thrift.enhancer.translator.runtime;

import com.github.rolandhe.thrift.enhancer.stuff.codec.CollectionProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.FieldProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MapProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.FieldRequire;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.SupportType;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructFieldValue;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 动态的、二进制序列化解析thrift idl所的到{@link StructInstance} 对象的工具
 *
 * @author rolandhe
 */
public class RuntimeSerializer {

  protected static final StructConverter<StructInstance> RUNTIME_STRUCT_CONVERTER_ENCODER = value -> createStructConverter(
      value, false);
  protected static final StructConverter<StructInstance> RUNTIME_STRUCT_CONVERTER_DECODER = value -> createStructConverter(
      value, true);


  protected static StructProvider createStructConverter(final StructInstance structInstance,
                                                        final boolean forceCreate) {

    return () -> {
      List<FieldProvider> list = new ArrayList<>(structInstance.getStructFieldValueList().size());

      for (final StructFieldValue structFieldValue : structInstance.getStructFieldValueList()) {
        list.add(new FieldProvider() {
          @Override
          public short getId() {
            return structFieldValue.getStructField().getId();
          }

          @Override
          public Object getValue() {
            return convertFieldValue(structFieldValue,
                structInstance.getThriftJavaIdl(), forceCreate);
          }

          @Override
          public byte getType() {
            return structFieldValue.getStructField().getFieldType().getType().getTypeValue();
          }

          @Override
          public String getName() {
            return structFieldValue.getStructField().getName();
          }

          @Override
          public Object createInstance() {
            ThriftJavaIdl thriftJavaIdl = structInstance.getThriftJavaIdl();
            StructDescription structDescription = thriftJavaIdl.getStructByName(
                structFieldValue.getStructField().getFieldType().getStructName());
            return StructInstance.instance(structDescription, thriftJavaIdl);
          }

          @Override
          public boolean isOptional() {
            return structFieldValue.getStructField().getFieldRequire() == FieldRequire.OPTIONAL;
          }

          @Override
          public void setValue(Object value) {
            structFieldValue.setValue(value);
          }
        });
      }
      return list;
    };
  }

  protected static Object convertFieldValue(final StructFieldValue structFieldValue,
      ThriftJavaIdl thriftJavaIdl, final boolean forceCreate) {
    byte type = structFieldValue.getStructField().getFieldType().getType().getTypeValue();
    if (type == ThriftTypeNumber.MAP) {
      return new MapProvider() {

        @Override
        public byte getKeyType() {
          return structFieldValue.getStructField().getFieldType().getContainedType().getType()
              .getTypeValue();
        }

        @Override
        public byte getValueType() {
          return structFieldValue.getStructField().getFieldType().getValueType().getType()
              .getTypeValue();
        }

        @Override
        public Object createKeyInstance() {
          return buildInstance(structFieldValue.getStructField().getFieldType().getContainedType(),
              thriftJavaIdl);
        }

        @Override
        public Object createValueInstance() {
          return buildInstance(structFieldValue.getStructField().getFieldType().getValueType(),
              thriftJavaIdl);
        }

        @Override
        public Map getMapValue() {
          if (structFieldValue.getValue() == null && forceCreate) {
            structFieldValue.setValue(createInstanceByClass(HashMap.class));
          }
          return (Map) structFieldValue.getValue();
        }
      };
    }

    if (type == ThriftTypeNumber.LIST || type == ThriftTypeNumber.SET) {
      return new CollectionProvider() {


        @Override
        public byte getElementType() {
          return structFieldValue.getStructField().getFieldType().getContainedType().getType()
              .getTypeValue();
        }

        @Override
        public Object createInstance() {
          return buildInstance(structFieldValue.getStructField().getFieldType().getContainedType(),
              thriftJavaIdl);
        }

        @Override
        public Collection getCollectionValue() {
          if (structFieldValue.getValue() == null && forceCreate) {
            structFieldValue.setValue(
                createInstanceByClass(type == ThriftTypeNumber.LIST ? ArrayList.class : HashSet.class));
          }
          return (Collection) structFieldValue.getValue();
        }
      };
    }

    return structFieldValue.getValue();
  }


  protected static Object buildInstance(ThriftType thriftType, ThriftJavaIdl thriftJavaIdl) {
    byte type = thriftType.getType().getTypeValue();
    if (thriftType.getType() == SupportType.STRUCT) {
      StructDescription structDescription = thriftJavaIdl
          .getStructByName(thriftType.getStructName());
      return StructInstance.instance(structDescription, thriftJavaIdl);
    }

    if (type == ThriftTypeNumber.SET) {
      return createInstanceByClass(HashSet.class);
    }
    if (type == ThriftTypeNumber.LIST) {
      return createInstanceByClass(ArrayList.class);
    }
    if (type == ThriftTypeNumber.MAP) {
      return createInstanceByClass(HashMap.class);
    }
    throw new RuntimeException(type + " is not complex type.");
  }

  private static Object createInstanceByClass(Class clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}