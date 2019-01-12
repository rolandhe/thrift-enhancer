package com.github.rolandhe.thrift.enhancer.translator.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.MapContainer;

import java.util.Map;

/**
 * @author rolandhe
 */
@JacksonXmlRootElement(localName="work_result")
public class PojoWorkResult {

  public Map<Integer, String> getSuccess() {
    return success;
  }

  public void setSuccess(Map<Integer, String> success) {
    this.success = success;
  }

  @MapContainer(keyClass = Integer.class,valueClass = String.class)
  private Map<Integer,String> success;
}
