package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class MediaDwmDetectRequest extends AbstractBceRequest {

    private String mediaId;
    private String transcodeOutputId;
    private Integer digitalWmAlgVersion;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTranscodeOutputId() {
        return transcodeOutputId;
    }

    public void setTranscodeOutputId(String transcodeOutputId) {
        this.transcodeOutputId = transcodeOutputId;
    }

    public Integer getDigitalWmAlgVersion() {
        return digitalWmAlgVersion;
    }

    public void setDigitalWmAlgVersion(Integer digitalWmAlgVersion) {
        this.digitalWmAlgVersion = digitalWmAlgVersion;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }

    public MediaDwmDetectRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public MediaDwmDetectRequest withTranscodeOutputId(String transcodeOutputId) {
        this.transcodeOutputId = transcodeOutputId;
        return this;
    }

    public MediaDwmDetectRequest withDigitalWmAlgVersion(Integer digitalWmAlgVersion) {
        this.digitalWmAlgVersion = digitalWmAlgVersion;
        return this;
    }

}
