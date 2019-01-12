package com.github.rolandhe.thrift.enhancer.translator.idl.parse;

import com.github.rolandhe.thrift.enhancer.translator.idl.ThriftLexer;
import com.github.rolandhe.thrift.enhancer.translator.idl.ThriftParser;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析thrift idl文件为{@link ThriftJavaIdl}的工具,一般不直接使用, 它会被封装在{@link IdlParser}中使用
 *
 * @author rolandhe
 */
public class JavaThriftParser {

  private JavaThriftParser() {
  }

  /**
   * 解析thrift idl文件
   *
   * @param in 包含idl信息的文件流
   * @param idlName 对应idl的名称
   * @param includeParserGenerator 解析include文件的解析器的生成器, 解析器需要知道文件的存放位置
   * @return
   */
  public static ThriftJavaIdl parse(InputStream in, String idlName, IncludeParserGenerator includeParserGenerator) {
    ANTLRInputStream inputStream = null;
    try {
      inputStream = new ANTLRInputStream(in);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ThriftLexer lexer = new ThriftLexer(inputStream);
    CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
    ThriftParser parser = new ThriftParser(commonTokenStream);
    ParseTree parseTree = parser.document();

    ParseTreeWalker walker = new ParseTreeWalker();

    JavaListener listener = new JavaListener(idlName, includeParserGenerator);

    walker.walk(listener, parseTree);

    return listener.getThriftJavaIdl();
  }

}
