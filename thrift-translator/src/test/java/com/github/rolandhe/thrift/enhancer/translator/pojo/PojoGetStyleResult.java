package com.github.rolandhe.thrift.enhancer.translator.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author rolandhe
 */
@JacksonXmlRootElement(localName="getStyle_result")
public class PojoGetStyleResult {

  public PojoAdStyle getSuccess() {
    return success;
  }

  public void setSuccess(PojoAdStyle success) {
    this.success = success;
  }

  private PojoAdStyle success;


}
