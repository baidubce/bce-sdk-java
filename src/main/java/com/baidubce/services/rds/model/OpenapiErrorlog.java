package com.baidubce.services.rds.model;

import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenapiErrorlog {
    private String errorlogId;
    private long errorlogSizeInBytes;
    private String errorlogStartTime;
    private String errorlogEndTime;

    public String getErrorlogId() {
        return errorlogId;
    }

    public void setErrorlogId(String errorlogId) {
        this.errorlogId = errorlogId;
    }

    public long getErrorlogSizeInBytes() {
        return errorlogSizeInBytes;
    }

    public void setErrorlogSizeInBytes(long errorlogSizeInBytes) {
        this.errorlogSizeInBytes = errorlogSizeInBytes;
    }

    public String getErrorlogStartTime() {
        return errorlogStartTime;
    }

    public void setErrorlogStartTime(String errorlogStartTime) {
        this.errorlogStartTime = errorlogStartTime;
    }

    public String getErrorlogEndTime() {
        return errorlogEndTime;
    }

    public void setErrorlogEndTime(String errorlogEndTime) {
        this.errorlogEndTime = errorlogEndTime;
    }
}
