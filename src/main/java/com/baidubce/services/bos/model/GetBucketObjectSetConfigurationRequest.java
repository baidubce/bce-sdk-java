package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/**
 * Request for getting bucket-level ObjectSet configuration.
 */
public class GetBucketObjectSetConfigurationRequest extends GenericBucketRequest {
    public GetBucketObjectSetConfigurationRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public GetBucketObjectSetConfigurationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetBucketObjectSetConfigurationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
