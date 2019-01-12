package com.github.rolandhe.thrift.enhancer.translator.idl.java;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述thrift service
 *
 * @author rolandhe
 */
public class ServiceDescription implements Description {

  private final ThriftJavaIdl thriftJavaIdl;

  /**
   * 服务名称
   */
  private final String name;

  private final String fullName;


  /**
   * 继承的服务名称
   */
  private final String inheritServiceName;

  /**
   * 本服务所包含的函数，不包括继承的
   */
  private final Map<String, Function> functionMap = new LinkedHashMap<>();


  public ServiceDescription(ThriftJavaIdl thriftJavaIdl, String name, String inheritServiceName) {
    this.thriftJavaIdl = thriftJavaIdl;
    this.name = name;
    this.inheritServiceName = inheritServiceName;
    this.fullName = StringUtils.removeEnd(thriftJavaIdl.getIdlFileName(),".thrift") + "." + name;
  }

  public String getName() {
    return name;
  }

  public ServiceDescription addFunc(Function function) {
    functionMap.put(function.getName(), function);
    return this;
  }

  /**
   * 根据函数名称获取Function对象，支持继承的Function
   *
   * @param name
   * @return
   */
  public Function getFunc(String name) {
    Function function = functionMap.get(name);
    if (function != null) {
      return function;
    }
    if (inheritServiceName == null || inheritServiceName.length() == 0) {
      return null;
    }
    ServiceDescription inherit = thriftJavaIdl.getServiceWithInclude(inheritServiceName);
    if (inherit == null) {
      return null;
    }
    return inherit.getFunc(name);
  }

  public String getInheritServiceName() {
    return inheritServiceName;
  }

  @Override
  public String getFullName() {
    return fullName;
  }
}
