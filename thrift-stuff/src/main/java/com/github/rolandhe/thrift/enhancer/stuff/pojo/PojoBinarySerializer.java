package com.github.rolandhe.thrift.enhancer.stuff.pojo;


import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftEncoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.coder.BinaryThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.coder.BinaryThriftEncoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.stream.WrappedThriftInputStream;
import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftSerializerHelper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Pojo序列化、反序列化thrift binary协议的工具
 *
 * @author rolandhe
 */
public class PojoBinarySerializer extends PojoSerializer {


  private PojoBinarySerializer() {
  }


  /**
   * 序列化pojo
   */
  public static <T> byte[] encode(T target) {
    ThriftEncoder thriftEncoder = new BinaryThriftEncoder(STRUCT_CONVERTER_ENCODER);
    StructProvider structProvider = STRUCT_CONVERTER_ENCODER.convert(target);
    thriftEncoder.writeStruct(structProvider);
    return thriftEncoder.toArray();
  }


  /**
   * 序列化pojo为rpc调用消息
   */
  public static <T> byte[] encodeMessage(T target, FunctionCallContext functionCall) {
    ThriftEncoder thriftEncoder = new BinaryThriftEncoder(STRUCT_CONVERTER_ENCODER);
    final StructProvider structProvider = STRUCT_CONVERTER_ENCODER.convert(target);
    MessageProvider messageProvider = ThriftSerializerHelper.buildMessageProvider(functionCall,
        structProvider);
    thriftEncoder.writeMessage(messageProvider);
    return thriftEncoder.toArray();
  }


  /**
   * 从byte数组 反序列化pojo
   *
   * @param buffer thrift binary 数组
   */
  public static <T> T decode(Class<T> tClass, byte[] buffer) {
    ThriftDecoder thriftDecoder = new BinaryThriftDecoder(
        WrappedThriftInputStream.wrap(new ByteArrayInputStream(buffer)),
        STRUCT_CONVERTER_DECODER);
    return decode(tClass, thriftDecoder);
  }

  /**
   * 从流读取二进制数据并反序列化pojo
   */
  public static <T> T decode(Class<T> tClass, InputStream inputStream) {
    ThriftDecoder thriftDecoder = new BinaryThriftDecoder(
        WrappedThriftInputStream.wrap(inputStream),
        STRUCT_CONVERTER_DECODER);

    return decode(tClass, thriftDecoder);
  }

  /**
   * 从流中读取二进制数据反序列化rpc返回结果消息为pojo
   */
  public static <T> T decodeMessage(Class<T> tClass, FunctionCallContext functionCall,
      InputStream inputStream) {
    ThriftDecoder thriftDecoder = new BinaryThriftDecoder(
        WrappedThriftInputStream.wrap(inputStream),
        STRUCT_CONVERTER_DECODER);

    return decodeMessage(tClass, functionCall, thriftDecoder);
  }

  /**
   * 反序列化rpc返回的二进制结果消息为pojo
   */
  public static <T> T decodeMessage(Class<T> tClass, FunctionCallContext functionCall,
      byte[] buffer) {
    ThriftDecoder thriftDecoder = new BinaryThriftDecoder(
        WrappedThriftInputStream.wrap(new ByteArrayInputStream(buffer)),
        STRUCT_CONVERTER_DECODER);

    return decodeMessage(tClass, functionCall, thriftDecoder);
  }

}
