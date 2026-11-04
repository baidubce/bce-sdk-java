package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/** Request for deleting ObjectSet metadata. */
public class DeleteObjectSetRequest extends ObjectSetRequest {
    public DeleteObjectSetRequest(String bucketName, String objectSetName) {
        super(bucketName, objectSetName);
    }

    @Override
    public DeleteObjectSetRequest withBucketName(String bucketName) {
        super.withBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteObjectSetRequest withObjectSetName(String objectSetName) {
        super.withObjectSetName(objectSetName);
        return this;
    }

    @Override
    public DeleteObjectSetRequest withRequestCredentials(BceCredentials credentials) {
        super.withRequestCredentials(credentials);
        return this;
    }
}
