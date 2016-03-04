package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/**
 * Get Bucket Location Request class
 */

public class GetBucketLocationRequest extends GenericBucketRequest {
    /**
     * Constructs a new GetBucketLocationRequest object, ready to retrieve the Location
     * for the specified bucket when executed.
     *
     * @param bucketName The name of the bucket whose Location will be retrieved by this
     *                   request when executed.
     */
    public GetBucketLocationRequest(String bucketName) {
        super(bucketName);
    }

    public GetBucketLocationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public GetBucketLocationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }
}
