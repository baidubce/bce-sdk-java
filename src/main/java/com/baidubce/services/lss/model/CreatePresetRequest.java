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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreatePresetRequest extends AbstractBceRequest {

    private  String name = null;

    private String description = null;

    private Boolean forwardOnly = null;

    private Audio audio = null;

    private Video video = null;

    private Hls hls = null;

    private Rtmp rtmp = null;

    private LiveThumbnail thumbnail = null;

    private Watermarks watermarks = null;

    /**
     * 直播模板名称
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreatePresetRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 模板的描述
     **/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreatePresetRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 是否仅进行直播转发，保持输入流分辨率与码率不变
     **/
    public Boolean getForwardOnly() {
        return forwardOnly;
    }

    public void setForwardOnly(Boolean forwardOnly) {
        this.forwardOnly = forwardOnly;
    }

    public CreatePresetRequest withForwardOnly(Boolean forwardOnly) {
        this.forwardOnly = forwardOnly;
        return this;
    }

    /**
     * 音频输出信息的集合
     **/
    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public CreatePresetRequest withAudio(Audio audio) {
        this.audio = audio;
        return this;
    }

    /**
     * 视频输出信息的集合
     **/
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public CreatePresetRequest withVideo(Video video) {
        this.video = video;
        return this;
    }

    /**
     * hls输出参数
     **/
    public Hls getHls() {
        return hls;
    }

    public void setHls(Hls hls) {
        this.hls = hls;
    }

    public CreatePresetRequest withHls(Hls hls) {
        this.hls = hls;
        return this;
    }

    /**
     * rtmp参数
     **/
    public Rtmp getRtmp() {
        return rtmp;
    }

    public void setRtmp(Rtmp rtmp) {
        this.rtmp = rtmp;
    }

    public CreatePresetRequest withRtmp(Rtmp rtmp) {
        this.rtmp = rtmp;
        return this;
    }

    /**
     * 直播水印相关参数
     **/
    public Watermarks getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
    }

    public CreatePresetRequest withWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
        return this;
    }

    /**
     * 缩略图输出信息的集合
     **/
    public LiveThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(LiveThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CreatePresetRequest withThumbnail(LiveThumbnail thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public CreatePresetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class CreatePresetRequest {\n");
        sb.append("    name: ").append(name).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    forwardOnly: ").append(forwardOnly).append("\n");
        sb.append("    audio: ").append(audio).append("\n");
        sb.append("    video: ").append(video).append("\n");
        sb.append("    hls: ").append(hls).append("\n");
        sb.append("    rtmp: ").append(rtmp).append("\n");
        sb.append("    thumbnail: ").append(thumbnail).append("\n");
        sb.append("    watermarks: ").append(watermarks).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
