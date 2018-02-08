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

public class ThumbnailCapture {

    private String mode = null;
    
    private Integer startTimeInSecond = null;
    
    private Integer endTimeInSecond = null;
    
    private Integer intervalInSecond = null;

    private Integer frameNumber = null;

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

    public Integer getStartTimeInSecond() {
        return startTimeInSecond;
    }

    public void setStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
    }

    public ThumbnailCapture withStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
        return this;
    }

    public Integer getEndTimeInSecond() {
        return endTimeInSecond;
    }

    public void setEndTimeInSecond(Integer endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond;
    }

    public ThumbnailCapture withEndTimeInSecond(Integer endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond;
        return this;
    }

    public Integer getIntervalInSecond() {
        return intervalInSecond;
    }

    public void setIntervalInSecond(Integer intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
    }
    
    public ThumbnailCapture withIntervalInSecond(Integer intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
        return this;
    }

    public Integer getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
    }

    public ThumbnailCapture withFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
        return this;
    }

    @Override
    public String toString() {
        return "ThumbnailCapture [mode=" + mode + ", startTimeInSecond=" + startTimeInSecond + ", endTimeInSecond="
                + endTimeInSecond + ", intervalInSecond=" + intervalInSecond + ", frameNumber=" + frameNumber + "]";
    }

}
