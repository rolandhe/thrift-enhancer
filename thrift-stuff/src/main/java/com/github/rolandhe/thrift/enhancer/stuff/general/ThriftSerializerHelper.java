package com.github.rolandhe.thrift.enhancer.stuff.general;

import com.github.rolandhe.thrift.enhancer.stuff.codec.CollectionProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.FieldProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MapProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.Optional;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.SetContainer;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.ListContainer;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.MapContainer;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * thrift序列化工具，支持:<br>
 *   <ul>
 *     <li>转换pojo为StructProvider</li>
 *     <li>构建MessageProvider</li>
 *   </ul>
 *
 * @author rolandhe
 */
public class ThriftSerializerHelper {

  private static final StructProvider EMPTY_STRUCT_PROVIDER = () -> Collections.emptyList();

  private ThriftSerializerHelper() {
  }

  /**
   * 转换pojo为StructProvider
   *
   * @param target
   * @param forceCreateContainer 是否需要为Map,List,Set类型的、null的field实例化，在反序列化时需要构建
   * @return
   */
  public static StructProvider buildStructProviderForPojo(Object target,
      final boolean forceCreateContainer) {
    Class tClass = target.getClass();

    Field[] allFields = FieldUtils.getAllFields(tClass);
    if (allFields == null || allFields.length == 0) {
      return EMPTY_STRUCT_PROVIDER;
    }

    final List<FieldProvider> fieldProviderList = new ArrayList<>(allFields.length);
    short count = 0;
    for (final Field field : allFields) {
      if (Modifier.isTransient(field.getModifiers())) {
        continue;
      }
      final byte type = recognizeType(field.getType());
      if (!validContainer(field, type)) {
        continue;
      }
      field.setAccessible(true);
      final short seqId = ++count;
      fieldProviderList.add(createFieldProvider(target, forceCreateContainer, field, type, seqId));
    }

    if (fieldProviderList.size() == 0) {
      return EMPTY_STRUCT_PROVIDER;
    }

    StructProvider structProvider = () -> fieldProviderList;
    return structProvider;
  }

  /**
   * 构建thrift函数调用消息
   *
   * @param functionCall
   * @param structProvider
   * @return
   */
  public static MessageProvider buildMessageProvider(final FunctionCallContext functionCall,
                                                     StructProvider structProvider) {
    return new MessageProvider() {


      @Override
      public StructProvider getStructProvider() {
        return structProvider;
      }

      @Override
      public FunctionCallContext getFunctionCallContext() {
        return functionCall;
      }
    };
  }

