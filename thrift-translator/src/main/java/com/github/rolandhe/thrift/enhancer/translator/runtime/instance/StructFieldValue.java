package com.github.rolandhe.thrift.enhancer.translator.runtime.instance;


import com.github.rolandhe.thrift.enhancer.translator.idl.java.FieldRequire;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructField;

import java.util.Objects;

/**
 * 描述struct.field或者function参数值，它包括field定义及对应的值
 *
 * @author rolandhe
 */
public class StructFieldValue {

  private final StructField structField;
  private Object value;

  public StructFieldValue(StructField structField) {
    this.structField = structField;
  }

  public StructField getStructField() {
    return structField;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public boolean checkRequired() {
    return structField.getFieldRequire() == FieldRequire.OPTIONAL || value != null;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StructFieldValue that = (StructFieldValue) o;
    return Objects.equals(structField, that.structField) &&
        Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {

    return Objects.hash(structField, value);
  }
}
