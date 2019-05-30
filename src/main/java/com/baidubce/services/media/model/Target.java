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

import java.util.ArrayList;
import java.util.List;

public class Target {
    /**
     * target BOS key
     **/
    private String targetKey = null;

    /**
     * preset name
     **/
    private String presetName = null;

    /**
     * delogo area
     **/
    private Area delogoArea = null;

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


    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        checkStringNotEmpty(targetKey, "The parameter targetKey should NOT be null or empty string.");
        this.targetKey = targetKey;
    }

    public Target withTargetKey(String targetKey) {
        checkStringNotEmpty(targetKey, "The parameter targetKey should NOT be null or empty string.");
        this.targetKey = targetKey;
        return this;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
    }

    public Target withPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
        return this;
    }

    public Area getDelogoArea() {
        return delogoArea;
    }

    public void setDelogoArea(Area delogoArea) {
        this.delogoArea = delogoArea;
    }

    public Target withDelogoArea(Area delogoArea) {
        this.delogoArea = delogoArea;
        return this;
    }

    public Area getCrop() {
        return crop;
    }

    public void setCrop(Area crop) {
        this.crop = crop;
    }

    public Target withCrop(Area crop) {
        this.crop = crop;
        return this;
    }

    public List<String> getWatermarkIds() {
        return watermarkIds;
    }

    public void setWatermarkIds(List<String> watermarkIds) {
        this.watermarkIds = watermarkIds;
    }

    public Target withWatermarkIds(List<String> watermarkIds) {
        this.watermarkIds = watermarkIds;
        return this;
    }

    public List<Insert> getInserts() {
        return inserts;
    }

    public void setInserts(List<Insert> inserts) {
        this.inserts = inserts;
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
        sb.append("    crop: ").append(crop).append("\n");
        sb.append("    watermarkIds: ").append(watermarkIds).append("\n");
        sb.append("    inserts: ").append(inserts).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
