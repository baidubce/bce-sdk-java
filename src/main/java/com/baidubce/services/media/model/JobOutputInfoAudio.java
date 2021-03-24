package com.baidubce.services.media.model;

public class JobOutputInfoAudio {
    /**
     * output job audio sample rate in Hz
     */
    private Integer sampleRateInHz;

    /**
     * ouput job audio channels
     */
    private Integer channels;

    public Integer getSampleRateInHz() { return sampleRateInHz; }

    public void setSampleRateInHz(Integer inputSampleRate) { this.sampleRateInHz = inputSampleRate; }

    public Integer getChannels() { return channels; }

    public void setChannels(Integer inputChannel) { this.channels = inputChannel; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobOutputInfoAudio {\n");
        sb.append("    sampleRateInHz: ").append(sampleRateInHz).append("\n");
        sb.append("    channels: ").append(channels).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
