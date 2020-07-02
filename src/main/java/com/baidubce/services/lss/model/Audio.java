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

package com.baidubce.services.lss.model;

import java.io.Serializable;

public class Audio implements Serializable {
    private Integer bitRateInBps = null;

    private Integer sampleRateInHz = null;

    private Integer channels = null;

    /**
     * Returns the audio bit rate
     *
     * @return the audio bit rate
     **/
    public Integer getBitRateInBps() {
        return bitRateInBps;
    }

    /**
     * Sets the audio bit rate.
     *
     * @param bitRateInBps the audio bit rate
     */
    public void setBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
    }

    /**
     * Sets the audio bit rate and returns this object.
     *
     * @param bitRateInBps the audio bit rate
     * @return this object
     */
    public Audio withBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
        return this;
    }

    /**
     * Returns the audio sample rate.
     *
     * @return the audio sample rate
     **/
    public Integer getSampleRateInHz() {
        return sampleRateInHz;
    }

    /**
     * Sets the audio sample rate
     *
     * @param sampleRateInHz the audio sample rate
     */
    public void setSampleRateInHz(Integer sampleRateInHz) {
        this.sampleRateInHz = sampleRateInHz;
    }

    /**
     * Sets the audio sample rate and returns this object
     *
     * @param sampleRateInHz the audio sample rate
     * @return this object
     */
    public Audio withSampleRateInHz(Integer sampleRateInHz) {
        this.sampleRateInHz = sampleRateInHz;
        return this;
    }

    /**
     * Returns the number of audio channels.
     *
     * @return the number of audio channels
     **/
    public Integer getChannels() {
        return channels;
    }

    /**
     * Sets the number of audio channels.
     *
     * @param channels the number of audio channels
     */
    public void setChannels(Integer channels) {
        this.channels = channels;
    }

    /**
     * Sets the number of audio channels and returns this object.
     *
     * @param channels the number of audio channels
     * @return this object
     */
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
