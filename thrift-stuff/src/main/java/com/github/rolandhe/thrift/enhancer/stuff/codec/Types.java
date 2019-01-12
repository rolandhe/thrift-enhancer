package com.github.rolandhe.thrift.enhancer.stuff.codec;

/**
 * @author rolandhe
 */
public interface Types {

  byte BOOLEAN_TRUE = 0x01;
  byte BOOLEAN_FALSE = 0x02;
  byte BYTE = 0x03;
  byte I16 = 0x04;
  byte I32 = 0x05;
  byte I64 = 0x06;
  byte DOUBLE = 0x07;
  byte BINARY = 0x08;
  byte LIST = 0x09;
  byte SET = 0x0A;
  byte MAP = 0x0B;
  byte STRUCT = 0x0C;
}
