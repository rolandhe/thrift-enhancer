package com.github.rolandhe.thrift.enhancer.transport;

import java.io.InputStream;

/**
 * 处理方法返回信息的回调
 *
 * @author rolandhe
 */
public interface ResponseCallback<T> {
  T callback(InputStream stream);
}
