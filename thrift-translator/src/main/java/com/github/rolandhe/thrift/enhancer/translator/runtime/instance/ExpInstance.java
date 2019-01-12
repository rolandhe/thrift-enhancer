package com.github.rolandhe.thrift.enhancer.translator.runtime.instance;


import com.github.rolandhe.thrift.enhancer.translator.idl.java.ExceptionDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;

import java.util.List;

/**
 * 描述{@link ExceptionDescription}实例，不推荐使用
 *
 * @author rolandhe
 */
public class ExpInstance extends StructInstance {

  public ExpInstance(String name,
      List<StructFieldValue> valueList, ThriftJavaIdl thriftJavaIdl) {
    super(name, valueList, thriftJavaIdl);
  }

  public static ExpInstance instance(ExceptionDescription exceptionDescription,
      ThriftJavaIdl thriftJavaIdl) {
    List<StructFieldValue> valueList = createStructFieldValues(exceptionDescription.getFieldList());
    ExpInstance instance = new ExpInstance(exceptionDescription.getName(), valueList,
        thriftJavaIdl);

    return instance;
  }
}
