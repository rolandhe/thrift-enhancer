package com.github.rolandhe.thrift.enhancer.stuff.codec.coder;


import com.github.rolandhe.thrift.enhancer.stuff.codec.CollectionProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.FieldProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MapProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftCompactConsts;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftEncoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.Types;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;

import java.io.UnsupportedEncodingException;

/**
 * thrift compact协议实现
 *
 * @author rolandhe
 */
public class CompactThriftEncoder extends AbstractThriftEncode implements ThriftEncoder {

  private final static byte[] ttypeToCompactType = new byte[16];

  static {
    ttypeToCompactType[ThriftTypeNumber.STOP] = ThriftTypeNumber.STOP;
    ttypeToCompactType[ThriftTypeNumber.BOOL] = Types.BOOLEAN_TRUE;
    ttypeToCompactType[ThriftTypeNumber.BYTE] = Types.BYTE;
    ttypeToCompactType[ThriftTypeNumber.I16] = Types.I16;
    ttypeToCompactType[ThriftTypeNumber.I32] = Types.I32;
    ttypeToCompactType[ThriftTypeNumber.I64] = Types.I64;
    ttypeToCompactType[ThriftTypeNumber.DOUBLE] = Types.DOUBLE;
    ttypeToCompactType[ThriftTypeNumber.STRING] = Types.BINARY;
    ttypeToCompactType[ThriftTypeNumber.LIST] = Types.LIST;
    ttypeToCompactType[ThriftTypeNumber.SET] = Types.SET;
    ttypeToCompactType[ThriftTypeNumber.MAP] = Types.MAP;
    ttypeToCompactType[ThriftTypeNumber.STRUCT] = Types.STRUCT;
  }

  private final Context context = new Context();
  private final byte[] temp = new byte[10];


  public CompactThriftEncoder(
      StructConverter structConverter) {
    super(structConverter);
  }

  public CompactThriftEncoder(
      StructConverter structConverter, boolean strictWrite) {
    super(structConverter, strictWrite);
  }

  @Override
  public void writeMessage(MessageProvider messageProvider) throws ThriftExp {
    outputStream.write8(ThriftCompactConsts.PROTOCOL_ID);
    outputStream.write8((byte) ((ThriftCompactConsts.VERSION & ThriftCompactConsts.VERSION_MASK) | (
        (messageProvider.getFunctionCallContext().getThriftMessageType().getType()
            << ThriftCompactConsts.TYPE_SHIFT_AMOUNT) & ThriftCompactConsts.TYPE_MASK)));
    outputStream.writeVarint32(messageProvider.getFunctionCallContext().getSeqId());
    writeValueByType(ThriftTypeNumber.STRING,
        messageProvider.getFunctionCallContext().getShortMethodName());
  }

  @Override
  public void writeStruct(StructProvider struct) throws ThriftExp {
    context.lastField_.push(context.lastFieldId_);
    context.lastFieldId_ = 0;
    super.writeStruct(struct);
    context.lastFieldId_ = context.lastField_.pop();
  }


  @Override
  public void writeMap(MapProvider map) throws ThriftExp {
    if (map.getMapValue().size() == 0) {
      outputStream.write8((byte) 0);
    } else {
      outputStream.writeVarint32(map.getMapValue().size());
      outputStream.write8(
          (byte) (getCompactType(map.getKeyType()) << 4 | getCompactType(map.getValueType())));
    }

    for (Object key : map.getMapValue().keySet()) {
      Object v = map.getMapValue().get(key);
      writeValueByType(map.getKeyType(), key);
      writeValueByType(map.getValueType(), v);
    }
  }

  @Override
  public void writeCollection(CollectionProvider collectionProvider) throws ThriftExp {
    if (collectionProvider.getCollectionValue().size() <= 14) {
      outputStream.write8(
          (byte) (collectionProvider.getCollectionValue().size() << 4 | getCompactType(
              collectionProvider.getElementType())));
    } else {
      outputStream.write8((byte) (0xf0 | getCompactType(collectionProvider.getElementType())));
      outputStream.writeVarint32(collectionProvider.getCollectionValue().size());
    }
    for (Object element : collectionProvider.getCollectionValue()) {
      writeValueByType(collectionProvider.getElementType(), element);
    }
  }


