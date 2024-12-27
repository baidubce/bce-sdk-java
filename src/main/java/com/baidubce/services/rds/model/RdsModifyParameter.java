package com.baidubce.services.rds.model;

import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modify rds parameter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsModifyParameter {

    private String name;
    private String value;
    private String applyMethod;
    private String etag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RdsModifyParameter() {
    }

    public String getApplyMethod() {
        return applyMethod;
    }

    public void setApplyMethod(String applyMethod) {
        this.applyMethod = applyMethod;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public RdsModifyParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
