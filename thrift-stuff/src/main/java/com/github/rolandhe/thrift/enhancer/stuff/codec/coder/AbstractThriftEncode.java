package com.github.rolandhe.thrift.enhancer.stuff.codec.coder;


import com.github.rolandhe.thrift.enhancer.stuff.codec.CollectionProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.FieldProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MapProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftEncoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.stream.InternalByteArrayOutputStream;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;

/**
 * ThriftEncoder抽象实现, 通用的功能都内聚到此类中
 *
 * @author rolandhe
 */
public abstract class AbstractThriftEncode implements ThriftEncoder {
  protected final InternalByteArrayOutputStream outputStream = new InternalByteArrayOutputStream();
  protected final StructConverter structConverter;
  protected final boolean strictWrite;


  protected AbstractThriftEncode(
      StructConverter structConverter) {
    this(structConverter, true);
  }

  protected AbstractThriftEncode(
      StructConverter structConverter, boolean strictWrite) {
    this.structConverter = structConverter;
    this.strictWrite = strictWrite;
  }

  protected void checkOptional(FieldProvider fieldProvider) {
    if(!fieldProvider.isOptional()) {
      throw new RuntimeException(fieldProvider.getName() + " is not optional for encoder");
    }
  }

  protected boolean isNullFieldValue(FieldProvider fieldProvider) {
    if (fieldProvider.getValue() == null) {
      return true;
    }
    byte type = fieldProvider.getType();
    if (type == ThriftTypeNumber.MAP) {
      return ((MapProvider) fieldProvider.getValue()).getMapValue() == null;
    }
    if (type == ThriftTypeNumber.SET || type == ThriftTypeNumber.LIST) {
      return ((CollectionProvider) fieldProvider.getValue()).getCollectionValue() == null;
    }
    return false;
  }

  @Override
  public void writeStruct(StructProvider struct) throws ThriftExp {
    for (FieldProvider fieldProvider : struct.getFieldList()) {
      writeField(fieldProvider);
    }

    outputStream.write8(ThriftTypeNumber.STOP);
  }

  protected abstract void writeField(FieldProvider fieldProvider);

  @Override
  public byte[] toArray() {
    return outputStream.toByteArray();
  }

  protected void doWriteMap(FieldProvider fieldProvider) {
    MapProvider mapProvider = (MapProvider) fieldProvider.getValue();

    if(mapProvider.getMapValue() == null || mapProvider.getMapValue().size() == 0) {
      checkOptional(fieldProvider);
    }
    writeMap(mapProvider);
  }

  protected void doWriteCollection(FieldProvider fieldProvider) {
    CollectionProvider collectionProvider = (CollectionProvider) fieldProvider.getValue();
    if(collectionProvider.getCollectionValue() == null || collectionProvider.getCollectionValue().size() == 0) {
      checkOptional(fieldProvider);
    }
    writeCollection(collectionProvider);
  }
}
