package com.baidubce.services.media.model;

public class JobOutputInfo {
    /**
     * output job video info
     */
    private JobOutputInfoVideo video = null;

    /**
     * output job audio info
     */
    private JobOutputInfoAudio audio = null;

    private Double bitRateInKBps = null;

    public JobOutputInfoVideo getVideo() { return video; }

    public void setVideo(JobOutputInfoVideo inputVideo) { this.video = inputVideo; }

    public JobOutputInfoAudio getAudio() { return audio; }

    public void setAudio(JobOutputInfoAudio inputAudio) { this.audio = inputAudio; }

    public Double getBitRateInKBps() {
        return bitRateInKBps;
    }

    public void setBitRateInKBps(Double bitRateInKBps) {
        this.bitRateInKBps = bitRateInKBps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobOutputInfo {\n");
        if (video != null) {
            sb.append("    video: ").append(video).append("\n");
        }
        if (audio != null) {
            sb.append("    audio: ").append(audio).append("\n");
        }
        if (bitRateInKBps != null) {
            sb.append("    bitRateInKBps: ").append(bitRateInKBps).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
