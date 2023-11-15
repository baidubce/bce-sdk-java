package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The task info of downloading rds slow log
 */
public class RdsSlowLogDownloadTask {

    @JsonProperty("slowlogId")
    private String slowLogId;
    @JsonProperty("slowlogSizeInBytes")
    private Integer slowLogSizeInBytes;
    @JsonProperty("slowlogStartTime")
    private String slowLogStartTime;
    @JsonProperty("slowlogEndTime")
    private String slowLogEndTime;

    public String getSlowLogId() {
        return slowLogId;
    }

    public void setSlowLogId(String slowLogId) {
        this.slowLogId = slowLogId;
    }

    public Integer getSlowLogSizeInBytes() {
        return slowLogSizeInBytes;
    }

    public void setSlowLogSizeInBytes(Integer slowLogSizeInBytes) {
        this.slowLogSizeInBytes = slowLogSizeInBytes;
    }

    public String getSlowLogStartTime() {
        return slowLogStartTime;
    }

    public void setSlowLogStartTime(String slowLogStartTime) {
        this.slowLogStartTime = slowLogStartTime;
    }

    public String getSlowLogEndTime() {
        return slowLogEndTime;
    }

    public void setSlowLogEndTime(String slowLogEndTime) {
        this.slowLogEndTime = slowLogEndTime;
    }
}
