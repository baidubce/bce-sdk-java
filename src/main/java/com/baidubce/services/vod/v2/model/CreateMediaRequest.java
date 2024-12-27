package com.baidubce.services.vod.v2.model;

public class CreateMediaRequest {

    private MediaUploadRequest mediaUploadRequest;
    private FileUploadRequest fileUploadRequest;
    private MediaCompleteUploadRequest mediaCompleteUploadRequest;

    public MediaUploadRequest getMediaUploadRequest() {
        return mediaUploadRequest;
    }

    public void setMediaUploadRequest(MediaUploadRequest mediaUploadRequest) {
        this.mediaUploadRequest = mediaUploadRequest;
    }

    public FileUploadRequest getFileUploadRequest() {
        return fileUploadRequest;
    }

    public void setFileUploadRequest(FileUploadRequest fileUploadRequest) {
        this.fileUploadRequest = fileUploadRequest;
    }

    public MediaCompleteUploadRequest getMediaCompleteUploadRequest() {
        return mediaCompleteUploadRequest;
    }

    public void setMediaCompleteUploadRequest(MediaCompleteUploadRequest mediaCompleteUploadRequest) {
        this.mediaCompleteUploadRequest = mediaCompleteUploadRequest;
    }

    public CreateMediaRequest withMediaUploadRequest(MediaUploadRequest mediaUploadRequest) {
        this.mediaUploadRequest = mediaUploadRequest;
        return this;
    }

    public CreateMediaRequest withFileUploadRequest(FileUploadRequest fileUploadRequest) {
        this.fileUploadRequest = fileUploadRequest;
        return this;
    }

    public CreateMediaRequest withMediaCompleteUploadRequest(MediaCompleteUploadRequest mediaCompleteUploadRequest) {
        this.mediaCompleteUploadRequest = mediaCompleteUploadRequest;
        return this;
    }

}
