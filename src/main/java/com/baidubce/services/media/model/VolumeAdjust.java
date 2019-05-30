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

public class VolumeAdjust {
    /**
     * mute audio
     */
    private Boolean mute = null;

    /**
     * audio normalization
     */
    private Boolean norm = null;

    /**
     * volume gain
     */
    private Integer gain = null;

    public Boolean getMute() {
        return mute;
    }

    public void setMute(Boolean mute) {
        this.mute = mute;
    }

    public VolumeAdjust withMute(Boolean mute) {
        this.mute = mute;
        return this;
    }

    public Boolean getNorm() {
        return norm;
    }

    public void setNorm(Boolean norm) {
        this.norm = norm;
    }

    public VolumeAdjust withNorm(Boolean norm) {
        this.norm = norm;
        return this;
    }

    public Integer getGain() {
        return gain;
    }

    public void setGain(Integer gain) {
        this.gain = gain;
    }

    public VolumeAdjust withGain(Integer gain) {
        this.gain = gain;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VolumeAdjust {\n");

        sb.append("    mute: ").append(mute).append("\n");
        sb.append("    norm: ").append(norm).append("\n");
        sb.append("    gain: ").append(gain).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
