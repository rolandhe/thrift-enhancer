package com.github.rolandhe.thrift.enhancer.translator.trans;


import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;

import java.io.InputStream;

/**
 * 字符串协议 / Thrift议转换接口抽象
 *
 * @author rolandhe
 */
public interface Translator {

  /**
   * 转换request数据
   *
   * @param doc 字符串协议数据，json or xml
   * @param thriftJavaIdl
   * @param targetName
   * @param isFunction
   * @return
   */
  byte[] translateRequest(String doc, ThriftJavaIdl thriftJavaIdl, String targetName, boolean isFunction);

  /**
   * 转换request rpc调用消息数据
   *
   * @param doc 字符串协议数据，json or xml
   * @param thriftJavaIdl
   * @param targetName
   * @param isFunction
   * @param functionCall
   * @return
   */
  byte[] translateRequestMessage(String doc, ThriftJavaIdl thriftJavaIdl, String targetName, boolean isFunction, FunctionCallContext functionCall);


  /**
   * 转换response数据
   *
   * @param thriftData thrift协议数据
   * @param thriftJavaIdl
   * @param functionName
   * @param enumIndex 枚举是否使用index进行输出，false表示以文本输出
   * @return
   */
  String translateResponse(byte[] thriftData, ThriftJavaIdl thriftJavaIdl, String functionName, boolean enumIndex);

  /**
   * 转换rpc调用返回消息数据
   *
   * @param inputStream
   * @param thriftJavaIdl
   * @param functionName
   * @param enumIndex
   * @param functionCall
   * @return
   */
  String translateResponseMessage(InputStream inputStream, ThriftJavaIdl thriftJavaIdl, String functionName, boolean enumIndex, FunctionCallContext functionCall);

  /**
   * 转换返回类型为struct的返回值数据，一般用于测试
   *
   * @param thriftData
   * @param thriftJavaIdl
   * @param structName
   * @param enumIndex
   * @return
   */
  String translateResponseStruct(byte[] thriftData, ThriftJavaIdl thriftJavaIdl, String structName, boolean enumIndex);
}
