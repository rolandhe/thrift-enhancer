package com.github.rolandhe.thrift.enhancer.stuff.codec;


import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;

/**
 * thrift 反序列化抽象
 *
 * @author rolandhe
 */
public interface ThriftDecoder {

  /**
   * 反序列化rpc调用消息
   *
   * @param messageProvider
   * @throws ThriftExp
   */
  void readMessage(MessageProvider messageProvider) throws ThriftExp;

  /**
   * 反序列化struct
   *
   * @param structProvider
   * @throws ThriftExp
   */
  void readStruct(StructProvider structProvider) throws ThriftExp;


  /**
   * 反序列化map
   *
   * @param mapProvider
   * @throws ThriftExp
   */
  void readMap(MapProvider mapProvider) throws ThriftExp;

  /**
   * 反序列化 list or set
   *
   * @param collectionProvider
   * @throws ThriftExp
   */
  void readCollection(CollectionProvider collectionProvider) throws ThriftExp;

  /**
   * 反序列化primitive类型
   *
   * @param type
   * @return
   */
  Object readSimple(byte type);

}
