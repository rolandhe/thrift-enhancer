package com.github.rolandhe.thrift.enhancer.stuff.codec;

import java.util.List;

/**
 * struct对象提供者
 *
 * @author rolandhe
 */
public interface StructProvider {

  /**
   * field信息列表
   *
   * @return
   */
  List<FieldProvider> getFieldList();
}
