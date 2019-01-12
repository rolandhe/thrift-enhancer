package com.github.rolandhe.thrift.enhancer.translator.trans.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 封装Jackson的工具
 *
 * @author rolandhe
 */
public class JsonHelper {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final JsonFactory JSON_FACTORY = new JsonFactory();



  static {
    JSON_FACTORY.enable(JsonParser.Feature.ALLOW_COMMENTS);
    OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
  private JsonHelper(){}

  public static JsonParser parse(String doc) {
    try {
      return JSON_FACTORY.createParser(doc);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static JsonGenerator createGenerator(OutputStream outputStream){
    try {
      JsonGenerator jsonGenerator = JSON_FACTORY.createGenerator(outputStream, JsonEncoding.UTF8);
      return jsonGenerator;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public static String toJson(Object o) {
    try {
      return OBJECT_MAPPER.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
