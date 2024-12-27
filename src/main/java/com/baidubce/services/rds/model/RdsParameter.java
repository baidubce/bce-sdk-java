package com.baidubce.services.rds.model;

import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Rds parameter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsParameter {

    private String name;
    private String defaultValue;
    private String value;
    private String pendingValue;
    private String type;
    private Boolean dynamic;
    private Boolean modifiable;
    private String allowedValues;
    private String desc;
    private String etag;
    private String configType;
    private String bestValue;
    private String attention;
    private boolean ifCluster;

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

    public String getPendingValue() {
        return pendingValue;
    }

    public void setPendingValue(String pendingValue) {
        this.pendingValue = pendingValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Boolean getModifiable() {
        return modifiable;
    }

    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }

    public String getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(String allowedValues) {
        this.allowedValues = allowedValues;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getBestValue() {
        return bestValue;
    }

    public void setBestValue(String bestValue) {
        this.bestValue = bestValue;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public boolean isIfCluster() {
        return ifCluster;
    }

    public void setIfCluster(boolean ifCluster) {
        this.ifCluster = ifCluster;
    }
}
