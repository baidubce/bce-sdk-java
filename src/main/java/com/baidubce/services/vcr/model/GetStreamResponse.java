/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vcr.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Vcr get stream check result response.
 */
public class GetStreamResponse extends AbstractBceResponse {
    private String source;
    private String preset;
    private String notification;
    private String status;
    private String label;
    private List<CheckResult> results;
    private VcrError error;
    private String createTime;
    private String finishTime;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CheckResult> getResults() {
        return results;
    }

    public void setResults(List<CheckResult> results) {
        this.results = results;
    }

    public VcrError getError() {
        return error;
    }

    public void setError(VcrError error) {
        this.error = error;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetStreamResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", preset='").append(preset).append('\'');
        sb.append(", notification='").append(notification).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append(", results=").append(results);
        sb.append(", error=").append(error);
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", finishTime='").append(finishTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
