package com.github.rolandhe.thrift.enhancer.stuff.codec;

/**
 * 描述thrift 消息协议版本信息
 *
 * @author rolandhe
 */
public interface ThriftBinaryConsts {

  int VERSION_MASK = 0xffff0000;
  int VERSION_1 = 0x80010000;
}
