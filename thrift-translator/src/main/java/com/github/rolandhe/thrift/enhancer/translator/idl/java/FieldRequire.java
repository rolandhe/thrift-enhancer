package com.github.rolandhe.thrift.enhancer.translator.idl.java;

/**
 * thrift field是否必须描述,缺省 {@link #REQUIRED}
 *
 * @author rolandhe
 */
public enum FieldRequire {
  REQUIRED, OPTIONAL;

  /**
   * 通过名字反查枚举
   *
   * @param name
   * @return
   */
  public static FieldRequire of(String name) {
    for (FieldRequire fieldRequire : FieldRequire.values()) {
      if (fieldRequire.name().equalsIgnoreCase(name)) {
        return fieldRequire;
      }
    }
    return null;
  }
}
