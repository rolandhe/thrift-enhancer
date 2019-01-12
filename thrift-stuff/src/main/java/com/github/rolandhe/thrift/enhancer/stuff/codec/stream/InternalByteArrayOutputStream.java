package com.github.rolandhe.thrift.enhancer.stuff.codec.stream;

import java.io.ByteArrayOutputStream;

/**
 * thrift协议序列化输出流
 *
 * @author rolandhe
 */
public class InternalByteArrayOutputStream extends ByteArrayOutputStream {

  private final  byte[] buffer = new byte[8];
  private final byte[] i32buf = new byte[5];
  private final byte[] varint64out = new byte[10];

  public InternalByteArrayOutputStream() {
    super(1024);
  }

  public InternalByteArrayOutputStream(int size) {
    super(size);
  }


  public void write8(byte b) {
    buffer[0] = b;
    super.write(buffer, 0, 1);
  }

  public void write16(short b) {
    buffer[0] = (byte) (0xff & (b >> 8));
    buffer[1] = (byte) (0xff & (b));
    super.write(buffer, 0, 2);
  }

  public void write32(int b) {
    buffer[0] = (byte) (0xff & (b >> 24));
    buffer[1] = (byte) (0xff & (b >> 16));
    buffer[2] = (byte) (0xff & (b >> 8));
    buffer[3] = (byte) (0xff & (b));
    super.write(buffer, 0, 4);
  }

  public void write64(long b) {
    buffer[0] = (byte) (0xff & (b >> 56));
    buffer[1] = (byte) (0xff & (b >> 48));
    buffer[2] = (byte) (0xff & (b >> 40));
    buffer[3] = (byte) (0xff & (b >> 32));
    buffer[4] = (byte) (0xff & (b >> 24));
    buffer[5] = (byte) (0xff & (b >> 16));
    buffer[6] = (byte) (0xff & (b >> 8));
    buffer[7] = (byte) (0xff & (b));
    super.write(buffer, 0, 8);
  }


  public void writeVarint32(int n)  {
    int idx = 0;
    while (true) {
      if ((n & ~0x7F) == 0) {
        i32buf[idx++] = (byte)n;
        break;
        // return;
      } else {
        i32buf[idx++] = (byte)((n & 0x7F) | 0x80);
        n >>>= 7;
      }
    }
    super.write(i32buf, 0, idx);
  }


  public void writeVarint64(long n) {
    int idx = 0;
    while (true) {
      if ((n & ~0x7FL) == 0) {
        varint64out[idx++] = (byte)n;
        break;
      } else {
        varint64out[idx++] = ((byte)((n & 0x7F) | 0x80));
        n >>>= 7;
      }
    }
    super.write(varint64out, 0, idx);
  }
}
