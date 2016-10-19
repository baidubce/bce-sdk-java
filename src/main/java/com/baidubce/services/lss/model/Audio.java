/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.lss.model;

public class Audio {
    private Integer bitRateInBps   = null;

    private Integer sampleRateInHz = null;

    private Integer channels       = null;

    /**
     * 音频目标码率
     **/
    public Integer getBitRateInBps() {
        return bitRateInBps;
    }

    public void setBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
    }

    public Audio withBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
        return this;
    }

    /**
     * 音频采样率
     **/
    public Integer getSampleRateInHz() {
        return sampleRateInHz;
    }

    public void setSampleRateInHz(Integer sampleRateInHz) {
        this.sampleRateInHz = sampleRateInHz;
    }

    public Audio withSampleRateInHz(Integer sampleRateInHz) {
        this.sampleRateInHz = sampleRateInHz;
        return this;
    }

    /**
     * 音频声道数目
     **/
    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }

    public Audio withChannels(Integer channels) {
        this.channels = channels;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Audio {\n");

        sb.append("    bitRateInBps: ").append(bitRateInBps).append("\n");
        sb.append("    sampleRateInHz: ").append(sampleRateInHz).append("\n");
        sb.append("    channels: ").append(channels).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
