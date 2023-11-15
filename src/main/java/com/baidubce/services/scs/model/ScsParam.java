package com.baidubce.services.scs.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Scs param item
 */
public class ScsParam {

    private String name;
    @JsonProperty("default")
    private String defaultValue;
    private String value;
    private String forceRestart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getForceRestart() {
        return forceRestart;
    }

    public void setForceRestart(String forceRestart) {
        this.forceRestart = forceRestart;
    }
}
