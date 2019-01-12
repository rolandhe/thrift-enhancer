package com.github.rolandhe.thrift.enhancer.stuff.codec.coder;

/**
 * @author rolandhe
 */
class ShortStack {

  private short[] vector;
  private int top = -1;

  public ShortStack(int initialCapacity) {
    vector = new short[initialCapacity];
  }

  public short pop() {
    return vector[top--];
  }

  public void push(short pushed) {
    if (vector.length == top + 1) {
      grow();
    }
    vector[++top] = pushed;
  }

  private void grow() {
    short[] newVector = new short[vector.length * 2];
    System.arraycopy(vector, 0, newVector, 0, vector.length);
    vector = newVector;
  }

  public short peek() {
    return vector[top];
  }

  public void clear() {
    top = -1;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("<ShortStack vector:[");
    for (int i = 0; i < vector.length; i++) {
      if (i != 0) {
        sb.append(" ");
      }

      if (i == top) {
        sb.append(">>");
      }

      sb.append(vector[i]);

      if (i == top) {
        sb.append("<<");
      }
    }
    sb.append("]>");
    return sb.toString();
  }
}