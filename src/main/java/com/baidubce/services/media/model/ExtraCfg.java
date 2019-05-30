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

import java.util.List;

public class ExtraCfg {
    /**
     * set the conditions of the video that will disable watermark, options include "portrait"
     **/
    private List<String> watermarkDisableWhitelist = null;

    /**
     * set the segment duration of hls and dash
     **/
    private Float segmentDurationInSecond = null;

    public List<String> getWatermarkDisableWhitelist() {
        return watermarkDisableWhitelist;
    }

    public void setWatermarkDisableWhitelist(List<String> watermarkDisableWhitelist) {
        this.watermarkDisableWhitelist = watermarkDisableWhitelist;
    }

    public ExtraCfg withWatermarkDisableWhitelist(List<String> watermarkDisableWhitelist) {
        this.watermarkDisableWhitelist = watermarkDisableWhitelist;
        return this;
    }

    public Float getSegmentDurationInSecond() {
        return segmentDurationInSecond;
    }

    public void setSegmentDurationInSecond(Float segmentDurationInSecond) {
        this.segmentDurationInSecond = segmentDurationInSecond;
    }

    public ExtraCfg withSegmentDurationInSecond(Float segmentDurationInSecond) {
        this.segmentDurationInSecond = segmentDurationInSecond;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExtraCfg {\n");

        sb.append("    watermarkDisableWhitelist: ").append(watermarkDisableWhitelist).append("\n");
        sb.append("    segmentDurationInSecond: ").append(segmentDurationInSecond).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
