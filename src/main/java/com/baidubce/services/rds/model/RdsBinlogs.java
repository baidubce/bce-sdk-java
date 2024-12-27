package com.baidubce.services.rds.model;

import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsBinlogs {
    private String binlogId;
    private Integer binlogSizeInBytes;
    private String binlogEndTime;
    private String binlogStartTime;
    private String binlogStatus;
    private String binlogRealId;

    public String getBinlogId() {
        return binlogId;
    }

    public void setBinlogId(String binlogId) {
        this.binlogId = binlogId;
    }

    public Integer getBinlogSizeInBytes() {
        return binlogSizeInBytes;
    }

    public void setBinlogSizeInBytes(Integer binlogSizeInBytes) {
        this.binlogSizeInBytes = binlogSizeInBytes;
    }

    public String getBinlogEndTime() {
        return binlogEndTime;
    }

    public void setBinlogEndTime(String binlogEndTime) {
        this.binlogEndTime = binlogEndTime;
    }

    public String getBinlogStartTime() {
        return binlogStartTime;
    }

    public void setBinlogStartTime(String binlogStartTime) {
        this.binlogStartTime = binlogStartTime;
    }

    public String getBinlogStatus() {
        return binlogStatus;
    }

    public void setBinlogStatus(String binlogStatus) {
        this.binlogStatus = binlogStatus;
    }

    public String getBinlogRealId() {
        return binlogRealId;
    }

    public void setBinlogRealId(String binlogRealId) {
        this.binlogRealId = binlogRealId;
    }
}
