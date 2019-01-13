package com.github.rolandhe.thrift.enhancer.stuff.codec.coder;

import com.github.rolandhe.thrift.enhancer.stuff.codec.CollectionProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.FieldProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MapProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.MessageProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructConverter;
import com.github.rolandhe.thrift.enhancer.stuff.codec.StructProvider;
import com.github.rolandhe.thrift.enhancer.stuff.codec.ThriftDecoder;
import com.github.rolandhe.thrift.enhancer.stuff.codec.stream.ThriftInputStream;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftCallExp;
import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftMessageType;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftSerializerHelper;
import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * ThriftDecoder抽象实现, 通用的功能都内聚到此类中
 *
 * @author rolandhe
 */
public abstract class AbstractThriftDecoder implements ThriftDecoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractThriftDecoder.class);

  protected final ThriftInputStream thriftInputStream;
  protected final StructConverter structConverter;


  protected AbstractThriftDecoder(
      ThriftInputStream thriftInputStream,
      StructConverter structConverter) {
    this.thriftInputStream = thriftInputStream;
    this.structConverter = structConverter;
  }

  protected interface InstanceFactory {

    Object factoryInstance();
  }

  private void checkOptional(FieldProvider fieldProvider) {
    if (!fieldProvider.isOptional()) {
      throw new RuntimeException(fieldProvider.getName() + " is not optional.");
    }
  }

  private boolean isComplexType(byte type) {
    return type == ThriftTypeNumber.MAP
        || type == ThriftTypeNumber.LIST
        || type == ThriftTypeNumber.SET
        || type == ThriftTypeNumber.STRUCT;
  }

  @Override
  public void readMap(MapProvider mapProvider) throws ThriftExp {
    MapMeta mapMeta = readMapMeta();
    byte keyType = mapMeta.keyType;
    byte valueType = mapMeta.valType;
    int size = mapMeta.size;

    if (size > 0 && (keyType != mapProvider.getKeyType() || valueType != mapProvider.getValueType())) {
      throw new RuntimeException("map k/v type mismatch.");
    }



    Map map = mapProvider.getMapValue();
    for (int i = 0; i < size; i++) {
      Object key = readValueByType(keyType, !isComplexType(keyType) ? null : new InstanceFactory() {
        @Override
        public Object factoryInstance() {
          return mapProvider.createKeyInstance();
        }
      });
      Object v = readValueByType(valueType,
          !isComplexType(valueType) ? null : new InstanceFactory() {
            @Override
            public Object factoryInstance() {
              return mapProvider.createValueInstance();
            }
          });
      map.put(key, v);
    }
  }

  @Override
  public void readCollection(CollectionProvider collectionProvider) throws ThriftExp {
    CollectionMeta collectionMeta = readCollectionMeta();
    byte elementType = collectionMeta.elmentType;
    int size = collectionMeta.size;
    if (elementType != collectionProvider.getElementType()) {
      throw new RuntimeException("list or set element type mismatch.");
    }

    for (int i = 0; i < size; i++) {
      Object v = readValueByType(elementType, () -> collectionProvider.createInstance());
      collectionProvider.getCollectionValue().add(v);
    }
  }

  protected void readField(StructProvider structProvider, String methodName, boolean isResp,
                           byte type, short id) {
    int index = id - 1;
    if (isResp) {
      if (id != 0) {
        LOGGER.info("{} response field id is {},expect 0. ", methodName, id);
        throw new RuntimeException(
            String.format("%s response field id is %d,expect 0.", methodName, id));
      }
      index = 0;
    }

    FieldProvider fieldProvider = structProvider.getFieldList().get(index);
    if (type == ThriftTypeNumber.MAP) {
      MapProvider mapProvider = (MapProvider) fieldProvider.getValue();
      readMap(mapProvider);
      if (mapProvider.getMapValue() == null || mapProvider.getMapValue().size() == 0) {
        checkOptional(fieldProvider);
      }
      return;
    }
    if (type == ThriftTypeNumber.SET || type == ThriftTypeNumber.LIST) {
      CollectionProvider collectionProvider = (CollectionProvider) fieldProvider.getValue();
      readCollection(collectionProvider);
      if (collectionProvider.getCollectionValue() == null
          || collectionProvider.getCollectionValue().size() == 0) {
        checkOptional(fieldProvider);
      }
      return;
    }
    byte needReadType = fieldProvider.getType();
    Object readValue = readValueByType(needReadType,
        needReadType != ThriftTypeNumber.STRUCT ? null : new InstanceFactory() {
          @Override
          public Object factoryInstance() {
            return fieldProvider.createInstance();
          }
        });
    if (readValue == null) {
      checkOptional(fieldProvider);
    }
    fieldProvider.setValue(readValue);
  }

  protected Object readValueByType(byte type, InstanceFactory instancer) {
    Object boolValue = readSimple(type);
    if (boolValue != null) {
      return boolValue;
    }

    if (type == ThriftTypeNumber.STRUCT) {
      Object value = instancer.factoryInstance();
      StructProvider structProvider = structConverter.convert(value);
      readStruct(structProvider);
      return value;
    }

    throw new RuntimeException("this is complex type");
  }


  protected String readString() {
    int len = readStringLen();

    byte[] buf = new byte[len];
    thriftInputStream.readAll(buf);
    String value = new String(buf, StandardCharsets.UTF_8);
    return value;
  }

  protected abstract int readStringLen();

  protected abstract MapMeta readMapMeta();

  protected abstract CollectionMeta readCollectionMeta();

  protected class MapMeta {

    final byte keyType;
    final byte valType;
    final int size;

    public MapMeta(byte keyType, byte valType, int size) {
      this.keyType = keyType;
      this.valType = valType;
      this.size = size;
    }
  }

  protected class CollectionMeta {

    final byte elmentType;
    final int size;

    public CollectionMeta(byte elmentType, int size) {
      this.elmentType = elmentType;
      this.size = size;
    }
  }

  protected void checkMessage(MessageProvider messageProvider, byte type, int seqid,
                              String methodName) {
    if (type == ThriftMessageType.EXCEPTION.getType()) {
      ThriftCallExp.ErrorMessage errorMessage = new ThriftCallExp.ErrorMessage();

      readStruct(ThriftSerializerHelper.buildStructProviderForPojo(errorMessage, false));
      ThriftCallExp thriftCallExp = new ThriftCallExp(errorMessage);
      throw thriftCallExp;
    }

    if (seqid != messageProvider.getFunctionCallContext().getSeqId()) {
      throw new ThriftExp(String
          .format("%s failed: out of sequence response: expected %d but got %d", methodName,
              messageProvider.getFunctionCallContext().getSeqId(), seqid));
    }

    if (!methodName.equals(messageProvider.getFunctionCallContext().getShortMethodName())
        || ThriftMessageType.REPLY.getType() != type) {
      throw new ThriftExp("method or type mismatch.");
    }
  }
}
