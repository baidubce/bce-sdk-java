/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.vod.model;

/**
 * Provides options for media PlayableUrl by VOD service.
 */
public class Meta {

    /**
     * video file size in bytes
     */
    private Long sizeInBytes;

    /**
     * video duration in seconds
     */
    private Integer durationInSeconds;

    /**
     *  video rate
     */
    private Integer bitRateInBps;

    /**
     * video frame rate
     */
    private float frameRate;

    /**
     * video width in pixels
     */
    private Integer widthInPixel;

    /**
     * video height in pixels
     */
    private Integer heightInPixel;

    /**
     * MP4 file header size
     */
    private Integer mp4MoovSize;

    public Long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public Integer getBitRateInBps() {
        return bitRateInBps;
    }

    public void setBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
    }

    public float getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(float frameRate) {
        this.frameRate = frameRate;
    }

    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }

    public Integer getMp4MoovSize() {
        return mp4MoovSize;
    }

    public void setMp4MoovSize(Integer mp4MoovSize) {
        this.mp4MoovSize = mp4MoovSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{ \n");
        sb.append("   sizeInBytes = ").append(sizeInBytes).append("\n");
        sb.append("   durationInSeconds = ").append(durationInSeconds).append("\n");
        sb.append("   bitRateInBps = ").append(bitRateInBps).append("\n");
        sb.append("   frameRate = ").append(frameRate).append("\n");
        sb.append("   widthInPixel = ").append(widthInPixel).append("\n");
        sb.append("   heightInPixel = ").append(heightInPixel).append("\n");
        sb.append("   mp4MoovSize = ").append(mp4MoovSize).append("\n");
        sb.append("  }\n");
        return sb.toString();
    }
}
