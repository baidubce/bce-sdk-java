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

public class AudioInfo {
    private String  codec          = null;
    private Integer channels       = null;

    // public enum channelsEnum { };

    private Integer sampleRateInHz = null;

    // public enum sampleRateInHzEnum { };

    private Integer bitRateInBps   = null;

    // public enum bitRateInBpsEnum { };

    /**
     * 音频文件的编码规格
     **/
    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    /**
     * 音频文件的声道信息
     **/
    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }

    /**
     * 音频文件的采样率
     **/
    public Integer getSampleRateInHz() {
        return sampleRateInHz;
    }

    public void setSampleRateInHz(Integer sampleRateInHz) {
        this.sampleRateInHz = sampleRateInHz;
    }

    /**
     * 音频文件的码率
     **/
    public Integer getBitRateInBps() {
        return bitRateInBps;
    }

    public void setBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AudioInfo {\n");

        sb.append("    codec: ").append(codec).append("\n");
        sb.append("    channels: ").append(channels).append("\n");
        sb.append("    sampleRateInHz: ").append(sampleRateInHz).append("\n");
        sb.append("    bitRateInBps: ").append(bitRateInBps).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
