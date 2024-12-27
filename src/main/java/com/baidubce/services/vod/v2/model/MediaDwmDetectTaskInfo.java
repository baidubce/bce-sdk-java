package com.baidubce.services.vod.v2.model;

public class MediaDwmDetectTaskInfo {

    private String mediaId;
    private String transcodeOutputId;
    private String status;
    private String errMsg;
    private Integer digitalWmAlgVersion;
    private String detectedText;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getDigitalWmAlgVersion() {
        return digitalWmAlgVersion;
    }

    public void setDigitalWmAlgVersion(Integer digitalWmAlgVersion) {
        this.digitalWmAlgVersion = digitalWmAlgVersion;
    }

    public String getDetectedText() {
        return detectedText;
    }

    public void setDetectedText(String detectedText) {
        this.detectedText = detectedText;
    }

}
