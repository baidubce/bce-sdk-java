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
    private String        codec           = null;
    private CodecOptions codecOptions    = null;
    private Integer      bitRateInBps    = null;

    /*
     * The max frames per second,
     * possible values include: 10,15, 23.97, 24, 25, 29.97, 30, 50, 60
     */
    private Float      maxFrameRate    = null;

    private Integer      maxWidthInPixel = null;

    private Integer      maxHeigtInPixel = null;

    private String sizingPolicy    = null;

    private Float        playbackSpeed   = null;

    /**
     * 视频编码信息集合(H.264)
     **/
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

    /**
     * 视频编码的配置选项
     **/
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

    /**
     * 视频目标码率
     **/
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

    /**
     * 目标视频最大帧率
     **/
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

    /**
     * 目标视频最大帧率
     **/
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

    /**
     * 目标视频的最大高度
     **/
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

    /**
     * 尺寸伸缩策略
     **/
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
        sb.append("}\n");
        return sb.toString();
    }
}
