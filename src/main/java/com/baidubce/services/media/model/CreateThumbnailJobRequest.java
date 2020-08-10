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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateThumbnailJobRequest extends AbstractBceRequest {
    /**
     * the pipeline name of the thumbnail job
     **/
    private String pipelineName = null;

    /**
     * the source information of the thumbnail job
     **/
    private ThumbnailSource source = null;

    /**
     * the preset name of the thumbnail job
     **/
    private String presetName = null;

    /**
     * the target information of the  thumbnail job
     **/
    private ThumbnailTarget target = null;

    /**
     * the information that tells how to pick the thumbnails
     **/
    private ThumbnailCapture capture = null;

    /**
     * delogo area setting
     **/
    private Area delogoArea = null;

    /**
     * crop area setting
     **/
    private Area crop = null;

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public CreateThumbnailJobRequest withPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
        return this;
    }

    public ThumbnailSource getSource() {
        return source;
    }

    public void setSource(ThumbnailSource source) {
        this.source = source;
    }

    public CreateThumbnailJobRequest withSource(ThumbnailSource source) {
        this.source = source;
        return this;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public CreateThumbnailJobRequest withPresetName(String presetName) {
        this.presetName = presetName;
        return this;
    }

    public ThumbnailTarget getTarget() {
        return target;
    }

    public void setTarget(ThumbnailTarget target) {
        this.target = target;
    }

    public CreateThumbnailJobRequest withTarget(ThumbnailTarget target) {
        this.target = target;
        return this;
    }

    public ThumbnailCapture getCapture() {
        return capture;
    }

    public void setCapture(ThumbnailCapture capture) {
        this.capture = capture;
    }

    public CreateThumbnailJobRequest withCapture(ThumbnailCapture capture) {
        this.capture = capture;
        return this;
    }

    public Area getDelogoArea() {
        return delogoArea;
    }

    public void setDelogoArea(Area delogoArea) {
        this.delogoArea = delogoArea;
    }

    public CreateThumbnailJobRequest withDelogoArea(Area delogoArea) {
        this.delogoArea = delogoArea;
        return this;
    }

    public Area getCrop() {
        return crop;
    }

    public void setCrop(Area crop) {
        this.crop = crop;
    }

    public CreateThumbnailJobRequest withCrop(Area crop) {
        this.crop = crop;
        return this;
    }

    @Override
    public CreateThumbnailJobRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
