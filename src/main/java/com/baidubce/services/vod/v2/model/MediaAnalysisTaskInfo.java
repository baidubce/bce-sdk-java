package com.baidubce.services.vod.v2.model;

public class MediaAnalysisTaskInfo {

    private String mediaId;
    private String status;
    private String errMsg;
    private MediaAnalysisTaskInput analysisInput;
    private MediaAnalysisOutputInfo analysisOutput;

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

    public MediaAnalysisTaskInput getAnalysisInput() {
        return analysisInput;
    }

    public void setAnalysisInput(MediaAnalysisTaskInput analysisInput) {
        this.analysisInput = analysisInput;
    }

    public MediaAnalysisOutputInfo getAnalysisOutput() {
        return analysisOutput;
    }

    public void setAnalysisOutput(MediaAnalysisOutputInfo analysisOutput) {
        this.analysisOutput = analysisOutput;
    }

}
