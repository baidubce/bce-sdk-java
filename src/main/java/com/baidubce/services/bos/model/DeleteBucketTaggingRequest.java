package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * DeleteBucketTaggingRequest class
 */
public class DeleteBucketTaggingRequest extends GenericBucketRequest {


    public DeleteBucketTaggingRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public GenericBucketRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
