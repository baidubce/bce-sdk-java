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

package com.baidubce.services.media.model;

import com.baidubce.model.AbstractBceResponse;

public class GetPresetResponse extends AbstractBceResponse {
    /**
     * preset name
     **/
    private String     presetName  = null;

    /**
     * preset description
     **/
    private String     description = null;

    /**
     * preset container, options include mp4, flv, hls, mp3, m4a, a-hls, pcm, dash
     **/
    private String     container   = null;

    /**
     * transmux mode
     **/
    private Boolean    transmux    = null;

    /**
     * clip source
     **/
    private Clip       clip        = null;

    /**
     * audio settings
     **/
    private Audio      audio       = null;

    /**
     * video settings
     **/
    private Video      video       = null;

    /**
     * HLS encryption settings
     **/
    private Encryption encryption  = null;

    /**
     * watermark Id
     **/
    private String     watermarkId = null;

    /**
     * multiple watermarks setting
     **/
    private Watermarks watermarks  = null;

    /**
     * transcoding configurations
     **/
    private TransCfg   transCfg    = null;

    /**
     * extra transcoding configurations
     **/
    private ExtraCfg   extraCfg    = null;


    /**
     * the state of the preset (ACTIVE/INACTIVE)
     **/
    private String     state       = null;

    /**
     * the UTC created time of the preset
     **/
    private String     createdTime = null;

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Boolean getTransmux() {
        return transmux;
    }

    public void setTransmux(Boolean transmux) {
        this.transmux = transmux;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public Watermarks getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
    }

    public TransCfg getTransCfg() {
        return transCfg;
    }

    public void setTransCfg(TransCfg transCfg) {
        this.transCfg = transCfg;
    }

    public ExtraCfg getExtraCfg() {
        return extraCfg;
    }

    public void setExtraCfg(ExtraCfg extraCfg) {
        this.extraCfg = extraCfg;
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
        sb.append("    watermarkId: ").append(watermarkId).append("\n");
        sb.append("    watermarks: ").append(watermarks).append("\n");
        sb.append("    transCfg: ").append(transCfg).append("\n");
        sb.append("    extraCfg: ").append(extraCfg).append("\n");
        sb.append("    state: ").append(state).append("\n");
        sb.append("    createdTime: ").append(createdTime).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
