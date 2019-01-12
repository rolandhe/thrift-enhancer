package com.github.rolandhe.thrift.enhancer.translator.trans;

import com.github.rolandhe.thrift.enhancer.translator.idl.java.Function;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ServiceDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * thrift动态实例构建工具
 *
 * @author rolandhe
 */
public class ThriftDynamicUtil {
  private ThriftDynamicUtil(){}

  /**
   * 构建struct或者function参数实例
   *
   * @param thriftJavaIdl
   * @param targetName
   * @param isFunction
   * @return
   */
  public static StructInstance createStructInstance(ThriftJavaIdl thriftJavaIdl, String targetName,
                                                    boolean isFunction) {
    if (isFunction) {
      Function function = getFunctionByName(thriftJavaIdl, targetName);

      return StructInstance.instanceFunctionParameters(function, thriftJavaIdl);
    }
    StructDescription structDescription = thriftJavaIdl.getStructByName(targetName);
    if (structDescription == null) {
      throw new RuntimeException(targetName + " struct is not exist.");
    }
    return StructInstance.instance(structDescription, thriftJavaIdl);
  }


  /**
   * 根据函数名称获取函数对象
   *
   * @param thriftJavaIdl
   * @param targetName
   * @return
   */
  public static Function getFunctionByName(ThriftJavaIdl thriftJavaIdl, String targetName) {
    String[] names = StringUtils.split(targetName,'.');
    if (names.length != 2) {
      throw new RuntimeException(targetName + " is not a valid function name");
    }
    ServiceDescription serviceDescription = thriftJavaIdl.getServiceByName(names[0]);
    if (serviceDescription == null) {
      throw new RuntimeException(names[0] + " service is not exist.");
    }
    Function function = serviceDescription.getFunc(names[1]);
    if (function == null) {
      throw new RuntimeException(targetName + " service is not exist.");
    }
    return function;
  }

}
