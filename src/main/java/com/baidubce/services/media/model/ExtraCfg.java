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

import java.util.List;

/**
 * The model which will be used to set extra configs in preset
 */
@Data
public class ExtraCfg {
    /**
     * set the conditions of the video that will disable watermark, options include "portrait"
     **/
    private List<String> watermarkDisableWhitelist = null;

    /**
     * set the segment duration of hls and dash
     **/
    private Float segmentDurationInSecond = null;

    /**
     * set the gop length
     **/
    private Integer gopLength = null;

    /**
     * enable skip black frame
     **/
    private Boolean skipBlackFrame = null;


    public ExtraCfg withWatermarkDisableWhitelist(List<String> watermarkDisableWhitelist) {
        this.watermarkDisableWhitelist = watermarkDisableWhitelist;
        return this;
    }

    public ExtraCfg withSegmentDurationInSecond(Float segmentDurationInSecond) {
        this.segmentDurationInSecond = segmentDurationInSecond;
        return this;
    }

    public ExtraCfg withGopLength(Integer gopLength) {
        this.gopLength = gopLength;
        return this;
    }

    public ExtraCfg withSkipBlackFrame(Boolean skipBlackFrame) {
        this.skipBlackFrame = skipBlackFrame;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExtraCfg {\n");

        sb.append("    watermarkDisableWhitelist: ").append(watermarkDisableWhitelist).append("\n");
        sb.append("    segmentDurationInSecond: ").append(segmentDurationInSecond).append("\n");
        sb.append("    gopLength: ").append(gopLength).append("\n");
        sb.append("    skipBlackFrame: ").append(skipBlackFrame).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
