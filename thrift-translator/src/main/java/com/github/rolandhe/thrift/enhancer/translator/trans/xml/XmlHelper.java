package com.github.rolandhe.thrift.enhancer.translator.trans.xml;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser.Feature;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 封装jackson xml的实现
 *
 * @author rolandhe
 */
public class XmlHelper {

  private static final XmlFactory XML_FACTORY = new XmlFactory();
  private static final XmlMapper XML_MAPPER = new XmlMapper();

  static {
    XML_FACTORY.enable(Feature.EMPTY_ELEMENT_AS_NULL);
    XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    XML_MAPPER.setDefaultPrettyPrinter(new DefaultXmlPrettyPrinter());
  }

  public static FromXmlParser parse(String xml) {
    try {
      FromXmlParser xmlParser = (FromXmlParser) XML_FACTORY.createParser(xml);
      return xmlParser;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static ToXmlGenerator createGenerator(OutputStream outputStream, String rootName) {
    try {
      ToXmlGenerator xmlGenerator = XML_FACTORY.createGenerator(outputStream,
          JsonEncoding.UTF8);
      xmlGenerator.initGenerator();
      xmlGenerator.setNextName(new QName(null, rootName));
      return xmlGenerator;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String toXml(Object o) {
    try {
      return XML_MAPPER.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
