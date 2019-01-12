package com.github.rolandhe.thrift.enhancer.stuff.exp;

/**
 * thrift通用异常
 *
 * @author rolandhe
 */
public class ThriftExp extends RuntimeException {

  public ThriftExp() {
    super();
  }

  public ThriftExp(String message) {
    super(message);
  }

  public ThriftExp(Exception e) {
    super(e);
  }

}
