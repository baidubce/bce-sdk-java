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

import java.util.List;

public class Audio {
    /**
     * audio target bitrate in bps
     **/
    private Integer bitRateInBps = null;

    /**
     * audio sample rate in hz
     **/
    private Integer sampleRateInHz = null;

    /**
     * channels of audio
     **/
    private Integer channels = null;

    /**
     * PCM format, options include s16le, can only be set when container is "pcm"
     **/
    private String pcmFormat = null;

    /**
     * settings about audio volume
     **/
    private VolumeAdjust volumeAdjust = null;

    /**
     * setting about audio codec
     */
    private String codec;

    private List<InputIndex> mapping;

    private boolean mixAllTracks;

    private List<Tracks> tracks;

    public static class Tracks {
        private int bitRateInBps;
        private int sampleRateInHz;
        private int channels;
        private String codec;
        private VolumeAdjust volumeAdjust;
        private List<InputIndex> mapping;

        public Tracks() {
        }

        public int getBitRateInBps() {
            return bitRateInBps;
        }

        public void setBitRateInBps(int bitRateInBps) {
            this.bitRateInBps = bitRateInBps;
        }

        public int getSampleRateInHz() {
            return sampleRateInHz;
        }

        public void setSampleRateInHz(int sampleRateInHz) {
            this.sampleRateInHz = sampleRateInHz;
        }

        public int getChannels() {
            return channels;
        }

        public void setChannels(int channels) {
            this.channels = channels;
        }

        public String getCodec() {
            return codec;
        }

        public void setCodec(String codec) {
            this.codec = codec;
        }

        public VolumeAdjust getVolumeAdjust() {
            return volumeAdjust;
        }

        public void setVolumeAdjust(VolumeAdjust volumeAdjust) {
            this.volumeAdjust = volumeAdjust;
        }

        public List<InputIndex> getMapping() {
            return mapping;
        }

        public void setMapping(List<InputIndex> mapping) {
            this.mapping = mapping;
        }
    }

    public static class InputIndex {
        private int inputIndex;
        private String padPolicy;

        public String getPadPolicy() {
            return padPolicy;
        }

        public void setPadPolicy(String padPolicy) {
            this.padPolicy = padPolicy;
        }

        public int getInputIndex() {
            return inputIndex;
        }

        public void setInputIndex(int inputIndex) {
            this.inputIndex = inputIndex;
        }

        public InputIndex() {
        }
    }

    public boolean isMixAllTracks() {
        return mixAllTracks;
    }

    public void setMixAllTracks(boolean mixAllTracks) {
        this.mixAllTracks = mixAllTracks;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

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

    public String getPcmFormat() {
        return pcmFormat;
    }

    public void setPcmFormat(String pcmFormat) {
        this.pcmFormat = pcmFormat;
    }

    public Audio withPcmFormat(String pcmFormat) {
        this.pcmFormat = pcmFormat;
        return this;
    }

    public VolumeAdjust getVolumeAdjust() {
        return volumeAdjust;
    }

    public void setVolumeAdjust(VolumeAdjust volumeAdjust) {
        this.volumeAdjust = volumeAdjust;
    }

    public Audio withVolumeAdjust(VolumeAdjust volumeAdjust) {
        this.volumeAdjust = volumeAdjust;
        return this;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public List<InputIndex> getMapping() {
        return mapping;
    }

    public void setMapping(List<InputIndex> mapping) {
        this.mapping = mapping;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Audio {\n");

        sb.append("    bitRateInBps: ").append(bitRateInBps).append("\n");
        sb.append("    sampleRateInHz: ").append(sampleRateInHz).append("\n");
        sb.append("    channels: ").append(channels).append("\n");
        sb.append("    pcmFormat: ").append(pcmFormat).append("\n");
        sb.append("    volumeAdjust: ").append(volumeAdjust).append("\n");
        sb.append("    codec: ").append(codec).append("\n");
        sb.append("    mapping: ").append(mapping).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
