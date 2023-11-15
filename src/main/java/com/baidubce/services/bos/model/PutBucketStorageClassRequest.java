package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class PutBucketStorageClassRequest extends GenericBucketRequest {
    /**
     * The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    private String storageClass;

    /**
     * Constructs a new PutBucketStorageClassRequest.
     * After constructing the request,
     * users may optionally specify storageClass.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param storageClass  The key is an identification
     *                      that distinguish between infrequent access bos and standard bos.
     */
    public PutBucketStorageClassRequest(String bucketName, String storageClass) {
        this.setBucketName(bucketName);
        this.setStorageClass(storageClass);
    }

    /**
     * Gets the storageClass of the input file which is be uploaded to Baidu Bos.
     *
     * @return storageClass  The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass of the input file which is be uploaded to Baidu Bos.
     *
     * @param storageClass  The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
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
    public PutBucketStorageClassRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public PutBucketStorageClassRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
