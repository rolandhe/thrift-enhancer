package com.github.rolandhe.thrift.enhancer.stuff.pojo;


import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftSerializerHelper;

/**
 * @author rolandhe
 */
class PojoSerializer {
  protected static final StructConverter<Object> STRUCT_CONVERTER_ENCODER = value -> ThriftSerializerHelper
      .buildStructProviderForPojo(value, false);

  protected static final StructConverter<Object> STRUCT_CONVERTER_DECODER = value -> ThriftSerializerHelper
      .buildStructProviderForPojo(value, true);

  protected static <T> T decode(Class<T> tClass, ThriftDecoder thriftDecoder) {
    try {
      T target = tClass.newInstance();
      StructProvider structProvider = STRUCT_CONVERTER_DECODER.convert(target);
      thriftDecoder.readStruct(structProvider);
      return target;
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  protected static <T> T decodeMessage(Class<T> tClass, FunctionCallContext functionCall,
      ThriftDecoder thriftDecoder) {
    try {
      T target = tClass.newInstance();
      final StructProvider structProvider = STRUCT_CONVERTER_DECODER.convert(target);
      MessageProvider messageProvider = ThriftSerializerHelper.buildMessageProvider(
          functionCall, structProvider);
      thriftDecoder.readMessage(messageProvider);
      return target;
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
