package com.github.rolandhe.thrift.enhancer.stuff.codec.coder;

import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftCompactConsts;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.Types;
import com.github.rolandhe.thrift.enhancer.stuff.codec.stream.ThriftInputStream;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rolandhe
 */
public class CompactThriftDecoder extends AbstractThriftDecoder implements ThriftDecoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(CompactThriftDecoder.class);

  private final byte[] temp = new byte[10];

  private final ShortStack lastField_ = new ShortStack(15);

  private short lastFieldId_ = 0;
  private Boolean boolValue_ = null;

  public CompactThriftDecoder(
      ThriftInputStream thriftInputStream,
      StructConverter structConverter) {
    super(thriftInputStream, structConverter);
  }

  @Override
  protected int readStringLen() {
    return thriftInputStream.readVarint32();
  }

  @Override
  public void readMessage(MessageProvider messageProvider) throws ThriftExp {
    byte protocolId = thriftInputStream.read8();
    if (protocolId != ThriftCompactConsts.PROTOCOL_ID) {
      throw new ThriftExp(
          "Expected protocol id " + Integer.toHexString(ThriftCompactConsts.PROTOCOL_ID)
              + " but got " + Integer.toHexString(protocolId));
    }
    byte versionAndType = thriftInputStream.read8();
    byte version = (byte) (versionAndType & ThriftCompactConsts.VERSION_MASK);
    if (version != ThriftCompactConsts.VERSION) {
      throw new ThriftExp(
          "Expected version " + ThriftCompactConsts.VERSION + " but got " + version);
    }
    byte type = (byte) ((versionAndType >> ThriftCompactConsts.TYPE_SHIFT_AMOUNT)
        & ThriftCompactConsts.TYPE_BITS);
    int seqid = thriftInputStream.readVarint32();
    String methodName = readString();

    checkMessage(messageProvider, type, seqid, methodName);

    readStruct(messageProvider.getStructProvider(),
        messageProvider.getFunctionCallContext().getMethodName(), true);
  }


  @Override
  public void readStruct(StructProvider structProvider) throws ThriftExp {
    readStruct(structProvider, null, false);
  }

  @Override
  protected MapMeta readMapMeta() {

    int size = thriftInputStream.readVarint32();
    byte keyAndValueType = size == 0 ? 0 : thriftInputStream.read8();
    byte keyType = getTType((byte) (keyAndValueType >> 4));
    byte valType = getTType((byte) (keyAndValueType & 0xf));

    return new MapMeta(keyType, valType, size);
  }

  @Override
  protected CollectionMeta readCollectionMeta() {
    byte sizeAndType = thriftInputStream.read8();
    int size = (sizeAndType >> 4) & 0x0f;
    if (size == 15) {
      size = thriftInputStream.readVarint32();
    }
    byte elementType = getTType(sizeAndType);
    return new CollectionMeta(elementType, size);
  }


  @Override
  public Object readSimple(byte type) {
    if (type == ThriftTypeNumber.BOOL) {
      if (boolValue_ != null) {
        boolean result = boolValue_.booleanValue();
        boolValue_ = null;
        return result;
      }
      byte value = thriftInputStream.read8();
      return value == Types.BOOLEAN_TRUE;
    }

    if (type == ThriftTypeNumber.BYTE) {
      byte value = thriftInputStream.read8();
      return value;
    }
    if (type == ThriftTypeNumber.I16) {
      short value = (short) zigzagToInt(thriftInputStream.readVarint32());
      return value;
    }
    if (type == ThriftTypeNumber.I32) {
      int value = zigzagToInt(thriftInputStream.readVarint32());
      return value;
    }
    if (type == ThriftTypeNumber.I64) {
      long value = zigzagToLong(thriftInputStream.readVarint64());
      return value;
    }
    if (type == ThriftTypeNumber.DOUBLE) {
      thriftInputStream.readUtil(temp, 0, 8);
      return Double.longBitsToDouble(bytesToLong(temp));
    }

    if (type == ThriftTypeNumber.BINARY) {
      int len = thriftInputStream.readVarint32();
      byte[] buf = new byte[len];
      thriftInputStream.readUtil(buf, 0, len);
      return buf;
    }

    if (type == ThriftTypeNumber.STRING) {
      return readString();
    }

    if (type == ThriftTypeNumber.ENUM) {
      int value = zigzagToInt(thriftInputStream.readVarint32());
      return value;
    }
    return null;
  }

  private void readStruct(StructProvider structProvider, String methodName, boolean isResp)
      throws ThriftExp {

    lastField_.push(lastFieldId_);
    lastFieldId_ = 0;

    while (true) {
      byte type = thriftInputStream.read8();
      if (type == ThriftTypeNumber.STOP) {
        break;
      }
      Field field = extractField(type);
      readField(structProvider, methodName, isResp, field.type, field.id);
    }

    lastFieldId_ = lastField_.pop();
  }

  private Field extractField(byte type) {
    short id;
    // mask off the 4 MSB of the type header. it could contain a field id delta.
    short modifier = (short) ((type & 0xf0) >> 4);
    if (modifier == 0) {
      // not a delta. look ahead for the zigzag varint field id.
      id = thriftInputStream.read16();
    } else {
      // has a delta. add the delta to the last read field id.
      id = (short) (lastFieldId_ + modifier);
    }

    if (isBoolType(type)) {
      // save the boolean value in a special instance variable.
      boolValue_ = (byte) (type & 0x0f) == Types.BOOLEAN_TRUE ? Boolean.TRUE : Boolean.FALSE;
    }

    type = getTType(type);
    lastFieldId_ = id;

    return new Field(id, type);
  }


  private boolean isBoolType(byte b) {
    int lowerNibble = b & 0x0f;
    return lowerNibble == Types.BOOLEAN_TRUE || lowerNibble == Types.BOOLEAN_FALSE;
  }

  private byte getTType(byte type) throws ThriftExp {
    switch ((byte) (type & 0x0f)) {
      case ThriftTypeNumber.STOP:
        return ThriftTypeNumber.STOP;
      case Types.BOOLEAN_FALSE:
      case Types.BOOLEAN_TRUE:
        return ThriftTypeNumber.BOOL;
      case Types.BYTE:
        return ThriftTypeNumber.BYTE;
      case Types.I16:
        return ThriftTypeNumber.I16;
      case Types.I32:
        return ThriftTypeNumber.I32;
      case Types.I64:
        return ThriftTypeNumber.I64;
      case Types.DOUBLE:
        return ThriftTypeNumber.DOUBLE;
      case Types.BINARY:
        return ThriftTypeNumber.STRING;
      case Types.LIST:
        return ThriftTypeNumber.LIST;
      case Types.SET:
        return ThriftTypeNumber.SET;
      case Types.MAP:
        return ThriftTypeNumber.MAP;
      case Types.STRUCT:
        return ThriftTypeNumber.STRUCT;
      default:
        throw new ThriftExp("don't know what type: " + (byte) (type & 0x0f));
    }
  }

  private int zigzagToInt(int n) {
    return (n >>> 1) ^ -(n & 1);
  }

  /**
   * Convert from zigzag long to long.
   */
  private long zigzagToLong(long n) {
    return (n >>> 1) ^ -(n & 1);
  }

  private long bytesToLong(byte[] bytes) {
    return
        ((bytes[7] & 0xffL) << 56) |
            ((bytes[6] & 0xffL) << 48) |
            ((bytes[5] & 0xffL) << 40) |
            ((bytes[4] & 0xffL) << 32) |
            ((bytes[3] & 0xffL) << 24) |
            ((bytes[2] & 0xffL) << 16) |
            ((bytes[1] & 0xffL) << 8) |
            ((bytes[0] & 0xffL));
  }

  private class Field {

    final short id;
    final byte type;

    public Field(short id, byte type) {
      this.id = id;
      this.type = type;
    }
  }
}
