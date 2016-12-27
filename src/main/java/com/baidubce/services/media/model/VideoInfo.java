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

public class VideoInfo {
    private String  codec            = null;
    private Integer heightInPixel    = null;

    // public enum heightInPixelEnum { };

    private Integer widthInPixel     = null;

    // public enum widthInPixelEnum { };

    // public enum durationInSecondEnum { };

    private Integer bitRateInBps     = null;

    // public enum bitRateInBpsEnum { };

    private Integer frameRate        = null;

    // public enum frameRateEnum { };

    /**
     * 视频文件的编码规格
     **/
    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    /**
     * 视频高度
     **/
    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }

    /**
     * 视频宽度
     **/
    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    /**
     * 视频媒体的码流
     **/
    public Integer getBitRateInBps() {
        return bitRateInBps;
    }

    public void setBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
    }

    /**
     * 视频媒体的帧率
     **/
    public Integer getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Integer frameRate) {
        this.frameRate = frameRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VideoInfo {\n");

        sb.append("    codec: ").append(codec).append("\n");
        sb.append("    heightInPixel: ").append(heightInPixel).append("\n");
        sb.append("    widthInPixel: ").append(widthInPixel).append("\n");
        sb.append("    bitRateInBps: ").append(bitRateInBps).append("\n");
        sb.append("    frameRate: ").append(frameRate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
