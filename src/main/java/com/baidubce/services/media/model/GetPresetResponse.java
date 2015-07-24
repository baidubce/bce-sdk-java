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

package com.baidubce.services.media.model;

import com.baidubce.model.AbstractBceResponse;

public class GetPresetResponse extends AbstractBceResponse {
    private String     presetName  = null;
    private String     description = null;
    private String     container   = null;
    private Boolean    transmux    = null;
    private Clip       clip        = null;
    private Audio      audio       = null;
    private Video      video       = null;
    private Encryption encryption  = null;
    private String     state       = null;
    private String     createdTime = null;
    private String watermarkId = null;

    /**
     * preset名称
     **/
    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    /**
     * preset详情描述
     **/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 音视频文件的容器(MP4, FLV, MOV, MP3, M4A)
     **/
    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    /**
     * 是否仅执行容器格式转换
     **/
    public Boolean getTransmux() {
        return transmux;
    }

    public void setTransmux(Boolean transmux) {
        this.transmux = transmux;
    }

    /**
     * 片段截取设置
     **/
    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    /**
     * 音频输出信息
     **/
    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
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

    /**
     * HLS加解密信息的集合
     **/
    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    /**
     * 模板状态(ACTIVE/INACTIVE)
     **/
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 模板创建的时间(UTC格式)
     **/
    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetPresetResponse {\n");

        sb.append("    presetName: ").append(presetName).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    container: ").append(container).append("\n");
        sb.append("    transmux: ").append(transmux).append("\n");
        sb.append("    clip: ").append(clip).append("\n");
        sb.append("    audio: ").append(audio).append("\n");
        sb.append("    video: ").append(video).append("\n");
        sb.append("    encryption: ").append(encryption).append("\n");
        sb.append("    state: ").append(state).append("\n");
        sb.append("    createdTime: ").append(createdTime).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
