package com.github.rolandhe.thrift.enhancer.stuff.codec;

/**
 * 转换 pojo为{@link StructProvider}
 * 的转换器
 *
 * @author rolandhe
 */
public interface StructConverter<T> {

  StructProvider convert(T value);
}
