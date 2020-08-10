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

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * The model which will be used to set capture info in creating thumbnail.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThumbnailPresetCapture {

    /**
     * The mode of thumbnail preset, can auto, manual, split, shot, idl or highlight.
     */
    private String mode = null;
    
    /**
     * The start time to create thumbnails, support in manual or split mode, default 0.0.
     */
    private Double startTimeInSecond = null;
    
    /**
     * The end time to create thumbnails, support in manual or split mode, default is video duration.
     */
    private Double endTimeInSecond = null;
    
    /**
     * The interval between two thumbnails, only support in manual mode, default 1.0.
     */
    private Double intervalInSecond = null;

    /**
     * The minium interval between two thumbnails, only support in split mode, default 1.0.
     */
    private Double minIntervalInSecond = null;

    /**
     * The max frame Number of thumbnsils, only support in split mode.
     */
    private Integer frameNumber = null;

    /**
     * Do not generate when frame is whole black if skipBlackFrame == true;
     */
    private Boolean skipBlackFrame = null;

    /**
     * Highlight mode configuration.
     */
    private HighlightOutputCfg highlightOutputCfg = null;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public ThumbnailPresetCapture withMode(String mode) {
        this.mode = mode;
        return this;
    }

    public Double getStartTimeInSecond() {
        return startTimeInSecond;
    }

    public void setStartTimeInSecond(Double startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond.doubleValue();
    }

    public ThumbnailPresetCapture withStartTimeInSecond(Double startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond.doubleValue();
        return this;
    }

    public Double getEndTimeInSecond() {
        return endTimeInSecond;
    }

    public void setEndTimeInSecond(Double endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond.doubleValue();
    }

    public ThumbnailPresetCapture withEndTimeInSecond(Double endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond.doubleValue();
        return this;
    }

    public Double getIntervalInSecond() {
        return intervalInSecond;
    }

    public void setIntervalInSecond(Double intervalInSecond) {
        this.intervalInSecond = intervalInSecond.doubleValue();
    }

    public ThumbnailPresetCapture withIntervalInSecond(Double intervalInSecond) {
        this.intervalInSecond = intervalInSecond.doubleValue();
        return this;
    }

    public Double getMinIntervalInSecond() {
        return minIntervalInSecond;
    }

    public void setMinIntervalInSecond(Double minIntervalInSecond) {
        this.minIntervalInSecond = minIntervalInSecond.doubleValue();
    }

    public ThumbnailPresetCapture withMinIntervalInSecond(Double minIntervalInSecond) {
        this.minIntervalInSecond = minIntervalInSecond.doubleValue();
        return this;
    }

    public ThumbnailPresetCapture withFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
        return this;
    }

    public ThumbnailPresetCapture withSkipBlackFrame(Boolean skipBlackFrame) {
        this.skipBlackFrame = skipBlackFrame;
        return this;
    }

    public ThumbnailPresetCapture withHighlightOutputCfg(HighlightOutputCfg highlightOutputCfg) {
        this.highlightOutputCfg = highlightOutputCfg;
        return this;
    }

    @Override
    public String toString() {
        return "ThumbnailPresetCapture [mode=" + mode + ", startTimeInSecond=" + startTimeInSecond
                + ", endTimeInSecond=" + endTimeInSecond + ", intervalInSecond=" + intervalInSecond
                + ", frameNumber=" + frameNumber +  ", skipBlackFrame=" + skipBlackFrame + "]";
    }

}
