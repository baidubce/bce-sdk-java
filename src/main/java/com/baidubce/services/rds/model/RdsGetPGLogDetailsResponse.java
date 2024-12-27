package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetPGLogDetailsResponse extends AbstractBceResponse {
    private String pglogId;
    private long pglogSizeInBytes;
    private String pglogStartTime;
    private String pglogEndTime;
    private String downloadUrl;
    private String downloadExpires;

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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadExpires() {
        return downloadExpires;
    }

    public void setDownloadExpires(String downloadExpires) {
        this.downloadExpires = downloadExpires;
    }
}
