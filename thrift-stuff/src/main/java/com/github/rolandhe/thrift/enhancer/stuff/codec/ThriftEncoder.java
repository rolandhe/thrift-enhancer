package com.github.rolandhe.thrift.enhancer.stuff.codec;


import com.github.rolandhe.thrift.enhancer.stuff.exp.ThriftExp;

/**
 * thrift序列化协议
 *
 * @author rolandhe
 */
public interface ThriftEncoder {

  /**
   * 序列化rpc调用消息
   */
  void writeMessage(MessageProvider messageProvider) throws ThriftExp;


  /**
   * 序列化结构
   */
  void writeStruct(StructProvider struct) throws ThriftExp;


  /**
   * 序列化map
   */
  void writeMap(MapProvider map) throws ThriftExp;


  /**
   * 序列化list或者set
   */
  void writeCollection(CollectionProvider list) throws ThriftExp;


  /**
   * 转换序列化内容为byte数组
   */
  byte[] toArray();
}
