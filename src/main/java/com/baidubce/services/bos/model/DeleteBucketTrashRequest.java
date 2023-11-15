package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class DeleteBucketTrashRequest extends GenericBucketRequest {

    public DeleteBucketTrashRequest(String bucketName){
        super(bucketName);
    }

    @Override
    public DeleteBucketTrashRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteBucketTrashRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
