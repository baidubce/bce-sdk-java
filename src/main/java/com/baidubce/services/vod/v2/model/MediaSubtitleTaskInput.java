package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaSubtitleTaskInput {

    private Integer startTimeInSecond;
    private Integer durationTimeInSecond;
    private List<String> formats;
    private String subtitleType;
    private MediaSubtitleOcrRegion ocrRegion;

    public Integer getStartTimeInSecond() {
        return startTimeInSecond;
    }

    public void setStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
    }

    public Integer getDurationTimeInSecond() {
        return durationTimeInSecond;
    }

    public void setDurationTimeInSecond(Integer durationTimeInSecond) {
        this.durationTimeInSecond = durationTimeInSecond;
    }

    public List<String> getFormats() {
        return formats;
    }

    public void setFormats(List<String> formats) {
        this.formats = formats;
    }

    public String getSubtitleType() {
        return subtitleType;
    }

    public void setSubtitleType(String subtitleType) {
        this.subtitleType = subtitleType;
    }

    public MediaSubtitleOcrRegion getOcrRegion() {
        return ocrRegion;
    }

    public void setOcrRegion(MediaSubtitleOcrRegion ocrRegion) {
        this.ocrRegion = ocrRegion;
    }

}
