package com.github.rolandhe.thrift.enhancer.translator.trans;


import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;

/**
 * thrift动态实例field值转化工具，由字符串协议转换为thrift
 *
 * @author rolandhe
 */
public interface FieldValueTo<T> {

  /**
   * 转换任意类型
   *
   * @param rawValue
   * @param thriftType
   * @param thriftJavaIdl
   * @return
   */
  Object to(T rawValue, ThriftType thriftType, ThriftJavaIdl thriftJavaIdl);

  /**
   * 转换struct或者函数参数对象
   *
   * @param rawValue
   * @param thriftJavaIdl
   * @param targetName
   * @param isFunction
   * @return
   */
  StructInstance to(T rawValue, ThriftJavaIdl thriftJavaIdl, String targetName, boolean isFunction);

}
