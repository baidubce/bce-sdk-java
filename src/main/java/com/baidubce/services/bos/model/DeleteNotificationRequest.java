package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class DeleteNotificationRequest extends GenericBucketRequest {
    public DeleteNotificationRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public DeleteNotificationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteNotificationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
