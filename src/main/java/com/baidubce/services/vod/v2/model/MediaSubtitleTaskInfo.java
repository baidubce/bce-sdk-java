package com.baidubce.services.vod.v2.model;

public class MediaSubtitleTaskInfo {

    private String mediaId;
    private String status;
    private String errMsg;
    private MediaSubtitleTaskInput subtitleInput;
    private MediaSubtitleOutputInfo subtitleOutput;

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

    public MediaSubtitleTaskInput getSubtitleInput() {
        return subtitleInput;
    }

    public void setSubtitleInput(MediaSubtitleTaskInput subtitleInput) {
        this.subtitleInput = subtitleInput;
    }

    public MediaSubtitleOutputInfo getSubtitleOutput() {
        return subtitleOutput;
    }

    public void setSubtitleOutput(MediaSubtitleOutputInfo subtitleOutput) {
        this.subtitleOutput = subtitleOutput;
    }

}
