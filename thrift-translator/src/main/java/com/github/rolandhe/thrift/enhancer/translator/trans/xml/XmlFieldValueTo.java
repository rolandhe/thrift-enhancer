package com.github.rolandhe.thrift.enhancer.translator.trans.xml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.trans.json.JsonFieldValueTo;

import java.io.IOException;
import java.util.Collection;

/**
 * xml协议实现
 *
 * @author rolandhe
 */
public class XmlFieldValueTo extends
        JsonFieldValueTo {

  @Override
  protected Object convertByte(JsonParser rawValue) {
    try {
      return Byte.parseByte(rawValue.getText());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertBool(JsonParser rawValue) {
    try {
      return "true".equals(rawValue.getText());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertShort(JsonParser rawValue) {
    try {
      return Short.parseShort(rawValue.getText());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertInt(JsonParser rawValue) {
    try {
      return Integer.parseInt(rawValue.getText());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertLong(JsonParser rawValue) {
    try {
      return Long.parseLong(rawValue.getText());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Object convertDouble(JsonParser rawValue) {
    try {
      return Double.parseDouble(rawValue.getText());
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
  protected void fillCollection(Collection collection, JsonParser jsonParser,
      ThriftType elementType,
      ThriftJavaIdl thriftJavaIdl) {

    JsonToken nt;

    while ((nt = nextToken(jsonParser)) != JsonToken.END_OBJECT) {
      checkField(nt);
      nt = nextToken(jsonParser);
      if (elementType.getType().getTypeValue() == ThriftTypeNumber.STRUCT) {
        checkObject(nt);
      }

      Object element = to(jsonParser, elementType, thriftJavaIdl);
      collection.add(element);
    }
  }
}
