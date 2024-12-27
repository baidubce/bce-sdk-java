package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MediaUploadRequest extends AbstractBceRequest {

    private String name;
    private String container;
    @JsonProperty("isMultipartUpload")
    private boolean isMultipartUpload = false;
    private Integer numParts = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public boolean isMultipartUpload() {
        return isMultipartUpload;
    }

    public void setMultipartUpload(boolean multipartUpload) {
        isMultipartUpload = multipartUpload;
    }

    public Integer getNumParts() {
        return numParts;
    }

    public void setNumParts(Integer numParts) {
        this.numParts = numParts;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaUploadRequest withName(String name) {
        this.name = name;
        return this;
    }

    public MediaUploadRequest withContainer(String container) {
        this.container = container;
        return this;
    }

    public MediaUploadRequest withMultipartUpload(boolean multipartUpload) {
        this.isMultipartUpload = multipartUpload;
        return this;
    }

    public MediaUploadRequest withNumParts(Integer numParts) {
        this.numParts = numParts;
        return this;
    }

}
