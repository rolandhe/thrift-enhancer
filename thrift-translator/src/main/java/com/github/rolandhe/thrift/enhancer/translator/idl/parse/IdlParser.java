package com.github.rolandhe.thrift.enhancer.translator.idl.parse;


import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;

/**
 * 用于解析thrift idl文件的接口抽象
 *
 * @author rolandhe
 */
public interface IdlParser {

  /**
   * 解析include idl文件为 {@link ThriftJavaIdl} 对象
   *
   * @param idlFileName include idl文件名称
   * @return
   */
  ThriftJavaIdl parse(String idlFileName);
}
