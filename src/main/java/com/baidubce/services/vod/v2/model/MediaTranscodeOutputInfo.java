package com.baidubce.services.vod.v2.model;

public class MediaTranscodeOutputInfo {

    private String id;
    private String url;
    private MediaTranscodeOutputMeta meta;
    private MediaTranscodeDigitalWatermark digitalWatermark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MediaTranscodeOutputMeta getMeta() {
        return meta;
    }

    public void setMeta(MediaTranscodeOutputMeta meta) {
        this.meta = meta;
    }

    public MediaTranscodeDigitalWatermark getDigitalWatermark() {
        return digitalWatermark;
    }

    public void setDigitalWatermark(MediaTranscodeDigitalWatermark digitalWatermark) {
        this.digitalWatermark = digitalWatermark;
    }

}
