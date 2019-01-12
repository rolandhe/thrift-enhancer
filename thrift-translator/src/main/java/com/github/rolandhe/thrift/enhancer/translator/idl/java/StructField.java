package com.github.rolandhe.thrift.enhancer.translator.idl.java;

/**
 * 描述Struct field或者Function 参数
 *
 * @author rolandhe
 */
public class StructField {

  /**
   * thrift id
   */
  private final short id;

  /**
   * 是否可选，对应Function参数时无效
   */
  private final FieldRequire fieldRequire;

  /**
   * 类型
   */
  private final ThriftType fieldType;

  /**
   * 名称
   */
  private final String name;

  public StructField(short id, FieldRequire fieldRequire, ThriftType fieldType, String name) {
    this.id = id;
    this.fieldRequire = fieldRequire == null ? FieldRequire.REQUIRED : fieldRequire;
    this.fieldType = fieldType;
    this.name = name;
  }

  public short getId() {
    return id;
  }

  public FieldRequire getFieldRequire() {
    return fieldRequire;
  }

  public ThriftType getFieldType() {
    return fieldType;
  }

  public String getName() {
    return name;
  }
}
