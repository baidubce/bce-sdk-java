/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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

/**
 * A specific item in GetStreamResponse.
 */
public class StreamParams {
    private String preset;
    private ResultLabelEnum notifyLevel;
    private Float thumbnailInterval;
    private Integer audioInterval;

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StreamParams{");
        sb.append("preset='").append(preset).append('\'');
        sb.append(", notifyLevel=").append(notifyLevel);
        sb.append(", thumbnailInterval=").append(thumbnailInterval);
        sb.append(", audioInterval=").append(audioInterval);
        sb.append('}');
        return sb.toString();
    }
}
