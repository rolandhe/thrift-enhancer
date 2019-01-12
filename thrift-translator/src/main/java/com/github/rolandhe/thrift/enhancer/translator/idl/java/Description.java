package com.github.rolandhe.thrift.enhancer.translator.idl.java;

/**
 * 描述一个thrift 对象,比如:<br>
 *   <ul>
 *     <li>Enum</li>
 *     <li>Struct</li>
 *     <li>Service</li>
 *     <li>Exception</li>
 *   </ul>
 *
 * @author rolandhe
 */
public interface Description {
    String getFullName();
}
