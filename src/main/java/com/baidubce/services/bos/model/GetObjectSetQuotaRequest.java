package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/** Request for getting an ObjectSet quota. */
public class GetObjectSetQuotaRequest extends ObjectSetRequest {
    public GetObjectSetQuotaRequest(String bucketName, String objectSetName) {
        super(bucketName, objectSetName);
    }

    @Override
    public GetObjectSetQuotaRequest withBucketName(String bucketName) {
        super.withBucketName(bucketName);
        return this;
    }

    @Override
    public GetObjectSetQuotaRequest withObjectSetName(String objectSetName) {
        super.withObjectSetName(objectSetName);
        return this;
    }

    @Override
    public GetObjectSetQuotaRequest withRequestCredentials(BceCredentials credentials) {
        super.withRequestCredentials(credentials);
        return this;
    }
}
