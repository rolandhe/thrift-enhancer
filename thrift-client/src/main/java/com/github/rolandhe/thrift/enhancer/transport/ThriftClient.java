package com.github.rolandhe.thrift.enhancer.transport;


import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;

/**
 * thrift客户端抽象
 *
 * @author rolandhe
 */
public interface ThriftClient {
  /**
   * 提供方法调用上下文
   *
   * @param methodName idl中方法的名称, style: serviceName.methodName
   * @return 方法调用的上下文
   */
  FunctionCallContext provide(String methodName);

  /**
   * 向远程服务发送方法request的序列化信息, 并接收解析返回信息
   *
   * @param buffer request的序列化信息
   * @param host   服务的地址
   * @param connectTimeout 连接超时
   * @param soTimeout  发送或接收超时
   * @param responseCallback 返回信息处理回调,解析信息
   * @param <T>
   * @return 最终的返回对象
   */
  <T> T call(byte[] buffer, String host, int connectTimeout, int soTimeout, ResponseCallback<T> responseCallback);

  /**
   * 同 {@link #call(byte[], String, int, int, ResponseCallback)}
   *
   * @param buffer request的序列化信息的容器
   * @param offset request在容器中的开始地址
   * @param len  request在容器中的长度
   * @param host
   * @param connectTimeout
   * @param soTimeout
   * @param responseCallback
   * @param <T>
   * @return
   */
  <T> T call(byte[] buffer, int offset, int len, String host, int connectTimeout, int soTimeout, ResponseCallback<T> responseCallback);
}
