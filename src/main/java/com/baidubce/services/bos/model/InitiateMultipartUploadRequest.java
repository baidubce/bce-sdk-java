/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/**
 * The InitiateMultipartUploadRequest contains the parameters used for the InitiateMultipartUpload method.
 *
 * <p>
 * Required Parameters: BucketName, Key
 */
public class InitiateMultipartUploadRequest extends GenericObjectRequest {

    /**
     * Additional information about the new object being created, such as
     * content type, content encoding, user metadata, etc.
     */
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    /**
     * The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     * 
     */
    private String storageClass;

    /**
     * Constructs a request to initiate a new multipart upload in the specified
     * bucket, stored by the specified key.
     *
     * @param bucketName The name of the bucket in which to create the new multipart
     *     upload, and hence, the eventual object created from the multipart upload.
     * @param key The key by which to store the new multipart upload, and hence,
     *     the eventual object created from the multipart upload.
     */
    public InitiateMultipartUploadRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    @Override
    public InitiateMultipartUploadRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket in which to create the new multipart upload,
     * and hence, the eventual object created from the multipart upload.
     *
     * <p>
     * Returns this updated InitiateMultipartUploadRequest object so that
     * additional method calls can be chained together.
     *
     * @param bucketName The name of the bucket in which to create the new multipart
     *     upload, and hence, the eventual object created from the multipart upload.
     * @return This updated InitiateMultipartUploadRequest object.
     */
    @Override
    public InitiateMultipartUploadRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key by which to store the new multipart upload, and hence, the
     * eventual object created from the multipart upload.
     *
     * <p>
     * Returns this updated InitiateMultipartUploadRequest object so that
     * additional method calls can be chained together.
     *
     * @param key The key by which to store the new multipart upload, and hence,
     *     the eventual object created from the multipart upload.
     * @return This updated InitiateMultipartUploadRequest object.
     */
    @Override
    public InitiateMultipartUploadRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Returns the additional information about the new object being created,
     * such as content type, content encoding, user metadata, etc.
     *
     * @return The additional information about the new object being created,
     *     such as content type, content encoding, user metadata, etc.
     */
    public ObjectMetadata getObjectMetadata() {
        return objectMetadata;
    }

    /**
     * Sets the additional information about the new object being created, such
     * as content type, content encoding, user metadata, etc.
     *
     * @param objectMetadata Additional information about the new object being created,
     *     such as content type, content encoding, user metadata, etc.
     */
    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    /**
     * Sets the additional information about the new object being created, such
     * as content type, content encoding, user metadata, etc.
     *
     * <p>
     * Returns this updated InitiateMultipartUploadRequest object so that
     * additional method calls can be chained together.
     *
     * @param objectMetadata Additional information about the new object being created,
     *     such as content type, content encoding, user metadata, etc.
     * @return This updated InitiateMultipartUploadRequest object.
     */
    public InitiateMultipartUploadRequest withMetadata(ObjectMetadata objectMetadata) {
        this.setObjectMetadata(objectMetadata);
        return this;
    }

    /**
     * Gets the storageClass of the input file which is be uploaded to Bos.
     * 
     * @return storageClass  The StorageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass of the object which is be uploaded to Bos.
     * 
     * @param storageClass  The StorageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    /**
     * Sets the storageClass of the object which is be uploaded to Bos.
     * 
     * @param storageClass  The StorageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     * @return This PutObjectRequest, so that additional method calls can be chained together.
     */
    public InitiateMultipartUploadRequest withStorageClass(String storageClass) {
        this.setStorageClass(storageClass);
        return this;
    }

}
