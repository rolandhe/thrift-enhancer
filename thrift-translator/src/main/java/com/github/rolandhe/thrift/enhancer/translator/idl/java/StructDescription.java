package com.github.rolandhe.thrift.enhancer.translator.idl.java;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述thrift struct
 *
 * @author rolandhe
 */
public class StructDescription implements Description {

    /**
     * 名称
     */
    private final String name;

    private final String idlName;

    private final String fullName;

    /**
     * field列表
     */
    private final List<StructField> fieldList = new ArrayList<>();

    public StructDescription(String name, String idlName) {
        this.name = name;
        this.idlName = idlName;
        this.fullName = StringUtils.removeEnd(idlName,".thrift") + "." + name;
    }

    public String getName() {
        return name;
    }

    public String getIdlName() {
        return idlName;
    }

    public StructDescription addField(StructField field) {
        this.fieldList.add(field);
        return this;
    }

    public List<StructField> getFieldList() {
        return fieldList;
    }


    @Override
    public String getFullName() {
        return this.fullName;
    }
}
