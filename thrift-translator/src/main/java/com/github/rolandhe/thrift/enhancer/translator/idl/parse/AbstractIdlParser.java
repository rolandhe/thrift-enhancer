package com.github.rolandhe.thrift.enhancer.translator.idl.parse;


import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析 idl文件的抽象实现
 *
 * @author rolandhe
 */
public abstract class AbstractIdlParser implements IdlParser {

  @Override
  public ThriftJavaIdl parse(String idlFileName) {
    try (InputStream inputStream = openInputStream(idlFileName)) {
      return JavaThriftParser.parse(inputStream, idlFileName, getIncludeParserGenerator());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected IncludeParserGenerator getIncludeParserGenerator() {
    return idlName -> AbstractIdlParser.this;
  }

  /**
   * 把include idl文件转换成{@link InputStream}对象
   *
   * @param idlFileName include idl文件名称
   * @return {@link InputStream} 流,使用者负责关闭
   * @throws IOException
   */
  protected abstract InputStream openInputStream(String idlFileName) throws IOException;
}
