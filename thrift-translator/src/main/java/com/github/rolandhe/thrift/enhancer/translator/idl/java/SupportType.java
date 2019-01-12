package com.github.rolandhe.thrift.enhancer.translator.idl.java;

import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;

/**
 * thrift支持的类型
 *
 * @author rolandhe
 */
public enum SupportType {
  VOID(1,false),BOOL(2,true),
  BYTE(3,true),I8(3,true),DOUBLE(4,true),I16(6,true),I32(8,true),I64(10,true),STRING(11,true),STRUCT(12,false)
  ,MAP(13,false),SET(14,false),LIST(15,false),ENUM(16,false),BINARY(-128,false),EXP(-127,false);

  /**
   * 类型对应的值，与{@link ThriftTypeNumber}中描述相同
   */
  private final byte typeValue;
  /**
   * 是否是简单类型,类似java primitive
   */
  private final boolean simple;

  SupportType(int typeValue,boolean simple) {
    this.typeValue = (byte)typeValue;
    this.simple = simple;
  }

  public byte getTypeValue () {
    return typeValue;
  }

  public boolean isSimple(){
    return simple;
  }

}

