/*
 * Copyright 2020 Baidu, Inc.
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * The request for updating preset
 */
@Data
public class UpdatePresetRequest extends AbstractBceRequest {
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

    public UpdatePresetRequest withPreset(GetPresetResponse preset) {
        checkStringNotEmpty(preset.getPresetName(),
                "The parameter presetName should NOT be null or empty string.");
        this.presetName = preset.getPresetName();
        this.description = preset.getDescription();
        this.container = preset.getContainer();
        this.transmux = preset.getTransmux();
        this.clip = preset.getClip();
        this.audio = preset.getAudio();
        this.video = preset.getVideo();
        this.encryption = preset.getEncryption();
        this.watermarkId = preset.getWatermarkId();
        this.watermarks = preset.getWatermarks();
        this.transCfg = preset.getTransCfg();
        this.extraCfg = preset.getExtraCfg();
        return this;
    }

    public UpdatePresetRequest withPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
        return this;
    }

    public UpdatePresetRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public UpdatePresetRequest withContainer(String container) {
        checkNotNull(container, "The parameter container should NOT be null.");
        this.container = container;
        return this;
    }

    public UpdatePresetRequest withTransmux(Boolean transmux) {
        this.transmux = transmux;
        return this;
    }

    public UpdatePresetRequest withClip(Clip clip) {
        this.clip = clip;
        return this;
    }

    public UpdatePresetRequest withAudio(Audio audio) {
        this.audio = audio;
        return this;
    }

    public UpdatePresetRequest withVideo(Video video) {
        this.video = video;
        return this;
    }

    public UpdatePresetRequest withEncryption(Encryption encryption) {
        this.encryption = encryption;
        return this;
    }

    public UpdatePresetRequest withWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
        return this;
    }

    public UpdatePresetRequest withWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
        return this;
    }

    public UpdatePresetRequest withTransCfg(TransCfg transCfg) {
        this.transCfg = transCfg;
        return this;
    }

    public UpdatePresetRequest withExtraCfg(ExtraCfg extraCfg) {
        this.extraCfg = extraCfg;
        return this;
    }

    public UpdatePresetRequest withRequestCredentials(BceCredentials credentials) {
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
