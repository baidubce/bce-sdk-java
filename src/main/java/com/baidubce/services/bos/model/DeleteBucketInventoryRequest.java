package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class DeleteBucketInventoryRequest extends GenericBucketRequest {
    private String id;

    public DeleteBucketInventoryRequest() {
    }

    public DeleteBucketInventoryRequest(String bucketName, String id) {
        super(bucketName);
        this.id = id;
    }

    @Override
    public GenericBucketRequest withBucketName(String bucketName) {
        return null;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
