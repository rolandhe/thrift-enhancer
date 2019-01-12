package com.github.rolandhe.thrift.enhancer.translator.trans;


import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;

/**
 * thrift动态实例field值转化工具，由为thrift转换字符串协议
 *
 * @author rolandhe
 */
public interface FieldValueFrom<T> {

  /**
   * 转换thrift struct或函数参数对象
   *
   * @param container
   * @param structInstance
   * @param enumIndex
   */
  void from(T container, StructInstance structInstance,
            boolean enumIndex);

  /**
   * 转换任意对象
   *
   * @param container
   * @param thriftType
   * @param value
   * @param thriftJavaIdl
   * @param enumIndex
   */
  void from(T container, ThriftType thriftType, Object value, ThriftJavaIdl thriftJavaIdl,
            boolean enumIndex);

}
