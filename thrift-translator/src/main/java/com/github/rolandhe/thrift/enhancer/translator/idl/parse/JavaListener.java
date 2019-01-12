package com.github.rolandhe.thrift.enhancer.translator.idl.parse;

import com.github.rolandhe.thrift.enhancer.translator.idl.ThriftBaseListener;
import com.github.rolandhe.thrift.enhancer.translator.idl.ThriftLexer;
import com.github.rolandhe.thrift.enhancer.translator.idl.ThriftParser;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.EnumDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ExceptionDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.FieldRequire;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.Function;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ServiceDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructField;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.SupportType;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftType;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Thrift idl语法树遍历监听者实现，用于把thrift idl转换为java对象描述，一个thrift idl最终 会被转换为{@link ThriftJavaIdl}对象. <br>
 *
 * <p>{@link ThriftBaseListener}是由antlr自动生成的,配合{@link ThriftLexer}
 * 和{@link ThriftParser}共同解析thrift idl文件,并遍历. Thrift语法描述参见
 * Resource/Thrift.g4文件</p>
 *
 * @author rolandhe
 */

public class JavaListener extends ThriftBaseListener {

  private final ThriftJavaIdl thriftJavaIdl;
  private final IncludeParserGenerator includeParserGenerator;

  public JavaListener(String idlName, IncludeParserGenerator includeParserGenerator) {
    this.thriftJavaIdl = new ThriftJavaIdl(idlName);
    this.includeParserGenerator = includeParserGenerator;
  }

  @Override
  public void exitInclude(ThriftParser.IncludeContext ctx) {
    String idlName = ctx.LITERAL().getText().substring(1, ctx.LITERAL().getText().length() - 1);
    IdlParser includeParser = includeParserGenerator.generate(idlName);
    ThriftJavaIdl includeThriftJavaIdl = includeParser.parse(idlName);
    thriftJavaIdl.addInclude(includeThriftJavaIdl);
  }

  @Override
  public void exitNamespace(ThriftParser.NamespaceContext ctx) {
    if (ctx.namespacescope().getText().equals("java")) {
      thriftJavaIdl.setPackageName(ctx.ID().getText());
    }
  }

  @Override
  public void exitTenum(ThriftParser.TenumContext ctx) {
    EnumDescription enumDescription = new EnumDescription(ctx.ID().getText(),
        thriftJavaIdl.getIdlFileName());
    for (ThriftParser.TenumfieldContext tenumfieldContext : ctx.tenumfield()) {
      enumDescription.addField(tenumfieldContext.ID().getText());
    }
    this.thriftJavaIdl.addEnum(enumDescription);
  }

  @Override
  public void exitStruct(ThriftParser.StructContext ctx) {
    StructDescription structDescription = new StructDescription(ctx.ID().getText(),
        thriftJavaIdl.getIdlFileName());

    for (ThriftParser.FieldContext fieldContext : ctx.field()) {
      StructField structField = createStructField(fieldContext);
      structDescription.addField(structField);
    }

    this.thriftJavaIdl.addStruct(structDescription);
  }


  @Override
  public void exitException(ThriftParser.ExceptionContext ctx) {
    ExceptionDescription exceptionDescription = new ExceptionDescription(ctx.ID().getText(),
        thriftJavaIdl.getIdlFileName());

    for (ThriftParser.FieldContext fieldContext : ctx.field()) {
      StructField structField = createStructField(fieldContext);
      exceptionDescription.addField(structField);
    }

    this.thriftJavaIdl.addException(exceptionDescription);
  }

  @Override
  public void exitService(ThriftParser.ServiceContext ctx) {
    ThriftParser.ServiceparentContext serviceparentContext = ctx.serviceparent();
    String parent = null;
    if (serviceparentContext != null && serviceparentContext.ID() != null) {
      parent = serviceparentContext.ID().getText();
    }
    ServiceDescription serviceDescription = new ServiceDescription(thriftJavaIdl,
        ctx.ID().getText(), parent);
    for (ThriftParser.FunctionContext functionContext : ctx.function()) {
      serviceDescription.addFunc(createFunction(functionContext, thriftJavaIdl.getIdlFileName()));
    }

    thriftJavaIdl.addService(serviceDescription);
  }

