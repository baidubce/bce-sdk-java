package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/**
 * GetBucketMirroringRequest class
 */
public class GetBucketMirroringRequest extends GenericBucketRequest {
    public GetBucketMirroringRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public GetBucketMirroringRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }


    @Override
    public GetBucketMirroringRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
