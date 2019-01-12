package com.github.rolandhe.thrift.enhancer.stuff.codec.coder;

import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftBinaryConsts;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.stream.ThriftInputStream;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * 实现thrift binary反序列化协议
 *
 * @author rolandhe
 */
public class BinaryThriftDecoder extends AbstractThriftDecoder implements ThriftDecoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(BinaryThriftDecoder.class);




  public BinaryThriftDecoder(
      ThriftInputStream thriftInputStream,
      StructConverter structConverter) {
    super(thriftInputStream,structConverter);
  }

  @Override
  protected int readStringLen() {
   return thriftInputStream.read32();
  }

  @Override
  protected MapMeta readMapMeta() {
    return new MapMeta(thriftInputStream.read8(),thriftInputStream.read8(),thriftInputStream.read32());
  }

  @Override
  protected CollectionMeta readCollectionMeta() {
    byte elementType = thriftInputStream.read8();
    int size = thriftInputStream.read32();
    return new CollectionMeta(elementType,size);
  }


  @Override
  public void readMessage(MessageProvider messageProvider)
      throws ThriftExp {
    int size = thriftInputStream.read32();
    String methodName;
    int seq;
    byte type;
    if (size < 0) {
      int version = size & ThriftBinaryConsts.VERSION_MASK;
      if (version != ThriftBinaryConsts.VERSION_1) {
        throw new ThriftExp("Bad version in readMessageBegin");
      }
      methodName = (String) readSimple(ThriftTypeNumber.STRING);
      type = (byte) (size & 0x000000ff);
      seq = (int) readSimple(ThriftTypeNumber.I32);
    } else {
      byte[] methodBuffer = new byte[size];
      thriftInputStream.readAll(methodBuffer);
      methodName = new String(methodBuffer, StandardCharsets.UTF_8);
      type = thriftInputStream.read8();
      seq = thriftInputStream.read32();
    }

    checkMessage(messageProvider,type,seq,methodName);
    readStruct(messageProvider.getStructProvider(),
        messageProvider.getFunctionCallContext().getMethodName(), true);
  }

  @Override
  public void readStruct(StructProvider structProvider) throws ThriftExp {
    readStruct(structProvider, null, false);
  }


  private void readStruct(StructProvider structProvider, String methodName, boolean isResp)
      throws ThriftExp {
    while (true) {
      byte type = thriftInputStream.read8();
      if (type == ThriftTypeNumber.STOP) {
        return;
      }
      short id = thriftInputStream.read16();

      if(structProvider.getFieldList().size() <= id) {
        LOGGER.info("type is {}, id is {}.", type, id);
      }

      readField(structProvider, methodName, isResp, type, id);
    }
  }


  @Override
  public Object readSimple(byte type) {
    if (type == ThriftTypeNumber.BOOL) {
      byte value = thriftInputStream.read8();
      boolean boolValue = value == 1;
      return boolValue;
    }

    if (type == ThriftTypeNumber.BYTE) {
      byte value = thriftInputStream.read8();
      return value;
    }
    if (type == ThriftTypeNumber.I16) {
      short value = thriftInputStream.read16();
      return value;
    }
    if (type == ThriftTypeNumber.I32) {
      int value = thriftInputStream.read32();
      return value;
    }
    if (type == ThriftTypeNumber.I64) {
      long value = thriftInputStream.read64();
      return value;
    }
    if (type == ThriftTypeNumber.DOUBLE) {
      long value = thriftInputStream.read64();
      return Double.longBitsToDouble(value);
    }

    if (type == ThriftTypeNumber.BINARY) {
      int len = thriftInputStream.read32();
      byte[] buf = new byte[len];
      thriftInputStream.readAll(buf);
      return buf;
    }

    if (type == ThriftTypeNumber.STRING) {
      return readString();
    }

    if (type == ThriftTypeNumber.ENUM) {
      int value = thriftInputStream.read32();
      return value;
    }
    return null;
  }


}
