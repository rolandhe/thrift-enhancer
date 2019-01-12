package com.github.rolandhe.thrift.enhancer.stuff.codec.stream;

/**
 * thrift协议输入流抽象
 *
 * @author rolandhe
 */
public interface ThriftInputStream {

  /**
   * 读取byte
   * @return
   */
  byte read8();

  /**
   * 读取short
   *
   * @return
   */
  short read16();

  /**
   * 读取int
   *
   * @return
   */
  int read32();

  int readVarint32();

  /**
   * 读取long
   *
   * @return
   */
  long read64();

  long readVarint64();

  /**
   * 读满整个buffer
   *
   * @param buffer
   */
  void readAll(byte[] buffer);

  /**
   * 读取指定长度的数据填充到指定位置
   *
   * @param b
   * @param off
   * @param len
   * @return
   */
  int readUtil(byte b[], int off, int len);


}
