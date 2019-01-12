package com.github.rolandhe.thrift.enhancer.translator.trans.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.EnumDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructFieldValue;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;
import com.github.rolandhe.thrift.enhancer.translator.trans.AbstractFieldValueTo;
import com.github.rolandhe.thrift.enhancer.translator.trans.ThriftDynamicUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 转换为json的实现
 *
 * @author rolandhe
 */
public class JsonFieldValueTo extends
        AbstractFieldValueTo<JsonParser> {

  @Override
  protected Object convertByte(JsonParser rawValue) {
    try {
      if (rawValue.getCurrentToken() == JsonToken.VALUE_STRING) {
        return Byte.parseByte(rawValue.getText());
      }
      return rawValue.getByteValue();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertBool(JsonParser rawValue) {
    try {
      if (rawValue.getCurrentToken() == JsonToken.VALUE_STRING) {
        return "true".equals(rawValue.getText());
      }
      return rawValue.getBooleanValue();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertShort(JsonParser rawValue) {
    try {
      if (rawValue.getCurrentToken() == JsonToken.VALUE_STRING) {
        return Short.parseShort(rawValue.getText());
      }
      return rawValue.getShortValue();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertInt(JsonParser rawValue) {
    try {
      if (rawValue.getCurrentToken() == JsonToken.VALUE_STRING) {
        return Integer.parseInt(rawValue.getText());
      }
      return rawValue.getIntValue();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertLong(JsonParser rawValue) {
    try {
      if (rawValue.getCurrentToken() == JsonToken.VALUE_STRING) {
        return Long.parseLong(rawValue.getText());
      }
      return rawValue.getLongValue();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertDouble(JsonParser rawValue) {
    try {
      if (rawValue.getCurrentToken() == JsonToken.VALUE_STRING) {
        return Double.parseDouble(rawValue.getText());
      }
      return rawValue.getDoubleValue();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertString(JsonParser rawValue) {
    try {
      return rawValue.getText();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertBinary(JsonParser rawValue) {
    try {
      return rawValue.getBinaryValue();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected int convertEnum(JsonParser rawValue, EnumDescription enumDescription) {
    try {
      String text = rawValue.getText();
      if (StringUtils.isNumeric(text)) {
        return Integer.parseInt(text);
      }
      return enumDescription.getIndexByName(text);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  protected void fillCollection(Collection collection, JsonParser jsonParser,
      ThriftType elementType,
      ThriftJavaIdl thriftJavaIdl) {

    JsonToken curToken = jsonParser.currentToken();
    checkArray(curToken);
    JsonToken nt = null;
    while ((nt = nextToken(jsonParser)) != JsonToken.END_ARRAY) {
      if (elementType.getType().getTypeValue() == ThriftTypeNumber.STRUCT) {
        checkObject(nt);
      }

      Object element = to(jsonParser, elementType, thriftJavaIdl);
      collection.add(element);
    }
  }

  @Override
  protected void fillMap(Map map, JsonParser jsonParser, ThriftType keyType, ThriftType valueType,
      ThriftJavaIdl thriftJavaIdl) {
    JsonToken nt = jsonParser.currentToken();
    checkObject(nt);
    while ((nt = nextToken(jsonParser)) != JsonToken.END_OBJECT) {
      Object key = null;
      if (ThriftTypeNumber.isNumber(keyType.getType().getTypeValue())) {
        String stringKeyValue = (String) to(jsonParser, ThriftType.STRING_TYPE, thriftJavaIdl);
        key = ThriftTypeNumber.adapt(keyType.getType().getTypeValue(), stringKeyValue);
      } else {
        key = to(jsonParser, keyType, thriftJavaIdl);
      }
      nt = nextToken(jsonParser);
      Object value = to(jsonParser, valueType, thriftJavaIdl);
      map.put(key, value);
    }
  }


  @Override
  public StructInstance to(JsonParser jsonParser, ThriftJavaIdl thriftJavaIdl, String targetName,
                           boolean isFunction) {

    JsonToken nt = jsonParser.currentToken() == JsonToken.START_OBJECT ? jsonParser.currentToken()
        : nextToken(jsonParser);
    checkObject(nt);

    StructInstance structInstance = ThriftDynamicUtil
        .createStructInstance(thriftJavaIdl, targetName, isFunction);
    Map<String, StructFieldValue> fieldValueMap = structInstance.toFieldValueMap();

    while ((nt = nextToken(jsonParser)) != JsonToken.END_OBJECT) {
      checkField(nt);
      String fieldName = safeFieldName(jsonParser);

      JsonToken valueToken = nextToken(jsonParser);

      StructFieldValue structFieldValue = fieldValueMap.get(fieldName);
      if (structFieldValue == null) {
        continue;
      }
      if (valueToken == JsonToken.VALUE_NULL) {
        byte type = structFieldValue.getStructField().getFieldType().getType().getTypeValue();
        if (type == ThriftTypeNumber.MAP) {
          structFieldValue.setValue(new HashMap());
        }
        if (type == ThriftTypeNumber.SET) {
          structFieldValue.setValue(new HashSet());
        }
        if (type == ThriftTypeNumber.LIST) {
          structFieldValue.setValue(new ArrayList());
        }
        continue;
      }

      structFieldValue.setValue(
          to(jsonParser, structFieldValue.getStructField().getFieldType(), structInstance.getThriftJavaIdl()));
    }

    for (StructFieldValue structFieldValue : fieldValueMap.values()) {
      if (!structFieldValue.checkRequired()) {
        throw new RuntimeException(structFieldValue.getStructField().getName() + " is required.");
      }
    }

    return structInstance;
  }


  protected void checkObject(JsonToken nt) {
    if (nt != JsonToken.START_OBJECT) {
      throw new RuntimeException(nt.name() + " is not object.");
    }
  }

  protected void checkField(JsonToken nt) {
    if (nt != JsonToken.FIELD_NAME) {
      throw new RuntimeException(nt.name() + " is not object.");
    }
  }

  protected void checkArray(JsonToken nt) {
    if (nt != JsonToken.START_ARRAY) {
      throw new RuntimeException(nt.name() + " is not array.");
    }
  }

  protected String safeFieldName(JsonParser jsonParser) {
    try {
      return jsonParser.getCurrentName();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected JsonToken nextToken(JsonParser jsonParser) {
    try {
      return jsonParser.nextToken();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
