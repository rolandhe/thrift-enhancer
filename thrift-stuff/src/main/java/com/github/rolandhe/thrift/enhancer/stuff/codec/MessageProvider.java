package com.github.rolandhe.thrift.enhancer.stuff.codec;


import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;

/**
 * rpc调用消息提供者
 *
 * @author rolandhe
 */
public interface MessageProvider {

  /**
   * rpc调用消息所封装的struct
   */
  StructProvider getStructProvider();

  /**
   * 函数调用上下文
   */
  FunctionCallContext getFunctionCallContext();
}
