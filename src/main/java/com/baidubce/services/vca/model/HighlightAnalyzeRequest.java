/**
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created on 2022/11/15.
 *
 * @author Zhangli Chen (chenzhangli01@baidu.com)
 */
public class HighlightAnalyzeRequest extends AbstractBceRequest {

    private String source;

    private String auth;

    private String notification;

    private Integer priority;

    private Integer maxDurationTime;

    private String videoType;

    private String description;

    @Override
    public HighlightAnalyzeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public HighlightAnalyzeRequest withSource(String source) {
        this.setSource(source);
        return this;
    }

    public HighlightAnalyzeRequest withAuth(String auth) {
        this.setAuth(auth);
        return this;
    }

    public HighlightAnalyzeRequest withNotification(String notification) {
        this.setNotification(notification);
        return this;
    }

    public HighlightAnalyzeRequest withPriority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public HighlightAnalyzeRequest withMaxDurationTime(Integer maxDurationTime) {
        this.setMaxDurationTime(maxDurationTime);
        return this;
    }

    public HighlightAnalyzeRequest withVideoType(String videoType) {
        this.setVideoType(videoType);
        return this;
    }

    public HighlightAnalyzeRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HighlightAnalyzeRequest{");
        sb.append("source='").append(source).append('\'');
        sb.append(",auth='").append(auth).append('\'');
        sb.append(",notification='").append(notification).append('\'');
        sb.append(",priority='").append(priority).append('\'');
        sb.append(",maxDurationTime='").append(maxDurationTime).append('\'');
        sb.append(",videoType='").append(videoType).append('\'');
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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getMaxDurationTime() {
        return maxDurationTime;
    }

    public void setMaxDurationTime(Integer maxDurationTime) {
        this.maxDurationTime = maxDurationTime;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
