package com.github.rolandhe.thrift.enhancer.translator.idl.java;

import java.util.HashMap;
import java.util.Map;

/**
 * thrift 类型体系，包括primitive、enum、struct、map、list、set
 *
 * @author rolandhe
 */
public class ThriftType {

  /**
   * thrift 类型,在解析过程中无法立即知道一个struct、enum、exception到底是哪种类型，
   * 需要在使用时重新修正，如果已经修正过，将不再继续修正，{@link #fixed}表示是否已经
   * 修正过
   */
  private volatile SupportType type;
  /**
   * 是否重新修正过
   */
  private volatile boolean fixed;
  /**
   * struct、enum、exception名称
   */
  private final String structName;
  /**
   * map key或者list element类型
   */
  private ThriftType containedType;
  /**
   * map value 类型
   */
  private ThriftType valueType;
  private final ThriftJavaIdl thriftJavaIdl;


  /**
   * 描述string类型，它是最常用的
   */
  public static final ThriftType STRING_TYPE;



  /**
   * 基础原生数据类型
   */
  private final static Map<String, ThriftType> baseMap = new HashMap<>();

  static {
    baseMap.put("bool", new ThriftType(SupportType.BOOL, null));
    baseMap.put("byte", new ThriftType(SupportType.BYTE, null));
    baseMap.put("i8", new ThriftType(SupportType.I8, null));
    baseMap.put("i16", new ThriftType(SupportType.I16, null));
    baseMap.put("i32", new ThriftType(SupportType.I32, null));
    baseMap.put("i64", new ThriftType(SupportType.I64, null));
    baseMap.put("binary", new ThriftType(SupportType.BINARY, null));
    baseMap.put("double", new ThriftType(SupportType.DOUBLE, null));
    baseMap.put("void", new ThriftType(SupportType.VOID, null));
    baseMap.put("string", new ThriftType(SupportType.STRING, null));
    STRING_TYPE = baseMap.get("string");
  }

  public ThriftType(SupportType type, String structName, ThriftJavaIdl thriftJavaIdl) {
    this.type = type;
    this.structName = structName;
    this.thriftJavaIdl = thriftJavaIdl;
  }

  /**
   * 获取类型，它会动态修正struct、enum、exception类型
   *
   * @return
   */
  public SupportType getType() {
    if (type != SupportType.STRUCT) {
      return type;
    }

    if (fixed) {
      return type;
    }

    Description description = thriftJavaIdl.getDescriptionByName(structName);
    if (description == null) {
      throw new RuntimeException(structName + " is not exist.");
    }
    SupportType myType = SupportType.STRUCT;
    if (description instanceof EnumDescription) {
      myType = SupportType.ENUM;
    }

    if (description instanceof ExceptionDescription) {
      myType = SupportType.ENUM;
    }

    if (myType != SupportType.STRUCT) {
      this.type = myType;
    }
    fixed = true;
    return myType;
  }

  public String getStructName() {
    return structName;
  }

  public ThriftType(SupportType type, ThriftJavaIdl thriftJavaIdl) {
    this(type, null, thriftJavaIdl);
  }

  public static ThriftType of(String baseName) {
    return baseMap.get(baseName);
  }

  public ThriftType getContainedType() {
    return containedType;
  }

  public void setContainedType(ThriftType containedType) {
    this.containedType = containedType;
  }

  public ThriftType getValueType() {
    return valueType;
  }

  public void setValueType(ThriftType valueType) {
    this.valueType = valueType;
  }
}
