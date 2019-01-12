package com.github.rolandhe.thrift.enhancer.translator.idl.parse;

/**
 * include文件的IdlParser生成器
 *
 * @author rolandhe
 */
public interface IncludeParserGenerator {

  /**
   * 根据指定的idlName名称生成解析器
   *
   * @param idlName
   * @return
   */
  IdlParser generate(String idlName);
}
