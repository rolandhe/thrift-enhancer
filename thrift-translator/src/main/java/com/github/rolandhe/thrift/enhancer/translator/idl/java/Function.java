package com.github.rolandhe.thrift.enhancer.translator.idl.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述thrift service的function
 *
 * @author rolandhe
 */
public class Function {

  /**
   * 名称
   */
  private final String name;

  private final String idlName;

  /**
   * 返回值类型
   */
  private final ThriftType returnType;
  /**
   * 参数
   */
  private final List<StructField> parameterList = new ArrayList<>();
  /**
   * 异常，一般推荐使用
   */
  private final List<StructField> throwList = new ArrayList<>();


  /**
   * 用{@link StructField}列表描述返回值类型，冗余，用于提供性能
   */
  private final List<StructField> returnFieldList;

  public Function(String name, ThriftType returnType,String idlName) {
    this.name = name;
    this.returnType = returnType;
    this.idlName = idlName;
    if (returnType.getType() == SupportType.VOID) {
      this.returnFieldList = null;
    } else {
      this.returnFieldList = new ArrayList<>();
      this.returnFieldList
          .add(new StructField((short) 0, FieldRequire.REQUIRED, returnType, "success"));
    }
  }

  public Function addParameter(StructField funcParameter) {
    this.parameterList.add(funcParameter);
    return this;
  }

  public Function addThrows(StructField funcParameter) {
    this.parameterList.add(funcParameter);
    return this;
  }

  public String getName() {
    return name;
  }

  public ThriftType getReturnType() {
    return returnType;
  }

  public String getIdlName() {
    return idlName;
  }

  public List<StructField> getParameterList() {
    return parameterList;
  }

  public List<StructField> getThrowList() {
    return throwList;
  }

  public List<StructField> getReturnField() {

    return returnFieldList;
  }

}
