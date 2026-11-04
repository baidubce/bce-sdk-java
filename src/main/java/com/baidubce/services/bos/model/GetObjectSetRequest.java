package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/** Request for getting an ObjectSet. */
public class GetObjectSetRequest extends ObjectSetRequest {
    public GetObjectSetRequest(String bucketName, String objectSetName) {
        super(bucketName, objectSetName);
    }

    @Override
    public GetObjectSetRequest withBucketName(String bucketName) {
        super.withBucketName(bucketName);
        return this;
    }

    @Override
    public GetObjectSetRequest withObjectSetName(String objectSetName) {
        super.withObjectSetName(objectSetName);
        return this;
    }

    @Override
    public GetObjectSetRequest withRequestCredentials(BceCredentials credentials) {
        super.withRequestCredentials(credentials);
        return this;
    }
}
