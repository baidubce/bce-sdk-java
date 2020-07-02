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
package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * clip recording file request
 *
 * @Author: yangwenyue
 * @Date: Created in 2019-08-28 15:47
 */
public class RecordingClipRequest extends AbstractBceRequest {

    private String playDomain;

    private String app;

    private String stream;

    private String filename;

    private Integer startTime;

    private Integer endTime;

    private String format = "m3u8";

    private String pipeline;

    private String preset;

    private String clipId;

    private String sourceFile;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getPlayDomain() {
        return playDomain;
    }

    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public String getClipId() {
        return clipId;
    }

    public void setClipId(String clipId) {
        this.clipId = clipId;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    @Override
    public String toString() {
        return "LssRecordingClipRequest{" +
                "playDomain='" + playDomain + '\'' +
                ", app='" + app + '\'' +
                ", stream='" + stream + '\'' +
                ", filename='" + filename + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", format='" + format + '\'' +
                ", pipeline='" + pipeline + '\'' +
                ", preset='" + preset + '\'' +
                ", clipId='" + clipId + '\'' +
                ", sourceFile='" + sourceFile + '\'' +
                '}';
    }
}