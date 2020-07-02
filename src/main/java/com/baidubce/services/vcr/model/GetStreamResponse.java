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

import com.baidubce.model.AbstractBceResponse;

import java.util.Date;
import java.util.List;

/**
 * Vcr get stream check result response.
 */
public class GetStreamResponse extends AbstractBceResponse {
    private String source;
    private String preset;
    private String notification;
    private String status;
    private String label;
    private List<CheckResult> results;
    private VcrError error;
    private Date createTime;
    private Date startTime;
    private Date finishTime;
    private Integer duration;
    private String streamId;
    private String mediaId;
    private String description;
    private StreamParams streamParams;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StreamParams getStreamParams() {
        return streamParams;
    }

    public void setStreamParams(StreamParams streamParams) {
        this.streamParams = streamParams;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetStreamResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", preset='").append(preset).append('\'');
        sb.append(", notification='").append(notification).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append(", results=").append(results);
        sb.append(", error=").append(error);
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", finishTime='").append(finishTime).append('\'');
        sb.append(", duration='").append(duration).append('\'');
        sb.append(", streamId='").append(streamId).append('\'');
        sb.append(", mediaId='").append(mediaId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", streamParams='").append(streamParams).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
