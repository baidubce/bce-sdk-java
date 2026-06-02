package com.baidubce.services.cdn.model;

public class DsaRule {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public DsaRule withType(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public DsaRule withValue(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
