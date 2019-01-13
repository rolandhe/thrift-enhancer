package com.github.rolandhe.thrift.enhancer.stuff.codec;

/**
 * @author rolandhe
 */
public interface ThriftCompactConsts {
  byte PROTOCOL_ID = (byte)0x82;
  byte VERSION = 1;
  byte VERSION_MASK = 0x1F; // 0001 1111
  byte TYPE_MASK = (byte)0xE0; // 1110 0000
  byte TYPE_BITS = 0x07; // 0000 0111
  int  TYPE_SHIFT_AMOUNT = 5;
}
