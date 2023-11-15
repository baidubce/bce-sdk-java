package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;


/**
 * GetBucketTrashRequest class
 */
public class GetBucketTrashRequest extends GenericBucketRequest {

    public GetBucketTrashRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public GetBucketTrashRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetBucketTrashRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
