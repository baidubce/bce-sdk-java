package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MediaCompleteUploadRequest extends AbstractBceRequest {

    private String sessionKey;
    @JsonProperty("isMultipartUpload")
    private boolean isMultipartUpload;
    private List<String> etags;
    private String categoryId;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public boolean isMultipartUpload() {
        return isMultipartUpload;
    }

    public void setMultipartUpload(boolean multipartUpload) {
        isMultipartUpload = multipartUpload;
    }

    public List<String> getEtags() {
        return etags;
    }

    public void setEtags(List<String> etags) {
        this.etags = etags;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaCompleteUploadRequest withSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public MediaCompleteUploadRequest withMultipartUpload(boolean multipartUpload) {
        isMultipartUpload = multipartUpload;
        return this;
    }

    public MediaCompleteUploadRequest withEtags(List<String> etags) {
        this.etags = etags;
        return this;
    }

    public MediaCompleteUploadRequest withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

}
