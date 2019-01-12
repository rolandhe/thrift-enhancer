package com.github.rolandhe.thrift.enhancer.translator.runtime;

import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftEncoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.coder.CompactThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.coder.CompactThriftEncoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.stream.WrappedThriftInputStream;
import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftSerializerHelper;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 动态的、二进制序列化解析thrift idl所的到{@link StructInstance} 对象的工具
 *
 * @author rolandhe
 */
public class RuntimeCompactSerializer extends RuntimeSerializer {


  private RuntimeCompactSerializer() {
  }

  /**
   * 序列化
   */
  public static byte[] encode(StructInstance structInstance) {
    ThriftEncoder thriftEncoder = new CompactThriftEncoder(RUNTIME_STRUCT_CONVERTER_ENCODER);
    StructProvider structProvider = RUNTIME_STRUCT_CONVERTER_ENCODER.convert(structInstance);
    thriftEncoder.writeStruct(structProvider);
    return thriftEncoder.toArray();
  }

  /**
   * 序列化rpc调用消息
   */
  public static byte[] encodeMessage(StructInstance structInstance,
      FunctionCallContext functionCall) {

    ThriftEncoder thriftEncoder = new CompactThriftEncoder(RUNTIME_STRUCT_CONVERTER_ENCODER);
    StructProvider structProvider = RUNTIME_STRUCT_CONVERTER_ENCODER.convert(structInstance);
    MessageProvider messageProvider = ThriftSerializerHelper
        .buildMessageProvider(functionCall, structProvider);
    thriftEncoder.writeMessage(messageProvider);
    return thriftEncoder.toArray();
  }

  /**
   * 反序列化
   *
   * @param buffer thrift序列化数据
   */
  public static StructInstance decode(StructInstance structInstance, byte[] buffer) {
    ThriftDecoder thriftDecoder = new CompactThriftDecoder(
        WrappedThriftInputStream.wrap(new ByteArrayInputStream(buffer)),
        RUNTIME_STRUCT_CONVERTER_DECODER);
    StructProvider structProvider = RUNTIME_STRUCT_CONVERTER_DECODER.convert(structInstance);
    thriftDecoder.readStruct(structProvider);
    return structInstance;
  }

  /**
   * 从InputStream中读取二进制并反序列化
   */
  public static StructInstance decode(StructInstance structInstance, InputStream inputStream) {
    ThriftDecoder thriftDecoder = new CompactThriftDecoder(
        WrappedThriftInputStream.wrap(inputStream),
        RUNTIME_STRUCT_CONVERTER_DECODER);
    StructProvider structProvider = RUNTIME_STRUCT_CONVERTER_DECODER.convert(structInstance);
    thriftDecoder.readStruct(structProvider);
    return structInstance;
  }

  /**
   * 反序列化rpc返回消息
   */
  public static StructInstance decodeMessage(StructInstance structInstance,
      FunctionCallContext functionCall,
      InputStream inputStream) {

    ThriftDecoder thriftDecoder = new CompactThriftDecoder(
        WrappedThriftInputStream.wrap(inputStream),
        RUNTIME_STRUCT_CONVERTER_DECODER);
    return decodeMessage(structInstance, functionCall, thriftDecoder);
  }

  /**
   * 反序列化rpc返回消息
   */
  public static StructInstance decodeMessage(StructInstance structInstance,
      FunctionCallContext functionCall,
      byte[] buffer) {

    ThriftDecoder thriftDecoder = new CompactThriftDecoder(
        WrappedThriftInputStream.wrap(new ByteArrayInputStream(buffer)),
        RUNTIME_STRUCT_CONVERTER_DECODER);
    return decodeMessage(structInstance, functionCall, thriftDecoder);
  }

  private static StructInstance decodeMessage(StructInstance structInstance,
      FunctionCallContext functionCall, ThriftDecoder thriftDecoder) {
    StructProvider structProvider = RUNTIME_STRUCT_CONVERTER_DECODER.convert(structInstance);

    MessageProvider messageProvider = ThriftSerializerHelper
        .buildMessageProvider(functionCall, structProvider);
    thriftDecoder.readMessage(messageProvider);
    return structInstance;
  }
}