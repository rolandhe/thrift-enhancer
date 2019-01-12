package com.github.rolandhe.thrift.enhancer.translator.idl.java;

import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述一个thrift idl文件
 *
 * @author rolandhe
 */
public class ThriftJavaIdl {

  /**
   * java 包名
   */
  private String packageName;
  /**
   * idl文件名称
   */
  private final String idlFileName;

  /**
   * idl中定义的枚举
   */
  private final Map<String, EnumDescription> enumMap = new LinkedHashMap<>();
  /**
   * idl中定义的结构
   */
  private final Map<String, StructDescription> structMap = new LinkedHashMap<>();

  /**
   * idl中定义的异常
   */
  private final Map<String, ExceptionDescription> exceptionMap = new LinkedHashMap<>();

  /**
   * idl中定义的service
   */
  private final Map<String, ServiceDescription> serviceMap = new LinkedHashMap<>();

  /**
   * idl中include的其他idl
   */
  private final Map<String, ThriftJavaIdl> includeMap = new LinkedHashMap<>();


  public EnumDescription getEnumByName(String name) {
    int pos = name.indexOf(".");
    if(pos == -1){
      return enumMap.get(name);
    }

    if(pos == 0){
      throw new RuntimeException("invalid enum name of " + name);
    }

    String fileName = name.substring(0,pos) + ".thrift";
    String realName = name.substring(pos + 1);
    ThriftJavaIdl incIdl = includeMap.get(fileName);
    if(incIdl != null) {
      return incIdl.getEnumByName(realName);
    }

    return null;
  }

  public ThriftJavaIdl getByIdlName(String idlFileName){
    if(this.idlFileName.equals(idlFileName)) {
      return this;
    }

    for(ThriftJavaIdl inc : includeMap.values()) {
      ThriftJavaIdl find = inc.getByIdlName(idlFileName);
      if(find != null) {
        return find;
      }
    }
    return null;
  }

  public StructDescription getStructByName(String name) {
    int pos = name.indexOf(".");
    if(pos == -1){
      return structMap.get(name);
    }

    if(pos == 0){
      throw new RuntimeException("invalid struct name of " + name);
    }

    String fileName = name.substring(0,pos) + ".thrift";
    String realName = name.substring(pos + 1);
    ThriftJavaIdl incIdl = includeMap.get(fileName);
    if(incIdl != null) {
      return incIdl.getStructByName(realName);
    }
    return null;
  }

  public ExceptionDescription getExpByName(String name) {
    int pos = name.indexOf(".");
    if(pos == -1) {
      return exceptionMap.get(name);
    }

    if(pos == 0){
      throw new RuntimeException("invalid exp name of " + name);
    }

    String fileName = name.substring(0,pos) + ".thrift";
    String realName = name.substring(pos + 1);
    ThriftJavaIdl incIdl = includeMap.get(fileName);
    if(incIdl != null) {
      return incIdl.getExpByName(realName);
    }
    return null;
  }

  public ServiceDescription getServiceWithInclude(String name){
    int pos = name.indexOf(".");
    if(pos == -1) {
      return serviceMap.get(name);
    }
    if(pos == 0){
      throw new RuntimeException("invalid service name of " + name);
    }

    String fileName = name.substring(0,pos) + ".thrift";
    String realName = name.substring(pos + 1);
    ThriftJavaIdl incIdl = includeMap.get(fileName);
    if(incIdl != null) {
      return incIdl.getServiceWithInclude(realName);
    }
    return null;
  }


  public ServiceDescription getServiceByName(String name) {
      return serviceMap.get(name);
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getIdlFileName() {
    return idlFileName;
  }

  public Map<String, EnumDescription> getEnumMap() {
    return enumMap;
  }

  public Map<String, StructDescription> getStructMap() {
    return structMap;
  }

  public Map<String, ServiceDescription> getServiceMap() {
    return serviceMap;
  }

  public Map<String, ThriftJavaIdl> getIncludeMap() {
    return includeMap;
  }

  public Map<String, ExceptionDescription> getExceptionMap() {
    return exceptionMap;
  }


  public ThriftJavaIdl(String idlFileName) {
    this.idlFileName = idlFileName;
  }

  public ThriftJavaIdl addEnum(EnumDescription enumDescription) {
    this.enumMap.put(enumDescription.getName(), enumDescription);
    return this;
  }

  public ThriftJavaIdl addStruct(StructDescription structDescription) {
    this.structMap.put(structDescription.getName(), structDescription);
    return this;
  }

  public ThriftJavaIdl addException(ExceptionDescription exceptionDescription) {
    this.exceptionMap.put(exceptionDescription.getName(), exceptionDescription);
    return this;
  }

  public ThriftJavaIdl addService(ServiceDescription serviceDescription) {
    this.serviceMap.put(serviceDescription.getName(), serviceDescription);
    return this;
  }

  public ThriftJavaIdl addInclude(ThriftJavaIdl thriftJavaIdl) {
    this.includeMap.put(onlyFileName(thriftJavaIdl.getIdlFileName()), thriftJavaIdl);
    return this;
  }

  private String onlyFileName(String fileName) {
   return Paths.get(fileName).getFileName().toString();
  }

  Description getDescriptionByName(String name) {
    Description description = this.getEnumByName(name);
    if (description != null) {
      return description;
    }
    description = this.getStructByName(name);
    if (description != null) {
      return description;
    }
    description = this.getExpByName(name);
    if (description != null) {
      return description;
    }

    return null;
  }

}
