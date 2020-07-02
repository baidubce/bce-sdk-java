/*
 * Copyright 2019 Baidu, Inc.
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

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Vcr get audio check result response.
 */
public class GetAudioResponse extends AbstractBceResponse {
    private String mediaId;
    private String source;
    private String description;
    private String preset;
    private String notification;
    private String status;
    private Integer percent;
    private String label;
    private List<CheckResult> results;
    private VcrError error;
    private String createTime;
    private String finishTime;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CheckResult> getResults() {
        return results;
    }

    public void setResults(List<CheckResult> results) {
        this.results = results;
    }

    public VcrError getError() {
        return error;
    }

    public void setError(VcrError error) {
        this.error = error;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAudioResponse{");
        sb.append("mediaId='").append(mediaId).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", preset='").append(preset).append('\'');
        sb.append(", notification='").append(notification).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", percent=").append(percent);
        sb.append(", label='").append(label).append('\'');
        sb.append(", results=").append(results);
        sb.append(", error=").append(error);
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", finishTime='").append(finishTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
