package com.baidubce.services.scs.model.logmanage;

import com.baidubce.model.AbstractBceResponse;

public class ScsLogDetailResponse extends AbstractBceResponse {
    private String logId;
    private String logShowId;
    private Long logSizeInBytes;
    private String logStartTime;
    private String logEndTime;
    private String downloadUrl;
    private String downloadExpires;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogShowId() {
        return logShowId;
    }

    public void setLogShowId(String logShowId) {
        this.logShowId = logShowId;
    }

    public Long getLogSizeInBytes() {
        return logSizeInBytes;
    }

    public void setLogSizeInBytes(Long logSizeInBytes) {
        this.logSizeInBytes = logSizeInBytes;
    }

    public String getLogStartTime() {
        return logStartTime;
    }

    public void setLogStartTime(String logStartTime) {
        this.logStartTime = logStartTime;
    }

    public String getLogEndTime() {
        return logEndTime;
    }

    public void setLogEndTime(String logEndTime) {
        this.logEndTime = logEndTime;
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
