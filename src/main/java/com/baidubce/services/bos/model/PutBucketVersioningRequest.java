package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"bucketName"})
public class PutBucketVersioningRequest extends GenericBucketRequest {

    @JsonAlias({"status"})
    private String status;

    public PutBucketVersioningRequest() {
    }


    public PutBucketVersioningRequest(String bucketName) {
        super(bucketName);
    }


    public PutBucketVersioningRequest(String bucketName, String status) {
        super(bucketName);
        this.status = status;
    }

    @Override
    public GenericBucketRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return null;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
