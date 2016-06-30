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

import com.baidubce.model.AbstractBceResponse;

public class RefreshSessionResponse extends AbstractBceResponse {
    private String sessionId = null;

    private String description = null;

    private String preset = null;

    private String createTime = null;

    private String status = null;

    private String notification = null;

    private String securityPolicy = null;

    private String streamingStatus = null;

    private String recording = null;

    private LivePublish publish = null;

    private LivePlay play = null;

    private SessionErrorInfo error = null;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getStreamingStatus() {
        return streamingStatus;
    }

    public void setStreamingStatus(String streamingStatus) {
        this.streamingStatus = streamingStatus;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public LivePublish getPublish() {
        return publish;
    }

    public void setPublish(LivePublish publish) {
        this.publish = publish;
    }

    public LivePlay getPlay() {
        return play;
    }

    public void setPlay(LivePlay play) {
        this.play = play;
    }

    public SessionErrorInfo getError() {
        return error;
    }

    public void setError(SessionErrorInfo error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class RefreshSessionResponse {\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    preset: ").append(preset).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("    securityPolicy: ").append(securityPolicy).append("\n");
        sb.append("    streamingStatus: ").append(streamingStatus).append("\n");
        sb.append("    recording: ").append(recording).append("\n");
        sb.append("    publish: ").append(publish).append("\n");
        sb.append("    play: ").append(play).append("\n");
        sb.append("    error: ").append(error).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
