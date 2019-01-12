package com.github.rolandhe.thrift.enhancer.stuff.general;

/**
 * thrift通信消息类型
 *
 * @author rolandhe
 */
public enum ThriftMessageType {
  CALL(1), REPLY(2), EXCEPTION(3), ONEWAY(4);


  private final byte type;

  ThriftMessageType(int type) {
    this.type = (byte) type;
  }

  public byte getType() {
    return type;
  }
}
