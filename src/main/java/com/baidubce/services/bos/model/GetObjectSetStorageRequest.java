package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/** Request for getting ObjectSet storage statistics. */
public class GetObjectSetStorageRequest extends ObjectSetRequest {
    public GetObjectSetStorageRequest(String bucketName, String objectSetName) {
        super(bucketName, objectSetName);
    }

    @Override
    public GetObjectSetStorageRequest withBucketName(String bucketName) {
        super.withBucketName(bucketName);
        return this;
    }

    @Override
    public GetObjectSetStorageRequest withObjectSetName(String objectSetName) {
        super.withObjectSetName(objectSetName);
        return this;
    }

    @Override
    public GetObjectSetStorageRequest withRequestCredentials(BceCredentials credentials) {
        super.withRequestCredentials(credentials);
        return this;
    }
}
