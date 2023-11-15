/**
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;

/**
 * stream analyze response.
 *
 * @author chenzhangli01
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StreamAnalyzeResponse extends AbstractBceResponse {

    private String source;

    private String preset;

    private String notification;

    private String description;

    private Integer intervalInSecond;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    private Integer durationInSecond;

    private String status;

    private VcaError error;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StreamAnalyzeResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", preset='").append(preset).append('\'');
        sb.append(", notification='").append(notification).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", intervalInSecond='").append(intervalInSecond).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", durationInSecond='").append(durationInSecond).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", error='").append(error).append('\'');
        sb.append('}');
        return sb.toString();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIntervalInSecond() {
        return intervalInSecond;
    }

    public void setIntervalInSecond(Integer intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDurationInSecond() {
        return durationInSecond;
    }

    public void setDurationInSecond(Integer durationInSecond) {
        this.durationInSecond = durationInSecond;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VcaError getError() {
        return error;
    }

    public void setError(VcaError error) {
        this.error = error;
    }
}
