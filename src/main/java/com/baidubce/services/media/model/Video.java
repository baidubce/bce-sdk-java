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

public class Video {
    /**
     * codec, options include h264, h265
     */
    private String        codec           = null;

    /**
     * codec options like profile
     **/
    private CodecOptions codecOptions    = null;

    /**
     * target bitrate in bps
     **/
    private Integer      bitRateInBps    = null;

    /**
     * The max frames per second,
     * possible values include: 10,15, 23.97, 24, 25, 29.97, 30, 50, 60
     **/
    private Float      maxFrameRate    = null;

    /**
     * target max width in pixel
     **/
    private Integer      maxWidthInPixel = null;

    /**
     * target max height in pixel
     **/
    private Integer      maxHeigtInPixel = null;

    /**
     * sizing policy, options include keep、shrinkToFit、stretch
     **/
    private String sizingPolicy    = null;

    /**
     * playback speed
     **/
    private Float        playbackSpeed   = null;

    /**
     * constant rate factor
     **/
    private Integer      crf             = null;

    /**
     * auto adjust resolution if video is portrait and preset's width bigger than its height
     **/
    private Boolean autoAdjustResolution = null;

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public Video withCodec(String codec) {
        this.codec = codec;
        return this;
    }

    public CodecOptions getCodecOptions() {
        return codecOptions;
    }

    public void setCodecOptions(CodecOptions codecOptions) {
        this.codecOptions = codecOptions;
    }

    public Video withCodecOptions(CodecOptions codecOptions) {
        this.codecOptions = codecOptions;
        return this;
    }

    public Integer getBitRateInBps() {
        return bitRateInBps;
    }

    public void setBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
    }

    public Video withBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
        return this;
    }

    public Float getMaxFrameRate() {
        return maxFrameRate;
    }

    public void setMaxFrameRate(Float maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }

    public Video withMaxFrameRate(Float maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
        return this;
    }

    public Integer getMaxWidthInPixel() {
        return maxWidthInPixel;
    }

    public void setMaxWidthInPixel(Integer maxWidth) {
        this.maxWidthInPixel = maxWidth;
    }

    public Video withMaxWidthInPixel(Integer maxWidth) {
        this.maxWidthInPixel = maxWidth;
        return this;
    }

    public Integer getMaxHeightInPixel() {
        return maxHeigtInPixel;
    }

    public void setMaxHeightInPixel(Integer maxHeight) {
        this.maxHeigtInPixel = maxHeight;
    }

    public Video withMaxHeightInPixel(Integer maxHeight) {
        this.maxHeigtInPixel = maxHeight;
        return this;
    }

    public String getSizingPolicy() {
        return sizingPolicy;
    }

    public void setSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
    }

    public Video withSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
        return this;
    }

    public Float getPlaybackSpeed() {
        return playbackSpeed;
    }

    public void setPlaybackSpeed(Float playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
    }

    public Video withPlaybackSpeed(Float playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
        return this;
    }

    public Integer getCrf() {
        return crf;
    }

    public void setCrf(Integer crf) {
        this.crf = crf;
    }

    public Video withCrf(Integer crf) {
        this.crf = crf;
        return this;
    }

    public Boolean getAutoAdjustResolution() {
        return autoAdjustResolution;
    }

    public void setAutoAdjustResolution(Boolean autoAdjustResolution) {
        this.autoAdjustResolution = autoAdjustResolution;
    }

    public Video withAutoAdjustResolution(Boolean autoAdjustResolution) {
        this.autoAdjustResolution = autoAdjustResolution;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Video {\n");

        sb.append("    codec: ").append(codec).append("\n");
        sb.append("    codecOptions: ").append(codecOptions).append("\n");
        sb.append("    bitRateInBps: ").append(bitRateInBps).append("\n");
        sb.append("    maxFrameRate: ").append(maxFrameRate).append("\n");
        sb.append("    maxWidth: ").append(maxWidthInPixel).append("\n");
        sb.append("    maxHeight: ").append(maxHeigtInPixel).append("\n");
        sb.append("    sizingPolicy: ").append(sizingPolicy).append("\n");
        sb.append("    playbackSpeed: ").append(playbackSpeed).append("\n");
        sb.append("    crf: ").append(crf).append("\n");
        sb.append("    autoAdjustResolution: ").append(autoAdjustResolution).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
