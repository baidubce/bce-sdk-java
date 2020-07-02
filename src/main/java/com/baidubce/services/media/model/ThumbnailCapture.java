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

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * The model which will be used to set capture info in creating thumbnail job
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThumbnailCapture {

    private String mode = null;
    
    private Integer startTimeInSecond = null;
    
    private Integer endTimeInSecond = null;
    
    private Integer intervalInSecond = null;

    private Integer frameNumber = null;

    private Boolean skipBlackFrame = null;

    private HighlightOutputCfg highlightOutputCfg = null;

    private SpriteOutputCfg spriteOutputCfg = null;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public ThumbnailCapture withMode(String mode) {
        this.mode = mode;
        return this;
    }

    public ThumbnailCapture withStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
        return this;
    }

    public ThumbnailCapture withEndTimeInSecond(Integer endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond;
        return this;
    }

    public ThumbnailCapture withIntervalInSecond(Integer intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
        return this;
    }

    public ThumbnailCapture withFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
        return this;
    }

    public ThumbnailCapture withSkipBlackFrame(Boolean skipBlackFrame) {
        this.skipBlackFrame = skipBlackFrame;
        return this;
    }

    public ThumbnailCapture withHighlightOutputCfg(HighlightOutputCfg highlightOutputCfg) {
        this.highlightOutputCfg = highlightOutputCfg;
        return this;
    }

    public ThumbnailCapture withSpriteOutputCfg(SpriteOutputCfg spriteOutputCfg) {
        this.spriteOutputCfg = spriteOutputCfg;
        return this;
    }


    @Override
    public String toString() {
        return "ThumbnailCapture [mode=" + mode + ", startTimeInSecond=" + startTimeInSecond + ", endTimeInSecond="
                + endTimeInSecond + ", intervalInSecond=" + intervalInSecond
                + ", frameNumber=" + frameNumber +  ", skipBlackFrame=" + skipBlackFrame + "]";
    }

}