  @Override
  protected void writeField(FieldProvider fieldProvider) {
    if (isNullFieldValue(fieldProvider)) {
      checkOptional(fieldProvider);
      return;
    }
    writeFieldBegin(fieldProvider);

    byte type = fieldProvider.getType();

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
      if (context.booleanField_ != null) {
        // we haven't written the field header yet
        writeFieldBeginInternal(context.booleanField_,
            b ? Types.BOOLEAN_TRUE : Types.BOOLEAN_FALSE);
        context.booleanField_ = null;
      } else {
        // we're not part of a field, so just write the value.
        outputStream.write8(b ? Types.BOOLEAN_TRUE : Types.BOOLEAN_FALSE);
      }
      return;
    }
    if (type == ThriftTypeNumber.BYTE) {
      outputStream.write8((byte) value);
      return;
    }
    if (type == ThriftTypeNumber.I16) {
      outputStream.writeVarint32(this.intToZigZag((short) value));
      return;
    }
    if (type == ThriftTypeNumber.I32) {
      outputStream.writeVarint32(intToZigZag((int) value));
      return;
    }
    if (type == ThriftTypeNumber.I64) {
      outputStream.writeVarint64(longToZigzag((long) value));
      return;
    }
    if (type == ThriftTypeNumber.DOUBLE) {
      fixedLongToBytes(Double.doubleToLongBits((double) value), temp, 0);
      outputStream.write(temp, 0, 8);
      return;
    }

    if (type == ThriftTypeNumber.BINARY) {
      byte[] binValue = (byte[]) value;
      outputStream.writeVarint32(binValue.length);
      outputStream.write(binValue, 0, binValue.length);
      return;
    }

    if (type == ThriftTypeNumber.STRING) {
      try {
        byte[] bytes = ((String) value).getBytes("UTF-8");
        outputStream.writeVarint32(bytes.length);
        outputStream.write(bytes, 0, bytes.length);
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
      return;
    }

    if (type == ThriftTypeNumber.ENUM) {
      outputStream.writeVarint32(intToZigZag((Integer) value));
      return;
    }

    if (type == ThriftTypeNumber.STRUCT) {
      writeStruct(structConverter.convert(value));
      return;
    }

    throw new RuntimeException("this is complex type");
  }

  private void fixedLongToBytes(long n, byte[] buf, int off) {
    buf[off + 0] = (byte) (n & 0xff);
    buf[off + 1] = (byte) ((n >> 8) & 0xff);
    buf[off + 2] = (byte) ((n >> 16) & 0xff);
    buf[off + 3] = (byte) ((n >> 24) & 0xff);
    buf[off + 4] = (byte) ((n >> 32) & 0xff);
    buf[off + 5] = (byte) ((n >> 40) & 0xff);
    buf[off + 6] = (byte) ((n >> 48) & 0xff);
    buf[off + 7] = (byte) ((n >> 56) & 0xff);
  }

  private void writeFieldBegin(FieldProvider fieldProvider) {
    byte type = fieldProvider.getType();
    if (type == ThriftTypeNumber.BOOL) {
      // we want to possibly include the value, so we'll wait.
      context.booleanField_ = fieldProvider;
      return;
    }
    writeFieldBeginInternal(fieldProvider, (byte) -1);
  }

  private void writeFieldBeginInternal(FieldProvider fieldProvider, byte typeOverride) {
    // short lastField = lastField_.pop();

    // if there's a type override, use that.
    byte typeToWrite = typeOverride == -1 ? getCompactType(ThriftTypeNumber.asRealThriftType(fieldProvider.getType())) : typeOverride;

    // check if we can use delta encoding for the field id
    if (fieldProvider.getId() > context.lastFieldId_
        && fieldProvider.getId() - context.lastFieldId_ <= 15) {
      // write them together
      int compactValue = (fieldProvider.getId() - context.lastFieldId_) << 4 | typeToWrite;
      outputStream.write8((byte) compactValue);
    } else {
      // write them separate
      outputStream.write8(typeToWrite);
      outputStream.write16(fieldProvider.getId());
    }

    context.lastFieldId_ = fieldProvider.getId();
    // lastField_.push(field.id);
  }

  private static class Context {

    /**
     * Used to keep track of the last field for the current and previous structs, so we can do the
     * delta stuff.
     */
    private ShortStack lastField_ = new ShortStack(15);

    private short lastFieldId_ = 0;

    /**
     * If we encounter a boolean field begin, save the TField here so it can have the value
     * incorporated.
     */
    private FieldProvider booleanField_ = null;

    /**
     * If we read a field header, and it's a boolean field, save the boolean value here so that
     * readBool can use it.
     */
    private Boolean boolValue_ = null;

    private void writeStructBegin() {
      lastField_.push(lastFieldId_);
      lastFieldId_ = 0;
    }

    private void writeStructEnd() {
      lastFieldId_ = lastField_.pop();
    }
  }


  private long longToZigzag(long l) {
    return (l << 1) ^ (l >> 63);
  }

  private int intToZigZag(int n) {
    return (n << 1) ^ (n >> 31);
  }

  private byte getCompactType(byte ttype) {
    return ttypeToCompactType[ttype];
  }

}
