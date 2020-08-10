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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The model which will be used to set capture info in creating thumbnail job
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThumbnailCapture {

    /**
     * The mode of thumbnail preset, can auto, manual, split, shot, idl or highlight.
     */
    private String mode = null;
    
    /**
     * The start time to create thumbnails, support in manual or split mode, default 0.0.
     */
    @JsonIgnore
    private Double startTimeInSecond = null;
    
    /**
     * The end time to create thumbnails, support in manual or split mode, default is video duration.
     */
    @JsonIgnore
    private Double endTimeInSecond = null;
    
    /**
     * The interval between two thumbnails, only support in manual mode, default 1.0.
     */
    @JsonIgnore
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

    /**
     * Thumbnail sprite output configuration.
     */
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

    /**
     * Use getStartTimeInSecondDouble instead.
     */
    @Deprecated
    public Integer getStartTimeInSecond() {
        if (startTimeInSecond == null) {
            return null;
        }
        return startTimeInSecond.intValue();
    }

    /**
     * Use setStartTimeInSecondDouble instead.
     */
    @Deprecated
    public void setStartTimeInSecond(Integer startTimeInSecond) {
        if (startTimeInSecond == null) {
            this.startTimeInSecond = null;
        }
        else {
            this.startTimeInSecond = startTimeInSecond.doubleValue();
        }
    }

    /**
     * Use withStartTimeInSecondDouble instead.
     */
    @Deprecated
    public ThumbnailCapture withStartTimeInSecond(Integer startTimeInSecond) {
        this.setStartTimeInSecond(startTimeInSecond);
        return this;
    }

    /**
     * Get the start time to create thumbnails.
     */
    @JsonProperty("startTimeInSecond")
    public Double getStartTimeInSecondDouble() {
        return startTimeInSecond;
    }

    /**
     * Set the start time to create thumbnails, support in manual or split mode, default 0.0.
     */
    public void setStartTimeInSecondDouble(Double startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
    }

    /**
     * Set the start time to create thumbnails, support in manual or split mode, default 0.0.
     */
    public ThumbnailCapture withStartTimeInSecondDouble(Double startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
        return this;
    }

    /**
     * Use getEndTimeInSecondDouble instead.
     */
    @Deprecated
    public Integer getEndTimeInSecond() {
        if (endTimeInSecond == null) {
            return null;
        }
        return endTimeInSecond.intValue();
    }

    /**
     * Use setEndTimeInSecondDouble instead.
     */
    @Deprecated
    public void setEndTimeInSecond(Integer endTimeInSecond) {
        if (endTimeInSecond == null) {
            this.endTimeInSecond = null;
        }
        else {
            this.endTimeInSecond = endTimeInSecond.doubleValue();
        }
    }

    /**
     * Use withEndTimeInSecondDouble instead.
     */
    @Deprecated
    public ThumbnailCapture withEndTimeInSecond(Integer endTimeInSecond) {
        this.setEndTimeInSecond(endTimeInSecond);
        return this;
    }

    /**
     * Get the end time to create thumbnails.
     */
    @JsonProperty("endTimeInSecond")
    public Double getEndTimeInSecondDouble() {
        return endTimeInSecond;
    }

    /**
     * Set the end time to create thumbnails, support in manual or split mode, default is video duration.
     */
    public void setEndTimeInSecondDouble(Double endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond;
    }

    /**
     * Set the end time to create thumbnails, support in manual or split mode, default is video duration.
     */
    public ThumbnailCapture withEndTimeInSecondDouble(Double endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond;
        return this;
    }

    /**
     * Use getIntervalInSecondDouble instead.
     */
    @Deprecated
    public Integer getIntervalInSecond() {
        if (intervalInSecond == null) {
            return null;
        }
        return intervalInSecond.intValue();
    }

    /**
     * Use setIntervalInSecondDouble instead.
     */
    @Deprecated
    public void setIntervalInSecond(Integer intervalInSecond) {
        if (intervalInSecond == null) {
            this.intervalInSecond = null;
        }
        else {
            this.intervalInSecond = intervalInSecond.doubleValue();
        }
    }

    /**
     * Use withIntervalInSecondDouble instead.
     */
    @Deprecated
    public ThumbnailCapture withIntervalInSecond(Integer intervalInSecond) {
        this.setIntervalInSecond(intervalInSecond);
        return this;
    }

    /**
     * Get the interval between two thumbnails.
     */
    @JsonProperty("intervalInSecond")
    public Double getIntervalInSecondDouble() {
        return intervalInSecond;
    }

    /**
     * Set the interval between two thumbnails, only support in manual mode, default 1.0.
     */
    public void setIntervalInSecondDouble(Double intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
    }
    
    /**
     * Set the interval between two thumbnails, only support in manual mode, default 1.0.
     */
    public ThumbnailCapture withIntervalInSecondDouble(Double intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
        return this;
    }

    public ThumbnailCapture withMinIntervalInSecond(Double minIntervalInSecond) {
        this.minIntervalInSecond = minIntervalInSecond;
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

    public SpriteOutputCfg getSpriteOutputCfg() {
        return spriteOutputCfg;
    }

    /**
     * Use ThumbnailTarget.setSpriteOutputCfg instead.
     */
    @Deprecated
    public SpriteOutputCfg setSpriteOutputCfg(SpriteOutputCfg spriteOutputCfg) {
        return this.spriteOutputCfg = spriteOutputCfg;
    }

    /**
     * Use ThumbnailTarget.withSpriteOutputCfg instead.
     */
    @Deprecated
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
