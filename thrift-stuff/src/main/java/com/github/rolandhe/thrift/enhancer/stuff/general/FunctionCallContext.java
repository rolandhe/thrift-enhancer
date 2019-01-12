package com.github.rolandhe.thrift.enhancer.stuff.general;


import org.apache.commons.lang3.StringUtils;

/**
 * 描述一次函数调用的上下问题，包括:<br>
 *   <ul>
 *     <li>函数名称</li>
 *     <li>调用类型</li>
 *     <li>调用seqid</li>
 *   </ul>
 *
 * @author rolandhe
 */
public class FunctionCallContext {

  private final String methodName;
  private final ThriftMessageType thriftMessageType;
  private final int seqId;
  private final String shortMethodName;

  private FunctionCallContext(String methodName,
      ThriftMessageType thriftMessageType, int seqId) {
    this.methodName = methodName;
    this.thriftMessageType = thriftMessageType;
    this.seqId = seqId;
    String[] array =  StringUtils.split(methodName,'.');
    this.shortMethodName = array[array.length - 1];
  }

  public static FunctionCallContext of(String methodName, ThriftMessageType thriftMessageType,
      int seqId) {
    return new FunctionCallContext(methodName, thriftMessageType, seqId);
  }

  public static FunctionCallContext of(String methodName, int seqId) {
    return new FunctionCallContext(methodName, ThriftMessageType.CALL, seqId);
  }

  public String getMethodName() {
    return methodName;
  }

  public String getShortMethodName() {
   return shortMethodName;
  }

  public ThriftMessageType getThriftMessageType() {
    return thriftMessageType;
  }

  public int getSeqId() {
    return seqId;
  }
}
