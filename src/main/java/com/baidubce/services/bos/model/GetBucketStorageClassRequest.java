package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class GetBucketStorageClassRequest extends GenericBucketRequest {

    /**
     * Constructs a new PutBucketStorageClassRequest.
     * After constructing the request,
     * users may optionally specify storageClass.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     */
    public GetBucketStorageClassRequest(String bucketName) {
        this.setBucketName(bucketName);
    }

    /**
     * Sets the name of the bucket where this request will upload a new
     * object to. Returns this object, enabling additional method calls to be
     * chained together.
     *
     * @param bucketName The name of an existing bucket where this request will upload a new object to.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public GetBucketStorageClassRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetBucketStorageClassRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
