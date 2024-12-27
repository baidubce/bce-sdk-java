package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetBucketVersioningRequest extends GenericBucketRequest {

    public GetBucketVersioningRequest(String bucketName){
        super(bucketName);
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
}
