package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * GetBucketTaggingRequest class
 */
public class GetBucketTaggingRequest extends GenericBucketRequest {


    public GetBucketTaggingRequest(String bucketName) {
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
