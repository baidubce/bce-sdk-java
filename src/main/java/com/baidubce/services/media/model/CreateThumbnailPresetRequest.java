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

import static com.baidubce.util.Validate.checkStringNotEmpty;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request containing all options for creating a preset.
 */
public class CreateThumbnailPresetRequest extends AbstractBceRequest {

    /**
     * The name of the thumbnail preset
     **/
    private String presetName = null;

    /**
     * Preset description
     **/
    private String description = null;

    /**
     * The target information of the thumbnail preset
     **/
    private ThumbnailPresetTarget target = null;

    /**
     * The information that tells how to pick the thumbnails
     **/
    private ThumbnailPresetCapture capture = null;

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
    }

    public CreateThumbnailPresetRequest withPresetName(String presetName) {
        this.setPresetName(presetName);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreateThumbnailPresetRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public ThumbnailPresetTarget getTarget() {
        return target;
    }

    public void setTarget(ThumbnailPresetTarget target) {
        this.target = target;
    }

    public CreateThumbnailPresetRequest withTarget(ThumbnailPresetTarget target) {
        this.target = target;
        return this;
    }

    public ThumbnailPresetCapture getCapture() {
        return capture;
    }

    public void setCapture(ThumbnailPresetCapture capture) {
        this.capture = capture;
    }

    public CreateThumbnailPresetRequest withCapture(ThumbnailPresetCapture capture) {
        this.capture = capture;
        return this;
    }

    @Override
    public CreateThumbnailPresetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
