/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.Map;

public class CreateSessionRequest extends AbstractBceRequest {

    private String description = null;

    private String preset = null;

    private Map<String, String> presets = null;

    private String notification = null;

    private String securityPolicy = null;

    private String recording = null;

    private String thumbnail = null;

    private Watermarks watermarks = null;

    private LivePublishInfo publish = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreateSessionRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public CreateSessionRequest withPreset(String preset) {
        this.preset = preset;
        return this;
    }

    public Map<String, String> getPresets() {
        return presets;
    }

    public void setPresets(Map<String, String> presets) {
        this.presets = presets;
    }

    public CreateSessionRequest withPresets(Map<String, String> presets) {
        this.presets = presets;
        return this;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public CreateSessionRequest withNotification(String notification) {
        this.notification = notification;
        return this;
    }

    public String getSecurityPolicy() {
        return securityPolicy;
    }

    public void setSecurityPolicy(String securityPolicy) {
        this.securityPolicy = securityPolicy;
    }

    public CreateSessionRequest withSecurityPolicy(String securityPolicy) {
        this.securityPolicy = securityPolicy;
        return this;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public CreateSessionRequest withRecording(String recording) {
        this.recording = recording;
        return this;
    }

    public LivePublishInfo getPublish() {
        return publish;
    }

    public void setPublish(LivePublishInfo publish) {
        this.publish = publish;
    }

    public CreateSessionRequest withPublish(LivePublishInfo publish) {
        this.publish = publish;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CreateSessionRequest withThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public Watermarks getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
    }

    public CreateSessionRequest withWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
        return this;
    }

    public CreateSessionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class CreateSessionRequest {\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    preset: ").append(preset).append("\n");
        sb.append("    presets: ").append(presets).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("    securityPolicy: ").append(securityPolicy).append("\n");
        sb.append("    recording: ").append(recording).append("\n");
        sb.append("    thumbnail: ").append(thumbnail).append("\n");
        sb.append("    watermarks: ").append(watermarks).append("\n");
        sb.append("    publish: ").append(publish).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
