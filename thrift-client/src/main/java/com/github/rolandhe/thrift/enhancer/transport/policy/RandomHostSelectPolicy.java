package com.github.rolandhe.thrift.enhancer.transport.policy;

import com.github.rolandhe.thrift.enhancer.transport.HostSelectPolicy;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rolandhe
 */
@Resource
public class RandomHostSelectPolicy implements HostSelectPolicy {

  @Override
  public String select(List<String> hostList) {
    int index = (int)(Math.random()*hostList.size());
    return hostList.get(index);
  }
}
