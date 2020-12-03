/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * analyze media request
 *
 * @author houshunwei
 */
public class AnalyzeRequest extends AbstractBceRequest {

    private String source;

    private String auth;

    private String preset;

    private String notification;

    private Integer priority;

    private String title;

    private String subTitle;

    private String category;

    private String description;

    @Override
    public AnalyzeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public AnalyzeRequest withMediaSource(String source) {
        this.setSource(source);
        return this;
    }

    public AnalyzeRequest withAuth(String auth) {
        this.setAuth(auth);
        return this;
    }

    public AnalyzeRequest withPreset(String preset) {
        this.setPreset(preset);
        return this;
    }

    public AnalyzeRequest withNotification(String notification) {
        this.setNotification(notification);
        return this;
    }

    public AnalyzeRequest withPriority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public AnalyzeRequest withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AnalyzeRequest{");
        sb.append("source='").append(source).append('\'');
        sb.append(",auth='").append(auth).append('\'');
        sb.append(",preset='").append(preset).append('\'');
        sb.append(",notification='").append(notification).append('\'');
        sb.append(",priority='").append(priority).append('\'');
        sb.append(",title='").append(title).append('\'');
        sb.append(",subTitle='").append(subTitle).append('\'');
        sb.append(",category='").append(category).append('\'');
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
