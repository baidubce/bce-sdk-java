package com.baidubce.services.vod.v2.model;

public class MediaBlackBorderDetectTaskInfo {

    private String mediaId;
    private String status;
    private String errMsg;
    private MediaBlackBorderDetectOutputInfo blackBorderOutput;

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

    public MediaBlackBorderDetectOutputInfo getBlackBorderOutput() {
        return blackBorderOutput;
    }

    public void setBlackBorderOutput(MediaBlackBorderDetectOutputInfo blackBorderOutput) {
        this.blackBorderOutput = blackBorderOutput;
    }

}
