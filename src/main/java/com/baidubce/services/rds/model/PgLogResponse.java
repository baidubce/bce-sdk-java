package com.baidubce.services.rds.model;

import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PgLogResponse {
    private String pglogId;
    private long pglogSizeInBytes;
    private String pglogStartTime;
    private String pglogEndTime;

    public String getPglogId() {
        return pglogId;
    }

    public void setPglogId(String pglogId) {
        this.pglogId = pglogId;
    }

    public long getPglogSizeInBytes() {
        return pglogSizeInBytes;
    }

    public void setPglogSizeInBytes(long pglogSizeInBytes) {
        this.pglogSizeInBytes = pglogSizeInBytes;
    }

    public String getPglogStartTime() {
        return pglogStartTime;
    }

    public void setPglogStartTime(String pglogStartTime) {
        this.pglogStartTime = pglogStartTime;
    }

    public String getPglogEndTime() {
        return pglogEndTime;
    }

    public void setPglogEndTime(String pglogEndTime) {
        this.pglogEndTime = pglogEndTime;
    }
}
