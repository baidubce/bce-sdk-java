package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/**
 * DeleteBucketMirroringRequest class
 */
public class DeleteBucketMirroringRequest extends GenericBucketRequest {

    /**
     * Constructs a void Constructor for DeleteBucketMirroringRequest
     */
    public DeleteBucketMirroringRequest(String bucketName) {
        super(bucketName);
    }


    @Override
    public DeleteBucketMirroringRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteBucketMirroringRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
