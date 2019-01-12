package com.github.rolandhe.thrift.enhancer.translator.idl.java;


import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 描述Thrift枚举
 *
 * @author rolandhe
 */
public class EnumDescription implements Description {

  /**
   * 枚举名称
   */
  private final String name;

  private final String idlName;

  private final String fullName;


  /**
   * 枚举的field
   */
  private final Set<String> fieldSet = new LinkedHashSet<>();

  public EnumDescription(String name, String idlName) {
    this.name = name;
    this.idlName = idlName;
    this.fullName = StringUtils.removeEnd(idlName,".thrift") + "." + name;
  }

  public String getName() {
    return name;
  }

  public String getIdlName() {
    return idlName;
  }

  public Set<String> getFieldSet() {
    return fieldSet;
  }

  public Set<String> addField(String field) {
    fieldSet.add(field);
    return fieldSet;
  }

  /**
   * 根据枚举名称获取 {@link Enum#ordinal}
   *
   * @param name
   * @return
   */
  public int getIndexByName(String name) {
    int index = 0;
    for (String v : fieldSet) {
      if (v.equals(name)) {
        return index;
      }
      index++;
    }
    throw new RuntimeException("invalid enum value of " + name);
  }

  /**
   * 根据 {@link Enum#ordinal} 获取名称
   * @param pos
   * @return
   */
  public String getNameByIndex(int pos) {
    int index = 0;
    for (String v : fieldSet) {
      if (index == pos) {
        return v;
      }

      index++;
    }
    throw new RuntimeException("invalid enum index of " + pos);
  }

  @Override
  public String getFullName() {
    return this.fullName;
  }
}
