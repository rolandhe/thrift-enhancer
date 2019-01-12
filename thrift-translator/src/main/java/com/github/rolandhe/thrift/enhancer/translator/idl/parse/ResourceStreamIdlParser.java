package com.github.rolandhe.thrift.enhancer.translator.idl.parse;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析存在于classpath路径idl的实现
 *
 * @author rolandhe
 */
public class ResourceStreamIdlParser extends AbstractIdlParser implements IdlParser {

  @Override
  protected InputStream openInputStream(String idlFileName) throws IOException {
    InputStream inputStream = ResourceStreamIdlParser.class.getClassLoader()
        .getResourceAsStream(idlFileName);
    return inputStream;
  }
}
