package com.baidubce.services.vod.v2.model;

public class MediaTranscodeOutputVideoCodec {

    private Integer durationInSeconds;

    private Integer durationInMilliSeconds;

    private Double sizeInKiloByte;

    private Integer widthInPixel;

    private Integer heightInPixel;

    private Integer bitRateInBps;

    private Integer frameRate;

    private Boolean gopAlign = false;

    private Integer mp4MoovSize = -1;

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public Integer getDurationInMilliSeconds() {
        return durationInMilliSeconds;
    }

    public void setDurationInMilliSeconds(Integer durationInMilliSeconds) {
        this.durationInMilliSeconds = durationInMilliSeconds;
    }

    public Double getSizeInKiloByte() {
        return sizeInKiloByte;
    }

    public void setSizeInKiloByte(Double sizeInKiloByte) {
        this.sizeInKiloByte = sizeInKiloByte;
    }

    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }

    public Integer getBitRateInBps() {
        return bitRateInBps;
    }

    public void setBitRateInBps(Integer bitRateInBps) {
        this.bitRateInBps = bitRateInBps;
    }

    public Integer getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Integer frameRate) {
        this.frameRate = frameRate;
    }

    public Boolean getGopAlign() {
        return gopAlign;
    }

    public void setGopAlign(Boolean gopAlign) {
        this.gopAlign = gopAlign;
    }

    public Integer getMp4MoovSize() {
        return mp4MoovSize;
    }

    public void setMp4MoovSize(Integer mp4MoovSize) {
        this.mp4MoovSize = mp4MoovSize;
    }

}
