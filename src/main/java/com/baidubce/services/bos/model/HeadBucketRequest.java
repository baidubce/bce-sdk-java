package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class HeadBucketRequest extends GenericBucketRequest {
    /**
     * Constructs a new GetBucketLocationRequest object, ready to retrieve the Location
     * for the specified bucket when executed.
     *
     * @param bucketName The name of the bucket whose Location will be retrieved by this
     *                   request when executed.
     */
    public HeadBucketRequest(String bucketName) {
        super(bucketName);
    }

    public HeadBucketRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public HeadBucketRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }
}