package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class GetNotificationRequest extends GenericBucketRequest {

    public GetNotificationRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public GetNotificationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetNotificationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
