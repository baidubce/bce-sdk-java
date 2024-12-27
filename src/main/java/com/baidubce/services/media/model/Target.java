/*
 * Copyright 2015-2020 Baidu, Inc.
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

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * The model which will be used to set target info in creating transcoding job
 */
@Data
public class Target {

    private String targetBucket = null;

    /**
     * target BOS key
     **/
    private String targetKey = null;

    /**
     * preset name
     **/
    private String presetName = null;

    /**
     * auto delogo
     */
    private Boolean autoDelogo;

    /**
     * delogo mode, can be Normal, Inpainting
     */
    private String delogoMode;

    /**
     * delogo area
     * use delogoAreas instead.
     **/
    @Deprecated
    private Area delogoArea = null;

    /**
     * delogo areas
     **/
    private List<Area> delogoAreas = null;

    /**
     * enable auto crop
     **/
    private Boolean autoCrop = null;

    /**
     * crop area
     **/
    private Area crop = null;

    /**
     * watermark with job
     **/
    private List<String> watermarkIds = null;

    /**
     * inserts of video, audio, image, text types
     **/
    private List<Insert> inserts = null;

    private JobCfg jobCfg;

    public String getTargetBucket() {
        return targetBucket;
    }

    public void setTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
    }

    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public Boolean getAutoDelogo() {
        return autoDelogo;
    }

    public void setAutoDelogo(Boolean autoDelogo) {
        this.autoDelogo = autoDelogo;
    }

    public String getDelogoMode() {
        return delogoMode;
    }

    public void setDelogoMode(String delogoMode) {
        this.delogoMode = delogoMode;
    }

    public Area getDelogoArea() {
        return delogoArea;
    }

    public void setDelogoArea(Area delogoArea) {
        this.delogoArea = delogoArea;
    }

    public List<Area> getDelogoAreas() {
        return delogoAreas;
    }

    public void setDelogoAreas(List<Area> delogoAreas) {
        this.delogoAreas = delogoAreas;
    }

    public Boolean getAutoCrop() {
        return autoCrop;
    }

    public void setAutoCrop(Boolean autoCrop) {
        this.autoCrop = autoCrop;
    }

    public Area getCrop() {
        return crop;
    }

    public void setCrop(Area crop) {
        this.crop = crop;
    }

    public List<String> getWatermarkIds() {
        return watermarkIds;
    }

    public void setWatermarkIds(List<String> watermarkIds) {
        this.watermarkIds = watermarkIds;
    }

    public List<Insert> getInserts() {
        return inserts;
    }

    public void setInserts(List<Insert> inserts) {
        this.inserts = inserts;
    }

    public static class JobCfg {
        private String notification;

        public JobCfg() {
        }

        public String getNotification() {
            return notification;
        }

        public void setNotification(String notification) {
            this.notification = notification;
        }
        public JobCfg withNotification(String notification) {
            this.notification = notification;
            return this;
        }
    }

    public JobCfg getJobCfg() {
        return jobCfg;
    }

    public void setJobCfg(JobCfg jobCfg) {
        this.jobCfg = jobCfg;
    }

    public Target withTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
        return this;
    }

    public Target withTargetKey(String targetKey) {
        checkStringNotEmpty(targetKey, "The parameter targetKey should NOT be null or empty string.");
        this.targetKey = targetKey;
        return this;
    }

    public Target withPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
        return this;
    }

    public Target withAutoDelogo(Boolean autoDelogo) {
        this.autoDelogo = autoDelogo;
        return this;
    }

    public Target withDelogoMode(String delogoMode) {
        this.delogoMode = delogoMode;
        return this;
    }

    @Deprecated
    public Target withDelogoArea(Area delogoArea) {
        this.delogoArea = delogoArea;
        return this;
    }

    public Target withDelogoAreas(List<Area> delogoAreas) {
        this.delogoAreas = delogoAreas;
        return this;
    }

    public Target addDelogoArea(Area delogoArea) {
        if (this.delogoAreas == null) {
            delogoAreas = new ArrayList<Area>();
        }
        delogoAreas.add(delogoArea);
        return this;
    }

    public Target withAutpCrop(Boolean autoCrop) {
        this.autoCrop = autoCrop;
        return this;
    }

    public Target withCrop(Area crop) {
        this.crop = crop;
        return this;
    }

    public Target withWatermarkIds(List<String> watermarkIds) {
        this.watermarkIds = watermarkIds;
        return this;
    }

    public Target withJobCfg(JobCfg jobCfg) {
        this.jobCfg = jobCfg;
        return this;
    }

    public Target withInserts(List<Insert> inserts) {
        this.inserts = inserts;
        return this;
    }

    public void addInsert(Insert insert) {
        if (inserts == null) {
            inserts = new ArrayList<Insert>();
        }
        inserts.add(insert);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Target {\n");

        sb.append("    targetKey: ").append(targetKey).append("\n");
        sb.append("    presetName: ").append(presetName).append("\n");
        sb.append("    delogoArea: ").append(delogoArea).append("\n");
        sb.append("    autoCrop: ").append(autoCrop).append("\n");
        sb.append("    crop: ").append(crop).append("\n");
        sb.append("    watermarkIds: ").append(watermarkIds).append("\n");
        sb.append("    inserts: ").append(inserts).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
