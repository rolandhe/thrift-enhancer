package com.github.rolandhe.thrift.enhancer.stuff.codec;

import java.util.Collection;

/**
 * set or list 提供者
 *
 * @author rolandhe
 */
public interface CollectionProvider {

  byte getElementType();

  Object createInstance();

  Collection getCollectionValue();
}
