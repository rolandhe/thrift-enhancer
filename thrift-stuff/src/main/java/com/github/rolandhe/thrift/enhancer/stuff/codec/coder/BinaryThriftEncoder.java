package com.github.rolandhe.thrift.enhancer.stuff.codec.coder;


import com.github.rolandhe.thrift.enhancer.stuff.codec.MapProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftBinaryConsts;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftEncoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.CollectionProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.FieldProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;

import java.io.UnsupportedEncodingException;

/**
 * 实现thrift Binary序列化协议
 *
 * @author rolandhe
 */
public class BinaryThriftEncoder extends AbstractThriftEncode implements ThriftEncoder {



  public BinaryThriftEncoder(
      StructConverter structConverter) {
    super(structConverter);
  }

  public BinaryThriftEncoder(
      StructConverter structConverter, boolean strictWrite) {
    super(structConverter,strictWrite);
  }

  @Override
  public void writeMessage(MessageProvider messageProvider)
      throws ThriftExp {
    if (strictWrite) {
      int version =
          ThriftBinaryConsts.VERSION_1 | messageProvider.getFunctionCallContext()
              .getThriftMessageType()
              .getType();
      outputStream.write32(version);
      writeValueByType(ThriftTypeNumber.STRING,
          messageProvider.getFunctionCallContext().getShortMethodName());
      outputStream.write32(messageProvider.getFunctionCallContext().getSeqId());
    } else {
      writeValueByType(ThriftTypeNumber.STRING,
          messageProvider.getFunctionCallContext().getShortMethodName());
      outputStream
          .write32(messageProvider.getFunctionCallContext().getThriftMessageType().getType());
      outputStream.write32(messageProvider.getFunctionCallContext().getSeqId());
    }

    writeStruct(messageProvider.getStructProvider());
  }


  @Override
  protected void writeField(FieldProvider fieldProvider) {

    if (isNullFieldValue(fieldProvider)) {
      checkOptional(fieldProvider);
      return;
    }
    byte type = fieldProvider.getType();
    outputStream.write8(ThriftTypeNumber.asRealThriftType(type));
    outputStream.write16(fieldProvider.getId());
    if (type == ThriftTypeNumber.MAP) {
     doWriteMap(fieldProvider);
      return;
    }
    if (type == ThriftTypeNumber.SET || type == ThriftTypeNumber.LIST) {
      doWriteCollection(fieldProvider);

      return;
    }

    writeValueByType(fieldProvider.getType(), fieldProvider.getValue());
  }




  private void writeValueByType(byte type, Object value) {
    if (type == ThriftTypeNumber.BOOL) {
      boolean b = (boolean) value;
      outputStream.write8(b ? (byte) 1 : (byte) 0);
      return;
    }
    if (type == ThriftTypeNumber.BYTE) {
      outputStream.write8((byte) value);
      return;
    }
    if (type == ThriftTypeNumber.I16) {
      outputStream.write16((short) value);
      return;
    }
    if (type == ThriftTypeNumber.I32) {
      outputStream.write32((int) value);
      return;
    }
    if (type == ThriftTypeNumber.I64) {
      outputStream.write64((long) value);
      return;
    }
    if (type == ThriftTypeNumber.DOUBLE) {
      outputStream.write64(Double.doubleToLongBits((double) value));
      return;
    }

    if (type == ThriftTypeNumber.BINARY) {
      byte[] binValue = (byte[]) value;
      outputStream.write32(binValue.length);
      outputStream.write(binValue, 0, binValue.length);
      return;
    }

    if (type == ThriftTypeNumber.STRING) {
      try {
        byte[] bytes = ((String) value).getBytes("UTF-8");
        outputStream.write32(bytes.length);
        outputStream.write(bytes, 0, bytes.length);
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
      return;
    }

    if (type == ThriftTypeNumber.ENUM) {
      outputStream.write32((Integer) value);
      return;
    }

    if (type == ThriftTypeNumber.STRUCT) {
      writeStruct(structConverter.convert(value));
      return;
    }

    throw new RuntimeException("this is complex type");
  }

  @Override
  public void writeMap(MapProvider map) throws ThriftExp {
    outputStream.write8(ThriftTypeNumber.asRealThriftType(map.getKeyType()));
    outputStream.write8(ThriftTypeNumber.asRealThriftType(map.getValueType()));
    outputStream.write32(map.getMapValue().size());

    for (Object key : map.getMapValue().keySet()) {
      Object v = map.getMapValue().get(key);
      writeValueByType(map.getKeyType(), key);
      writeValueByType(map.getValueType(), v);
    }
  }

  @Override
  public void writeCollection(CollectionProvider collectionProvider) throws ThriftExp {
    outputStream.write8(ThriftTypeNumber.asRealThriftType(collectionProvider.getElementType()));
    outputStream.write32(collectionProvider.getCollectionValue().size());

    for (Object element : collectionProvider.getCollectionValue()) {
      writeValueByType(collectionProvider.getElementType(), element);
    }
  }



}
