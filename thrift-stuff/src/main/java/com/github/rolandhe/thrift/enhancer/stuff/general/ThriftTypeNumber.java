package com.github.rolandhe.thrift.enhancer.stuff.general;

/**
 * thrift binary协议需要的类型信息，copy from libthrift.jar
 *
 * @author rolandhe
 */
public class ThriftTypeNumber {

  public static final byte STOP = 0;
  public static final byte VOID = 1;
  public static final byte BOOL = 2;
  public static final byte BYTE = 3;
  public static final byte I8 = 3;
  public static final byte DOUBLE = 4;
  public static final byte I16 = 6;
  public static final byte I32 = 8;
  public static final byte I64 = 10;
  public static final byte STRING = 11;
  public static final byte STRUCT = 12;
  public static final byte MAP = 13;
  public static final byte SET = 14;
  public static final byte LIST = 15;
  public static final byte ENUM = 16;

  /**
   * thrift binary 类型在序列化时类型为String
   */
  public static final byte BINARY = -128;


  public static byte asRealThriftType(byte type) {
    if (type == ThriftTypeNumber.BINARY) {
      return ThriftTypeNumber.STRING;
    }
    if (type == ThriftTypeNumber.ENUM) {
      return ThriftTypeNumber.I32;
    }
    return type;
  }

  public static boolean isNumber(byte type) {
    if (type == I8) {
      return true;
    }
    if (type == I16) {
      return true;
    }
    if (type == I32) {
      return true;
    }
    if (type == I64) {
      return true;
    }
    if (type == BOOL) {
      return true;
    }
    if (type == DOUBLE) {
      return true;
    }
    return false;
  }

  public static Object adapt(byte type, String strValue) {
    if (type == I8) {
      return Byte.parseByte(strValue);
    }
    if (type == I16) {
      return Short.parseShort(strValue);
    }
    if (type == I32) {
      return Integer.parseInt(strValue);
    }
    if (type == I64) {
      return Long.parseLong(strValue);
    }
    if (type == BOOL) {
      return "true".equals(strValue);
    }
    if (type == DOUBLE) {
      return Double.parseDouble(strValue);
    }
    throw new RuntimeException("don't support type of " + type);
  }
}
