package com.github.rolandhe.thrift.enhancer.stuff.exp;

/**
 * thrift方法调用异常，它会被自动反序列化"TApplicationException"
 *
 * @author rolandhe
 */
public class ThriftCallExp extends ThriftExp {




  public static class ErrorMessage{
    /**
     * 异常消息
     */
    private String message;
    /**
     * 异常类型
     */
    private int type;

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }
  }

  private final ErrorMessage errorMessage;

  public ThriftCallExp(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  public int getType() {
    return errorMessage.type;
  }

  @Override
  public String getMessage() {
    return errorMessage.message;
  }
}
