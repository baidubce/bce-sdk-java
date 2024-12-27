package com.baidubce.services.vod.v2.model;

public class MediaRegulationTaskInfo {

    private String mediaId;
    private String status;
    private String errMsg;
    private MediaRegulationTaskInput regulationInput;
    private MediaRegulationOutputInfo regulationOutput;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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

    public MediaRegulationTaskInput getRegulationInput() {
        return regulationInput;
    }

    public void setRegulationInput(MediaRegulationTaskInput regulationInput) {
        this.regulationInput = regulationInput;
    }

    public MediaRegulationOutputInfo getRegulationOutput() {
        return regulationOutput;
    }

    public void setRegulationOutput(MediaRegulationOutputInfo regulationOutput) {
        this.regulationOutput = regulationOutput;
    }

}
