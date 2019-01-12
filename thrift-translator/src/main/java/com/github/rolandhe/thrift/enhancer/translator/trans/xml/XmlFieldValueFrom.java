package com.github.rolandhe.thrift.enhancer.translator.trans.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.trans.json.JsonFieldValueFrom;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * xml协议实现
 *
 * @author rolandhe
 */
public class XmlFieldValueFrom extends JsonFieldValueFrom {

  private static ThreadLocal<Deque<String>> FIELD_STACK = ThreadLocal
      .withInitial(() -> new ArrayDeque<>());

  @Override
  protected void writeField(JsonGenerator container, Object fieldName) {
    String fname = null;
    if (fieldName instanceof String) {
      fname = (String) fieldName;
    } else {
      fname = fieldName.toString();
    }
    safeWriteField(container, fname);
    FIELD_STACK.get().offerFirst(fname);
  }

  private void safeWriteField(JsonGenerator container, String fname) {
    try {
      container.writeFieldName(fname);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  protected void writeFieldEnd(Object fieldName) {
    FIELD_STACK.get().pollFirst();
  }


  @Override
  protected void writeCollectionStart(JsonGenerator container, Collection collection,
      ThriftType elementType) {
    try {
      container.writeStartArray(collection.size());

      ToXmlGenerator xmlGenerator = (ToXmlGenerator) container;

      xmlGenerator.startWrappedValue(new QName(FIELD_STACK.get().peekFirst()),
          new QName(FIELD_STACK.get().peekFirst()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  protected void writeCollectionEnd(JsonGenerator container, Collection collection,
      ThriftType elementType) {
    try {
      ToXmlGenerator xmlGenerator = (ToXmlGenerator) container;
      xmlGenerator.finishWrappedValue(new QName(FIELD_STACK.get().peekFirst()),
          new QName(FIELD_STACK.get().peekFirst()));
      container.writeEndArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
