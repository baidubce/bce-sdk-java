package com.baidubce.services.vod.v2.model;

public class MediaTranscodeOutputAudioCodec {

    private Integer sampleRateInHz;

    private Integer channels;

    public Integer getSampleRateInHz() {
        return sampleRateInHz;
    }

    public void setSampleRateInHz(Integer sampleRateInHz) {
        this.sampleRateInHz = sampleRateInHz;
    }

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }

}
