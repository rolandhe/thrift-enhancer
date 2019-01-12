package com.github.rolandhe.thrift.enhancer.transport;

import java.util.List;

/**
 * 多个服务实例的选择策略
 *
 * @author rolandhe
 */
public interface HostSelectPolicy {
  /**
   * 选择实例
   *
   * @param hostList  ip:port list
   * @return
   */
  String select(List<String> hostList);
}
