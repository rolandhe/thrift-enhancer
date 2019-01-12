package com.github.rolandhe.thrift.enhancer.stuff.codec.stream;

import java.io.IOException;
import java.io.InputStream;

/**
 * ThriftInputStream实现，它从一个指定的InputStream读取数据
 *
 * @author rolandhe
 */
public class WrappedThriftInputStream implements ThriftInputStream {

  private byte[] buffer = new byte[8];
  private final InputStream inputStream;

  private WrappedThriftInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public static ThriftInputStream wrap(InputStream inputStream) {
    return new WrappedThriftInputStream(inputStream);
  }

  @Override
  public byte read8() {
    int len = readUtil(buffer, 0, 1);
    return buffer[0];
  }

  @Override
  public short read16() {
    readUtil(buffer, 0, 2);
    int v = buffer[0] << 8;
    v |= (0xff & buffer[1]);
    return (short) v;
  }

  @Override
  public int read32() {
    int len = readUtil(buffer, 0, 4);
    int v = buffer[0] << 24;
    v |= (0xff & buffer[1]) << 16;
    v |= (0xff & buffer[2]) << 8;
    v |= (0xff & buffer[3]);

    return v;
  }

  @Override
  public int readVarint32() {
    int result = 0;
    int shift = 0;

    while (true) {
      byte b = read8();
      result |= (b & 0x7f) << shift;
      if ((b & 0x80) != 0x80) {
        break;
      }
      shift += 7;
    }
    return result;
  }

  @Override
  public long read64() {
    readUtil(buffer, 0, 8);
    long v = (long) buffer[0] << 56;
    v |= (0xffL & buffer[1]) << 48;
    v |= (0xffL & buffer[2]) << 40;
    v |= (0xffL & buffer[3]) << 32;
    v |= (0xffL & buffer[4]) << 24;
    v |= (0xffL & buffer[5]) << 16;
    v |= (0xffL & buffer[6]) << 8;
    v |= (0xffL & buffer[7]);

    return v;
  }

  @Override
  public long readVarint64() {
    int shift = 0;
    long result = 0;
    while (true) {
      byte b = read8();
      result |= (long) (b & 0x7f) << shift;
      if ((b & 0x80) != 0x80) {
        break;
      }
      shift += 7;
    }
    return result;
  }


  @Override
  public void readAll(byte[] buffer) {
    readUtil(buffer, 0, buffer.length);
  }

  @Override
  public int readUtil(byte[] b, int off, int len) {
    int got = off;
    int reading = len - off;
    try {
      while (reading > 0) {
        int r = inputStream.read(b, got, reading);
        if (r == -1) {
          throw new RuntimeException("peer reset.");
        }
        reading -= r;
        got += r;
      }
      return len - off;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
