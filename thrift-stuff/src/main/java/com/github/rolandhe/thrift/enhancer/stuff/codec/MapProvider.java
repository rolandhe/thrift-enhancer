package com.github.rolandhe.thrift.enhancer.stuff.codec;

import java.util.Map;

/**
 * map提供者
 *
 * @author rolandhe
 */
public interface MapProvider {

  byte getKeyType();

  byte getValueType();

  Object createKeyInstance();

  Object createValueInstance();

  Map getMapValue();
}
