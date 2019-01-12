package com.github.rolandhe.thrift.enhancer.translator.trans.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;
import com.github.rolandhe.thrift.enhancer.translator.trans.AbstractFieldValueFrom;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * 转换为json的实现
 *
 * @author rolandhe
 */
public class JsonFieldValueFrom extends AbstractFieldValueFrom<JsonGenerator> {

  @Override
  protected void writeStructStart(JsonGenerator container, StructInstance structInstance) {
    try {
      container.writeStartObject();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeStructEnd(JsonGenerator container, StructInstance structInstance) {
    try {
      container.writeEndObject();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeMapStart(JsonGenerator container, Map map, ThriftType keyType,
      ThriftType valueType) {
    try {
      container.writeStartObject();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeField(JsonGenerator container, Object fieldName) {
    String fname = null;
    if (fieldName instanceof String) {
      fname = (String) fieldName;
    } else {
      fname = fieldName.toString();
    }
    try {
      container.writeFieldName(fname);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeFieldEnd(Object fieldName) {

  }

  @Override
  protected void writeMapEnd(JsonGenerator container, Map map, ThriftType keyType,
      ThriftType valueType) {
    try {
      container.writeEndObject();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeCollectionStart(JsonGenerator container, Collection collection,
      ThriftType elementType) {
    try {
      container.writeStartArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  protected void writeCollectionEnd(JsonGenerator container, Collection collection,
      ThriftType elementType) {
    try {
      container.writeEndArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeByte(JsonGenerator container, Byte value) {
    try {
      container.writeNumber(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeBool(JsonGenerator container, Boolean value) {
    try {
      container.writeBoolean(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeShort(JsonGenerator container, Short value) {
    try {
      container.writeNumber(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeInt(JsonGenerator container, Integer value) {
    try {
      container.writeNumber(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeLong(JsonGenerator container, Long value) {
    try {
      container.writeNumber(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeDouble(JsonGenerator container, Double value) {
    try {
      container.writeNumber(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeString(JsonGenerator container, String value) {
    try {
      container.writeString(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeBinary(JsonGenerator container, byte[] value) {
    try {
      container.writeBinary(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void writeNull(JsonGenerator container) {
    try {
      container.writeNull();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
