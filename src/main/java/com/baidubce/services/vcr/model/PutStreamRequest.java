/*
 * Copyright (C) 2017-2020 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.vcr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Put stream request.
 */
public class PutStreamRequest extends AbstractBceRequest {
    /**
     * stream地址，必填
     **/
    private String source;
    private String preset;
    /**
     * 通知地址，必填
     **/
    private String notification;
    private ResultLabelEnum notifyLevel = ResultLabelEnum.REVIEW;
    private Float thumbnailInterval = 1F;
    private Integer audioInterval = 30;
    private String description;

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

    public ResultLabelEnum getNotifyLevel() {
        return notifyLevel;
    }

    public void setNotifyLevel(ResultLabelEnum notifyLevel) {
        this.notifyLevel = notifyLevel;
    }

    public Float getThumbnailInterval() {
        return thumbnailInterval;
    }

    public void setThumbnailInterval(Float thumbnailInterval) {
        this.thumbnailInterval = thumbnailInterval;
    }

    public Integer getAudioInterval() {
        return audioInterval;
    }

    public void setAudioInterval(Integer audioInterval) {
        this.audioInterval = audioInterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PutStreamRequest{");
        sb.append("source='").append(source).append('\'');
        sb.append(", preset='").append(preset).append('\'');
        sb.append(", notification='").append(notification).append('\'');
        sb.append(", notifyLevel='").append(notifyLevel).append('\'');
        sb.append(", thumbnailInterval='").append(thumbnailInterval).append('\'');
        sb.append(", audioInterval='").append(audioInterval).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
