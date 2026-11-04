package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/** Request for creating an ObjectSet. */
public class PutObjectSetRequest extends ObjectSetRequest {
    public PutObjectSetRequest(String bucketName, String objectSetName) {
        super(bucketName, objectSetName);
    }

    @Override
    public PutObjectSetRequest withBucketName(String bucketName) {
        super.withBucketName(bucketName);
        return this;
    }

    @Override
    public PutObjectSetRequest withObjectSetName(String objectSetName) {
        super.withObjectSetName(objectSetName);
        return this;
    }

    @Override
    public PutObjectSetRequest withRequestCredentials(BceCredentials credentials) {
        super.withRequestCredentials(credentials);
        return this;
    }
}