  /**
   * 针对语法中的 "function" 语法规则构建{@link Function}对象
   */
  private Function createFunction(ThriftParser.FunctionContext functionContext, String idlName) {
    ThriftParser.FieldtypeContext fieldtypeContext = functionContext.functiontype().fieldtype();
    ThriftType returnType = null;
    if ("void".equals(functionContext.functiontype().getText())) {
      returnType = ThriftType.of("void");
    } else {
      returnType = parseFieldType(fieldtypeContext);
    }

    String name = functionContext.ID().getText();

    Function function = new Function(name, returnType, idlName);

    for (ThriftParser.FieldContext fieldContext : functionContext.field()) {
      function.addParameter(createFuncParameter(fieldContext));
    }
    ThriftParser.ThrowstatContext throwstatContext = functionContext.throwstat();
    if (throwstatContext != null) {
      for (ThriftParser.FieldContext fieldContext : throwstatContext.field()) {
        function.addThrows(createFuncParameter(fieldContext));
      }
    }
    return function;
  }

  /**
   * 构建 "function" 的参数对象, function的参数描述与"struct"的field描述相同，参见Thrift.g4文件
   */
  private StructField createFuncParameter(ThriftParser.FieldContext fieldContext) {
    short fieldId = parseFieldId(fieldContext);

    ThriftType thriftType = parseFieldType(fieldContext.fieldtype());
    String name = fieldContext.ID().getText();
    return new StructField(fieldId, null, thriftType, name);
  }

  /**
   * 构建 "struct" 的 field
   */
  private StructField createStructField(ThriftParser.FieldContext fieldContext) {
    short fieldId = parseFieldId(fieldContext);

    FieldRequire fieldRequire = null;
    if (fieldContext.fieldreq() != null) {
      fieldRequire = FieldRequire.of(fieldContext.fieldreq().getText());
    }
    ThriftType thriftType = parseFieldType(fieldContext.fieldtype());
    String name = fieldContext.ID().getText();
    parseFieldConstDefault(fieldContext.constvalue());
    return new StructField(fieldId, fieldRequire, thriftType, name);
  }

  /**
   * 构建 thrift 的field id对象
   */
  private short parseFieldId(ThriftParser.FieldContext fieldContext) {

    StringBuilder stringBuilder = new StringBuilder();
    for (TerminalNode terminalNode : fieldContext.fieldid().intconst().DIGIT()) {
      stringBuilder.append(terminalNode.getSymbol().getText());
    }
    return Short.parseShort(stringBuilder.toString());
  }

  /**
   * 不支持feild缺省值
   */
  private Object parseFieldConstDefault(ThriftParser.ConstvalueContext constvalueContext) {
    if (constvalueContext != null) {
      throw new RuntimeException("don't support default const value.");
    }
    return null;
  }

  /**
   * 构建field的类型对象
   */
  private ThriftType parseFieldType(ThriftParser.FieldtypeContext fieldtypeContext) {
    TerminalNode terminalNode = fieldtypeContext.ID();
    if (terminalNode != null) {
      return new ThriftType(SupportType.STRUCT, terminalNode.getText(), thriftJavaIdl);
    }
    ThriftParser.BasetypeContext basetypeContext = fieldtypeContext.basetype();
    if (basetypeContext != null) {
      String baseTypeName = basetypeContext.getText();
      return ThriftType.of(baseTypeName);
    }
    ThriftParser.ContainertypeContext containertypeContext = fieldtypeContext.containertype();
    if (containertypeContext == null) {
      throw new RuntimeException("invalid type");
    }

    if (containertypeContext.maptype() != null) {
      ThriftType thriftType = new ThriftType(SupportType.MAP, thriftJavaIdl);
      thriftType.setContainedType(parseFieldType(containertypeContext.maptype().fieldtype(0)));
      thriftType.setValueType(parseFieldType(containertypeContext.maptype().fieldtype(1)));
      return thriftType;
    }

    ThriftType thriftType = null;
    ThriftParser.FieldtypeContext containerFieldtypeContext = null;

    if (containertypeContext.listtype() != null) {
      thriftType = new ThriftType(SupportType.LIST, thriftJavaIdl);
      containerFieldtypeContext = containertypeContext.listtype().fieldtype();
    }

    if (containertypeContext.settype() != null) {
      thriftType = new ThriftType(SupportType.SET, thriftJavaIdl);
      containerFieldtypeContext = containertypeContext.settype().fieldtype();
    }
    thriftType.setContainedType(parseFieldType(containerFieldtypeContext));
    if (thriftType == null) {
      throw new RuntimeException("invalid type");
    }
    return thriftType;
  }


  public ThriftJavaIdl getThriftJavaIdl() {
    return thriftJavaIdl;
  }
}
