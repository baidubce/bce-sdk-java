package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class GetObjectTaggingRequest extends GenericObjectRequest {

    public GetObjectTaggingRequest() {
    }

    public GetObjectTaggingRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    @Override
    public GetObjectTaggingRequest withKey(String key) {
        return null;
    }

    @Override
    public GetObjectTaggingRequest withBucketName(String bucketName) {
        return null;
    }

    @Override
    public GetObjectTaggingRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }
}
