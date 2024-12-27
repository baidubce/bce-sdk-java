package com.baidubce.services.vod.v2.model;

public class MediaThumbnailTaskInfo {

    private String mediaId;
    private String status;
    private String errMsg;
    private MediaThumbnailTaskInput thumbnailInput;
    private MediaThumbnailOutputInfo thumbnailOutput;

    public MediaThumbnailOutputInfo getThumbnailOutput() {
        return thumbnailOutput;
    }

    public void setThumbnailOutput(MediaThumbnailOutputInfo thumbnailOutput) {
        this.thumbnailOutput = thumbnailOutput;
    }

    public MediaThumbnailTaskInput getThumbnailInput() {
        return thumbnailInput;
    }

    public void setThumbnailInput(MediaThumbnailTaskInput thumbnailInput) {
        this.thumbnailInput = thumbnailInput;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
