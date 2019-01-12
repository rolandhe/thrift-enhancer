package com.github.rolandhe.thrift.enhancer.translator.runtime.instance;


import com.github.rolandhe.thrift.enhancer.stuff.general.ThriftTypeNumber;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.Function;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructField;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述{@link StructDescription}实例
 *
 * @author rolandhe
 */
public class StructInstance {

  /**
   * struct名称
   */
  private final String name;
  private final List<StructFieldValue> structFieldValueList;
  private final ThriftJavaIdl thriftJavaIdl;


  protected StructInstance(String name,
      List<StructFieldValue> structFieldValueList, ThriftJavaIdl thriftJavaIdl) {
    this.name = name;
    this.structFieldValueList = structFieldValueList;
    this.thriftJavaIdl = thriftJavaIdl;
  }

  /**
   * 实例化struct
   *
   * @param structDescription
   * @param thriftJavaIdl
   * @return
   */
  public static StructInstance instance(StructDescription structDescription,
      ThriftJavaIdl thriftJavaIdl) {
    List<StructFieldValue> valueList = createStructFieldValues(structDescription.getFieldList());
    StructInstance instance = new StructInstance(structDescription.getName(), valueList,
        thriftJavaIdl.getByIdlName(structDescription.getIdlName()));

    return instance;
  }

  /**
   * 实例化Function的参数，多个参数可以打包成一个新的对象，按照thrift协议，参数类名称是 funcName_args.
   *
   * @param function
   * @param thriftJavaIdl
   * @return
   */
  public static StructInstance instanceFunctionParameters(Function function,
      ThriftJavaIdl thriftJavaIdl) {
    if (function.getParameterList().size() == 0) {
      return null;
    }

    List<StructFieldValue> valueList = createStructFieldValues(function.getParameterList());

    StructInstance instance = new StructInstance(function.getName() + "_args", valueList,
        thriftJavaIdl.getByIdlName(function.getIdlName()));

    return instance;
  }

  /**
   * 实例化thrift function返回值对象，非struct返回值按照thrift 协议需要打包成一个对象，该对象名称是
   * funcName_result
   *
   * @param function
   * @param thriftJavaIdl
   * @return
   */
  public static StructInstance instanceFunctionReturn(Function function,
      ThriftJavaIdl thriftJavaIdl) {
    if (function.getReturnType().getType().getTypeValue() == ThriftTypeNumber.VOID) {
      return null;
    }

    List<StructFieldValue> valueList = createStructFieldValues(function.getReturnField());

    StructInstance instance = new StructInstance(function.getName() + "_result", valueList,
        thriftJavaIdl.getByIdlName(function.getIdlName()));

    return instance;
  }

  public String getName() {
    return name;
  }

  public List<StructFieldValue> getStructFieldValueList() {
    return structFieldValueList;
  }

  public ThriftJavaIdl getThriftJavaIdl() {
    return thriftJavaIdl;
  }

  public Map<String, StructFieldValue> toFieldValueMap() {
    Map<String, StructFieldValue> map = new HashMap<>(structFieldValueList.size() * 4 / 3);
    this.structFieldValueList.forEach(structFieldValue -> {
      map.put(structFieldValue.getStructField().getName(), structFieldValue);
    });
    return map;
  }

  protected static List<StructFieldValue> createStructFieldValues(
      List<StructField> fieldList) {
    List<StructFieldValue> valueList = new ArrayList<>(fieldList.size());
    for (StructField structField : fieldList) {
      valueList.add(new StructFieldValue(structField));
    }
    return valueList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StructInstance that = (StructInstance) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(thriftJavaIdl, that.thriftJavaIdl) &&
        Objects.equals(structFieldValueList, that.structFieldValueList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, thriftJavaIdl, structFieldValueList);
  }
}
