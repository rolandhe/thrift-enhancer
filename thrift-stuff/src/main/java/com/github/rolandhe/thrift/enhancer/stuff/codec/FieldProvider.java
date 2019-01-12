package com.github.rolandhe.thrift.enhancer.stuff.codec;

/**
 * struct field对象提供者
 *
 * @author rolandhe
 */
public interface FieldProvider {

  /**
   * thrift field id
   *
   * @return
   */
  short getId();

  /**
   * field名称
   *
   * @return
   */
  String getName();


  /**
   * field类型
   *
   * @return
   */
  byte getType();

  /**
   * 获取field 值
   *
   * @return
   */
  Object getValue();

  /**
   * 设置field值
   *
   * @param value
   */
  void setValue(Object value);


  /**
   * 为该field类型创建实例
   *
   * @return
   */
  Object createInstance();

  boolean isOptional();

}
