package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Map;

/**
 * Created by wuyafei on 16/6/28.
 */
public class GetAppResponse extends AbstractBceResponse {
    private String name = null;

    private String description = null;

    private Map<String, String> presets = null;

    private String preset = null;

    private String status = null;

    private String notification = null;

    private String securityPolicy = null;

    private String recording = null;

    private PlayPrefix playPrefix = null;

    private PublishPrefix publishPrefix = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getSecurityPolicy() {
        return securityPolicy;
    }

    public void setSecurityPolicy(String securityPolicy) {
        this.securityPolicy = securityPolicy;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public PlayPrefix getPlayPrefix() {
        return playPrefix;
    }

    public void setPlayPrefix(PlayPrefix playPrefix) {
        this.playPrefix = playPrefix;
    }

    public PublishPrefix getPublishPrefix() {
        return publishPrefix;
    }

    public void setPublishPrefix(PublishPrefix publishPrefix) {
        this.publishPrefix = publishPrefix;
    }

    public Map<String, String> getPresets() {
        return presets;
    }

    public void setPresets(Map<String, String> presets) {
        this.presets = presets;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetAppResponse {\n");
        sb.append("    name: ").append(name).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    preset: ").append(preset).append("\n");
        sb.append("    presets: ").append(presets).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("    securityPolicy: ").append(securityPolicy).append("\n");
        sb.append("    recording: ").append(recording).append("\n");
        sb.append("    playPrefix: ").append(playPrefix).append("\n");
        sb.append("    publishPrefix: ").append(publishPrefix).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
