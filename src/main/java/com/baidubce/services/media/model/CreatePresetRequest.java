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

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.baidubce.util.Validate.checkNotNull;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreatePresetRequest extends AbstractBceRequest {
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
    private String  container   = null;

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
    private String watermarkId = null;

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

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
    }

    public CreatePresetRequest withPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
        return this;
    }

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

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        checkNotNull(container, "The parameter container should NOT be null.");
        this.container = container;
    }

    public CreatePresetRequest withContainer(String container) {
        checkNotNull(container, "The parameter container should NOT be null.");
        this.container = container;
        return this;
    }

    public Boolean getTransmux() {
        return transmux;
    }

    public void setTransmux(Boolean transmux) {
        this.transmux = transmux;
    }

    public CreatePresetRequest withTransmux(Boolean transmux) {
        this.transmux = transmux;
        return this;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public CreatePresetRequest withClip(Clip clip) {
        this.clip = clip;
        return this;
    }

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

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public CreatePresetRequest withEncryption(Encryption encryption) {
        this.encryption = encryption;
        return this;
    }

    public String getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
    }

    public CreatePresetRequest withWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
        return this;
    }

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

    public TransCfg getTransCfg() {
        return transCfg;
    }

    public void setTransCfg(TransCfg transCfg) {
        this.transCfg = transCfg;
    }

    public CreatePresetRequest withTransCfg(TransCfg transCfg) {
        this.transCfg = transCfg;
        return this;
    }

    public ExtraCfg getExtraCfg() {
        return extraCfg;
    }

    public void setExtraCfg(ExtraCfg extraCfg) {
        this.extraCfg = extraCfg;
    }

    public CreatePresetRequest withExtraCfg(ExtraCfg extraCfg) {
        this.extraCfg = extraCfg;
        return this;
    }

    public CreatePresetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreatePresetRequest {\n");

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
        sb.append("}\n");
        return sb.toString();
    }
}
