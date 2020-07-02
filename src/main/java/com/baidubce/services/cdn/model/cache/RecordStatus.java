package com.baidubce.services.cdn.model.cache;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class RecordStatus extends JsonObject {
    private String status;
    private String url;
    private String type;
    private String createdAt;
    private String startedAt;
    private String finishedAt;
    private int progress;
    private String reason;
    private String operator;

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status in-progress|completed|failed
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url URL record
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type record type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt task create time
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return startedAt
     */
    public String getStartedAt() {
        return startedAt;
    }

    /**
     * @param startedAt task start time
     */
    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    /**
     * @return finishedAt
     */
    public String getFinishedAt() {
        return finishedAt;
    }

    /**
     * @param finishedAt task finish time
     */
    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    /**
     * @return progress
     */
    public int getProgress() {
        return progress;
    }

    /**
     * @param progress Progress percentage
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason Reason for preloading task failure
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator Operating account
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
}
