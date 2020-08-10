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

import com.baidubce.model.AbstractBceResponse;

/**
 * The response contains all information of the getted thumbnail preset.
 **/
public class GetThumbnailPresetResponse extends AbstractBceResponse {
    /**
     * The name of the thumbnail preset
     **/
    private String presetName = null;

    /**
     * Preset description
     **/
    private String description = null;

    /**
     * The UTC created time of the thumbnail preset
     **/
    private String createdTime = null;

    /**
     * The state of the thumbnail preset (ACTIVE/INACTIVE)
     **/
    private String state = null;

    /**
     * The target information of the  thumbnail preset
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
        this.presetName = presetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ThumbnailPresetTarget getTarget() {
        return target;
    }

    public void setTarget(ThumbnailPresetTarget target) {
        this.target = target;
    }

    public ThumbnailPresetCapture getCapture() {
        return capture;
    }

    public void setCapture(ThumbnailPresetCapture capture) {
        this.capture = capture;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetThumbnailPresetResponse {\n");

        sb.append("    presetName: ").append(presetName).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    state: ").append(state).append("\n");
        sb.append("    createdTime: ").append(createdTime).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("    capture: ").append(capture).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
