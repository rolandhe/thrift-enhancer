package com.github.rolandhe.thrift.enhancer.translator.trans.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.github.rolandhe.thrift.enhancer.translator.trans.FieldValueFrom;
import com.github.rolandhe.thrift.enhancer.translator.trans.FieldValueTo;
import com.github.rolandhe.thrift.enhancer.translator.trans.Translator;
import com.github.rolandhe.thrift.enhancer.translator.trans.json.JsonTranslator;

import java.io.ByteArrayOutputStream;

/**
 * xml协议转换实现
 *
 * @author rolandhe
 */
public class XmlTranslator extends JsonTranslator implements Translator {

  private static final FieldValueTo<JsonParser> XML_PARSER_FIELD_VALUE_CONVERTER = new XmlFieldValueTo();
  private static final FieldValueFrom<JsonGenerator> XML_GENERATOR_FIELD_VALUE_FROMER = new XmlFieldValueFrom();

  @Override
  protected FieldValueTo<JsonParser> getFieldValueConverter() {
    return XML_PARSER_FIELD_VALUE_CONVERTER;
  }

  @Override
  protected FieldValueFrom<JsonGenerator> getFieldValueFromer() {
    return XML_GENERATOR_FIELD_VALUE_FROMER;
  }

  @Override
  protected JsonParser forJsonParser(String doc) {
    return XmlHelper.parse(doc);
  }

  @Override
  protected JsonGenerator forJsonGenerator(ByteArrayOutputStream outputStream, String structName) {
    return XmlHelper.createGenerator(outputStream, structName);
  }
}
