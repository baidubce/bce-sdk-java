package com.baidubce.services.vod.v2.model;

public class MediaTranscodeDigitalWatermark {

    private String digitalWmTextContent;
    private Integer digitalWmAlgVersion;
    private Float digitalWmStrength;

    public String getDigitalWmTextContent() {
        return digitalWmTextContent;
    }

    public void setDigitalWmTextContent(String digitalWmTextContent) {
        this.digitalWmTextContent = digitalWmTextContent;
    }

    public Integer getDigitalWmAlgVersion() {
        return digitalWmAlgVersion;
    }

    public void setDigitalWmAlgVersion(Integer digitalWmAlgVersion) {
        this.digitalWmAlgVersion = digitalWmAlgVersion;
    }

    public Float getDigitalWmStrength() {
        return digitalWmStrength;
    }

    public void setDigitalWmStrength(Float digitalWmStrength) {
        this.digitalWmStrength = digitalWmStrength;
    }

}
