/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Vca get media tags result response.
 *
 * @author houshunwei
 */
public class QueryResultResponse extends AbstractBceResponse {

    private String source;
    private String status;
    private Integer durationInSecond;
    private String createTime;
    private String preset;
    private String notification;
    private Integer percent;
    private String publishTime;
    private List<TagsResult> results;
    private VcaError error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<TagsResult> getResults() {
        return results;
    }

    public void setResults(List<TagsResult> results) {
        this.results = results;
    }

    public VcaError getError() {
        return error;
    }

    public void setError(VcaError error) {
        this.error = error;
    }

    public void setDurationInSecond(Integer durationInSecond) {
        this.durationInSecond = durationInSecond;
    }

    public Integer getDurationInSecond() {
        return this.durationInSecond;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishTime() {
        return this.getPublishTime();
    }

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

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryResultResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", durationInSecond=").append(durationInSecond);
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", publishTime='").append(publishTime).append('\'');
        sb.append(", preset='").append(preset).append('\'');
        sb.append(", notification='").append(notification).append('\'');
        sb.append(", publishTime=").append(percent);
        sb.append(", results=").append(results);
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}
