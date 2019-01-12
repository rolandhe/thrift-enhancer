package com.github.rolandhe.thrift.enhancer.translator.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author rolandhe
 */
@JacksonXmlRootElement(localName="show_result")
public class PojoShowResult {

  public boolean getSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  private boolean success;
}
