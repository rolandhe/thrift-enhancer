package com.github.rolandhe.thrift.enhancer.translator.trans.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.Function;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.runtime.RuntimeCompactSerializer;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;
import com.github.rolandhe.thrift.enhancer.translator.trans.FieldValueFrom;
import com.github.rolandhe.thrift.enhancer.translator.trans.FieldValueTo;
import com.github.rolandhe.thrift.enhancer.translator.trans.ThriftDynamicUtil;
import com.github.rolandhe.thrift.enhancer.translator.trans.Translator;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * json协议转换实现
 *
 * @author rolandhe
 */
public class JsonCompactTranslator implements Translator {

  private static final FieldValueTo<JsonParser> JSON_PARSER_FIELD_VALUE_CONVERTER = new JsonFieldValueTo();
  private static final FieldValueFrom<JsonGenerator> JSON_GENERATOR_FIELD_VALUE_FROMER = new JsonFieldValueFrom();
  public static final int JSON_OUT_BUFFER_SIZE = 1024;

  protected FieldValueTo<JsonParser> getFieldValueConverter() {
    return JSON_PARSER_FIELD_VALUE_CONVERTER;
  }

  protected FieldValueFrom<JsonGenerator> getFieldValueFromer() {
    return JSON_GENERATOR_FIELD_VALUE_FROMER;
  }

  @Override
  public byte[] translateRequest(String doc, ThriftJavaIdl thriftJavaIdl, String targetName,
                                 boolean isFunction) {
    JsonParser jsonParser = forJsonParser(doc);

    StructInstance structInstance = getFieldValueConverter()
        .to(jsonParser, thriftJavaIdl, targetName, isFunction);

    return RuntimeCompactSerializer.encode(structInstance);
  }


  @Override
  public byte[] translateRequestMessage(String doc, ThriftJavaIdl thriftJavaIdl, String targetName,
      boolean isFunction, FunctionCallContext functionCall) {
    JsonParser jsonParser = forJsonParser(doc);

    StructInstance structInstance = getFieldValueConverter()
        .to(jsonParser, thriftJavaIdl, targetName, isFunction);
    return RuntimeCompactSerializer.encodeMessage(structInstance, functionCall);
  }

  @Override
  public String translateResponse(byte[] thriftData, ThriftJavaIdl thriftJavaIdl,
      String functionName, boolean enumIndex) {

    Function function = ThriftDynamicUtil.getFunctionByName(thriftJavaIdl, functionName);
    ThriftType returnType = function.getReturnType();

    StructInstance structInstance = StructInstance.instanceFunctionReturn(function, thriftJavaIdl);
    if (structInstance == null) {
      return "";
    }
    Object jsonObj = RuntimeCompactSerializer.decode(structInstance, thriftData);
    if (returnType.getType().getTypeValue() != ThriftTypeNumber.STRUCT) {
      jsonObj = structInstance.getStructFieldValueList().get(0).getValue();
    }

    return outputJsonString(thriftJavaIdl, enumIndex, returnType, jsonObj, functionName);
  }

  @Override
  public String translateResponseMessage(InputStream inputStream, ThriftJavaIdl thriftJavaIdl,
      String functionName, boolean enumIndex, FunctionCallContext functionCall) {
    Function function = ThriftDynamicUtil.getFunctionByName(thriftJavaIdl, functionName);
    ThriftType returnType = function.getReturnType();

    StructInstance structInstance = StructInstance.instanceFunctionReturn(function, thriftJavaIdl);
    if (structInstance == null) {
      return "";
    }
    RuntimeCompactSerializer
        .decodeMessage(structInstance, functionCall, inputStream);
    Object jsonObj = structInstance.getStructFieldValueList().get(0).getValue();
    return outputJsonString(thriftJavaIdl, enumIndex, returnType, jsonObj, functionName);
  }

  private String outputJsonString(ThriftJavaIdl thriftJavaIdl, boolean enumIndex,
      ThriftType returnType, Object jsonObj, String functionName) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(JSON_OUT_BUFFER_SIZE);
    JsonGenerator jsonGenerator = forJsonGenerator(outputStream,
        genStructName(returnType, functionName));
    try {
      getFieldValueFromer()
          .from(jsonGenerator, returnType, jsonObj, thriftJavaIdl, enumIndex);
      jsonGenerator.flush();
      return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        jsonGenerator.close();
      } catch (IOException e) {
        // ignore
      }
    }
  }

  private String genStructName(ThriftType thriftType, String functionName) {
    if (thriftType.getType().getTypeValue() == ThriftTypeNumber.STRUCT) {
      return thriftType.getStructName();
    }
    String[] arr = StringUtils.split(functionName, '.');
    return arr[arr.length - 1] + "_result";
  }

  protected JsonParser forJsonParser(String doc) {
    return JsonHelper.parse(doc);
  }

  protected JsonGenerator forJsonGenerator(ByteArrayOutputStream outputStream, String structName) {
    return JsonHelper.createGenerator(outputStream);
  }

  @Override
  public String translateResponseStruct(byte[] thriftData, ThriftJavaIdl thriftJavaIdl,
      String structName,
      boolean enumIndex) {
    StructInstance structInstance = ThriftDynamicUtil.createStructInstance(thriftJavaIdl,
        structName, false);
    RuntimeCompactSerializer.decode(structInstance, thriftData);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(JSON_OUT_BUFFER_SIZE);
    JsonGenerator jsonGenerator = forJsonGenerator(outputStream, structName);

    try {
      getFieldValueFromer()
          .from(jsonGenerator, structInstance, enumIndex);
      jsonGenerator.flush();
      return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        jsonGenerator.close();
      } catch (IOException e) {
        // ignore
      }
    }

  }


}
