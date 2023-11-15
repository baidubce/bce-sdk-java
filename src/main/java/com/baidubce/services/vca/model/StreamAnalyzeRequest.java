/**
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * stream analyze request.
 *
 * @author chenzhangli01
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamAnalyzeRequest extends AbstractBceRequest {

    private String source;

    private String preset;

    private String notification;

    private Integer intervalInSecond;

    private String description;

    @Override
    public StreamAnalyzeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public StreamAnalyzeRequest withSource(String source) {
        this.setSource(source);
        return this;
    }

    public StreamAnalyzeRequest withPreset(String preset) {
        this.setPreset(preset);
        return this;
    }

    public StreamAnalyzeRequest withNotification(String notification) {
        this.setNotification(notification);
        return this;
    }

    public StreamAnalyzeRequest withIntervalInSecond(Integer intervalInSecond) {
        this.setIntervalInSecond(intervalInSecond);
        return this;
    }

    public StreamAnalyzeRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StreamAnalyzeRequest{");
        sb.append("source='").append(source).append('\'');
        sb.append(",preset='").append(preset).append('\'');
        sb.append(",notification='").append(notification).append('\'');
        sb.append(",intervalInSecond='").append(intervalInSecond).append('\'');
        sb.append(",description='").append(description).append('\'');
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

    public Integer getIntervalInSecond() {
        return intervalInSecond;
    }

    public void setIntervalInSecond(Integer intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
