package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class DeleteObjectTaggingRequest extends GenericObjectRequest {
    public DeleteObjectTaggingRequest() {

    }

    public DeleteObjectTaggingRequest(String bucketName, String key) {
        super(bucketName, key);
    }


    @Override
    public PutObjectTaggingRequest withKey(String key) {
        return null;
    }

    @Override
    public PutObjectTaggingRequest withBucketName(String bucketName) {
        return null;
    }

    @Override
    public PutObjectTaggingRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }
}