  private static FieldProvider createFieldProvider(Object target, boolean forceCreateContainer,
      Field field, byte type, short seqId) {
    return new FieldProvider() {
      @Override
      public short getId() {
        return seqId;
      }

      @Override
      public Object getValue() {
        return convertFieldValue(type, field, target, forceCreateContainer);
      }

      @Override
      public byte getType() {
        return type;
      }

      @Override
      public String getName() {
        return field.getName();
      }

      @Override
      public Object createInstance() {
        try {
          return field.getType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public boolean isOptional() {
        Optional optional = field.getAnnotation(Optional.class);
        return optional != null;
      }


      private Object translateEnum(Integer ordinal) {
        if (ordinal == null) {
          return null;
        }
        Enum[] enums = (Enum[]) field.getType().getEnumConstants();
        for (Enum v : enums) {
          if (v.ordinal() == ordinal) {
            return v;
          }
        }
        throw new RuntimeException(
            "invalid enum ordinal " + ordinal + " in " + field.getType().getName());
      }

      @Override
      public void setValue(Object value) {
        try {
          if (type == ThriftTypeNumber.ENUM) {
            value = translateEnum((Integer) value);
          }
          FieldUtils.writeField(field, target, value, true);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

  private static Object convertFieldValue(byte type, Field field, Object target,
      boolean forceCreateContainer) {
    field.setAccessible(true);
    final Object[] value = {null};
    try {
      value[0] = field.get(target);
    } catch (IllegalAccessException e) {
      throw new ThriftExp(e);
    }
    if (type == ThriftTypeNumber.MAP) {
      final MapContainer mapContainer = field.getAnnotation(MapContainer.class);
      byte keyType = recognizeType(mapContainer.keyClass());
      byte valueType = recognizeType(mapContainer.valueClass());
      validContaineredType(keyType);
      validContaineredType(valueType);
      createMap(field, target, forceCreateContainer, value, mapContainer.realMapClass());
      return createMapProvider(value[0], mapContainer, keyType, valueType);
    }
    if (type == ThriftTypeNumber.LIST || type == ThriftTypeNumber.SET) {
      byte elementType;
      Class<?> realCollectionClass;
      Class<?> elementClass;
      if (type == ThriftTypeNumber.SET) {
        SetContainer setContainer = field.getAnnotation(SetContainer.class);
        elementClass = setContainer.value();
        elementType = recognizeType(elementClass);
        realCollectionClass = setContainer.realSetClass();
      } else {
        ListContainer listContainer = field.getAnnotation(ListContainer.class);
        elementClass = listContainer.value();
        elementType = recognizeType(elementClass);
        realCollectionClass = listContainer.realListClass();
      }
      validContaineredType(elementType);
      createCollection(type, field, target, forceCreateContainer, value, realCollectionClass);
      return createListOrSetProvider(value[0], elementType, elementClass);
    }
    if (type == ThriftTypeNumber.ENUM) {
      Enum enumValue = (Enum) value[0];
      return enumValue.ordinal();
    }
    return value[0];
  }

  private static CollectionProvider createListOrSetProvider(Object o, byte elementType,
                                                            Class<?> elementClass) {
    return new CollectionProvider() {
      @Override
      public byte getElementType() {
        return elementType;
      }

      @Override
      public Object createInstance() {
        try {
          return elementClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public Collection getCollectionValue() {
        return (Collection) o;
      }
    };
  }

  private static MapProvider createMapProvider(Object o, MapContainer mapContainer, byte keyType,
                                               byte valueType) {
    return new MapProvider() {
      @Override
      public byte getKeyType() {
        return keyType;
      }

      @Override
      public byte getValueType() {
        return valueType;
      }


      @Override
      public Object createKeyInstance() {
        try {
          return mapContainer.keyClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public Object createValueInstance() {
        try {
          return mapContainer.valueClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public Map getMapValue() {
        return (Map) o;
      }
    };
  }


  private static boolean validContainer(Field field, byte type) {
    if (type == ThriftTypeNumber.MAP) {
      MapContainer mapContainer = field.getAnnotation(MapContainer.class);
      if (mapContainer == null) {
        return false;
      }
    }
    if (type == ThriftTypeNumber.LIST) {
      ListContainer listContainer = field.getAnnotation(ListContainer.class);
      if (listContainer == null) {
        return false;
      }
    }
    if (type == ThriftTypeNumber.SET) {
      SetContainer setContainer = field.getAnnotation(SetContainer.class);
      if (setContainer == null) {
        return false;
      }
    }
    return true;
  }


  private static void createMap(Field field, Object target, boolean forceCreateContainer,
      Object[] value, Class<?> mapClass) {
    if (value[0] != null || !forceCreateContainer) {
      return;
    }
    try {
      value[0] = mapClass.newInstance();
      FieldUtils.writeField(field, target, value[0], true);
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private static void createCollection(byte type, Field field, Object target,
      boolean forceCreateContainer,
      Object[] value, Class<?> realConatinerClass) {

    if (value[0] != null || !forceCreateContainer) {
      return;
    }
    try {
      if (type == ThriftTypeNumber.LIST) {
        value[0] = realConatinerClass.newInstance();
        FieldUtils.writeField(field, target, value[0], true);
      }
      if (type == ThriftTypeNumber.SET) {
        value[0] = realConatinerClass.newInstance();
        FieldUtils.writeField(field, target, value[0], true);
      }
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }


  private static void validContaineredType(byte keyType) {
    if (keyType == ThriftTypeNumber.MAP
        || keyType == ThriftTypeNumber.LIST
        || keyType == ThriftTypeNumber.SET) {
      throw new RuntimeException("don't support containers type in container.");
    }
  }


  private static byte recognizeType(Class clazz) {
    if (isNotSupport(clazz)) {
      throw new RuntimeException("thrift don't support this type:" + clazz.getName());
    }
    if (clazz == byte[].class) {
      return ThriftTypeNumber.BINARY;
    }
    if (clazz == Byte.class || clazz == byte.class) {
      return ThriftTypeNumber.BYTE;
    }
    if (clazz == Short.class || clazz == short.class) {
      return ThriftTypeNumber.I16;
    }

    if (clazz == Integer.class || clazz == int.class) {
      return ThriftTypeNumber.I32;
    }

    if (clazz == Long.class || clazz == long.class) {
      return ThriftTypeNumber.I64;
    }

    if (clazz == Double.class || clazz == double.class) {
      return ThriftTypeNumber.DOUBLE;
    }

    if (clazz == byte[].class) {
      return ThriftTypeNumber.STRING;
    }

    if (Enum.class.isAssignableFrom(clazz)) {
      return ThriftTypeNumber.ENUM;
    }

    if (clazz == String.class) {
      return ThriftTypeNumber.STRING;
    }

    if (clazz == Boolean.class || clazz == boolean.class) {
      return ThriftTypeNumber.BOOL;
    }

    if (clazz == Map.class) {
      return ThriftTypeNumber.MAP;
    }

    if (clazz == List.class) {
      return ThriftTypeNumber.LIST;
    }

    if (clazz == Set.class) {
      return ThriftTypeNumber.SET;
    }

    return ThriftTypeNumber.STRUCT;
  }

  private static boolean isNotSupport(Class clazz) {
    if (clazz == byte[].class) {
      return false;
    }
    return clazz.getName().startsWith("[")
        || clazz == float.class
        || clazz == Float.class
        || clazz == char.class
        || clazz == Character.class;
  }

}
