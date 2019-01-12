package com.github.rolandhe.thrift.enhancer.translator.idl.parse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 从指定文件中读取idl，root是文件的直接父目录。
 *
 * <p>
 *   /tmp/a/b/x.thrift, root必须是/tmp/a/b/
 *
 * </p>
 *
 * @author rolandhe
 */
public class FileIdlParser extends AbstractIdlParser {
  private final String root;

  public FileIdlParser(String root) {
    this.root = root;
  }

  @Override
  protected InputStream openInputStream(String idlFileName) throws IOException {
    String shortName = Paths.get(idlFileName).getFileName().toString();
    String path = Paths.get(root,shortName).normalize().toString();
    return new FileInputStream(path);
  }

  protected IncludeParserGenerator getIncludeParserGenerator() {
    return idlName -> {
      Path path = Paths.get(root,idlName).normalize();
      return new FileIdlParser(path.getParent().toString());
    };
  }

}
