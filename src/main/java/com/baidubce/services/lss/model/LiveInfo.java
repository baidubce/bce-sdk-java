/*
 * Copyright 2015 Baidu, Inc.
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

public class LiveInfo {
    private String userId = null;

    private String sessionId = null;

    private String description = null;

    private String presetName = null;

    private String createTime = null;

    private String lastUpdateTime = null;

    private String status = null;

    private String notification = null;

    private String streamingStatus = null;

    private LivePublish publish = null;

    private LivePlay play = null;

    private SessionErrorInfo error = null;

    private LiveTarget target = null;

    private LiveRecordPrefix record = null;

    private LiveThumbnailPrefix thumbnail = null;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LiveInfo withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LiveInfo withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LiveInfo withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public LiveInfo withPresetName(String presetName) {
        this.presetName = presetName;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public LiveInfo withCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public LiveInfo withLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LiveInfo withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public LiveInfo withNotification(String notification) {
        this.notification = notification;
        return this;
    }

    public String getStreamingStatus() {
        return streamingStatus;
    }

    public void setStreamingStatus(String streamingStatus) {
        this.streamingStatus = streamingStatus;
    }

    public LiveInfo withStreamingStatus(String streamingStatus) {
        this.streamingStatus = streamingStatus;
        return this;
    }


    public LivePublish getPublish() {
        return publish;
    }

    public void setPublish(LivePublish publish) {
        this.publish = publish;
    }

    public LiveInfo withPublish(LivePublish publish) {
        this.publish = publish;
        return this;
    }

    public LivePlay getPlay() {
        return play;
    }

    public void setPlay(LivePlay play) {
        this.play = play;
    }

    public LiveInfo withPlay(LivePlay play) {
        this.play = play;
        return this;
    }

    public SessionErrorInfo getError() {
        return error;
    }

    public void setError(SessionErrorInfo error) {
        this.error = error;
    }

    public LiveInfo withError(SessionErrorInfo error) {
        this.error = error;
        return this;
    }

    public LiveRecordPrefix getRecord() {
        return record;
    }

    public void setRecord(LiveRecordPrefix record) {
        this.record = record;
    }

    public LiveInfo withRecord(LiveRecordPrefix record) {
        this.record = record;
        return this;
    }

    public LiveThumbnailPrefix getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(LiveThumbnailPrefix thumbnail) {
        this.thumbnail = thumbnail;
    }

    public LiveInfo withThumbnail(LiveThumbnailPrefix thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public LiveTarget getTarget() {
        return target;
    }

    public void setTarget(LiveTarget target) {
        this.target = target;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveInfo {\n");
        sb.append("    userId: ").append(userId).append("\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    presetName: ").append(presetName).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        sb.append("    lastUpdateTime: ").append(lastUpdateTime).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("    streamingStatus: ").append(streamingStatus).append("\n");
        sb.append("    publish: ").append(publish).append("\n");
        sb.append("    play: ").append(play).append("\n");
        sb.append("    error: ").append(error).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("    record: ").append(record).append("\n");
        sb.append("    thumbnail: ").append(thumbnail).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
