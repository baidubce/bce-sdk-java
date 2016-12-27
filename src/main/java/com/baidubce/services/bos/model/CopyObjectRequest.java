/*
 * Copyright 2014 Baidu, Inc.
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

import static com.google.common.base.Preconditions.checkNotNull;

import com.baidubce.auth.BceCredentials;

/**
 * Provides options for copying an Baidu Bos object from a source location to a new destination.
 *
 * <p>
 * All <code>CopyObjectRequests</code> must specify a source bucket and key, along with a destination bucket and key.
 */
public class CopyObjectRequest extends GenericObjectRequest {

    /**
     * The name of the bucket containing the object to be copied
     */
    private String sourceBucketName;

    /**
     * The key in the source bucket under which the object to be copied is stored
     */
    private String sourceKey;

    /**
     * Optional field specifying the object metadata for the new object
     */
    private ObjectMetadata newObjectMetadata = null;

    /**
     * Optional ETag value that constrain the copy request to only be executed if the source
     * object's ETag matches the specified ETag value.
     */
    private String eTag;

    /**
     * Optional ETag value that constrain the copy request to only be executed if the source
     * object's ETag does not match the specified ETag value.
     */
    private String noneMatchETagConstraint;

    /**
     * If the value of the unmodifiedSinceConstraint is equal to or later than the actual file
     * modification time, then take the normal file transfer process.
     */
    private String unmodifiedSinceConstraint;

    /**
     * If the value of the modifiedSinceConstraint is less than the actual file modification 
     * time, then take the normal file transfer process.
     */
    private String modifiedSinceConstraint;

    /**
     * The storage class is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    private String storageClass;

    /**
     * Constructs a new CopyObjectRequest with only basic options.
     *
     * @param sourceBucketName The name of the Bos bucket containing the object to copy.
     * @param sourceKey        The source bucket key under which the object to copy is stored.
     * @param bucketName       The name of the Bos bucket to which the new object will be copied.
     * @param key              The destination bucket key under which the new object will be copied.
     */
    public CopyObjectRequest(String sourceBucketName, String sourceKey, String bucketName, String key) {
        super(bucketName, key);
        this.setSourceBucketName(sourceBucketName);
        this.setSourceKey(sourceKey);
    }

    /**
     * Gets the name of the bucket containing the source object to be copied.
     *
     * @return The name of the bucket containing the source object to be copied.
     */
    public String getSourceBucketName() {
        return this.sourceBucketName;
    }

    /**
     * Sets the name of the bucket containing the source object to be copied.
     *
     * @param sourceBucketName The name of the bucket containing the source object to be copied.
     */
    public void setSourceBucketName(String sourceBucketName) {
        checkNotNull(sourceBucketName, "sourceBucketName should not be null");
        this.sourceBucketName = sourceBucketName;
    }

    /**
     * Sets the name of the bucket containing the source object to be copied,
     * and returns this object, enabling additional method calls to be chained together.
     *
     * @param sourceBucketName The name of the bucket containing the source object to be copied.
     * @return This <code>CopyObjectRequest</code> instance,
     *     enabling additional method calls to be chained together.
     */
    public CopyObjectRequest withSourceBucketName(String sourceBucketName) {
        this.setSourceBucketName(sourceBucketName);
        return this;
    }

    /**
     * Gets the source bucket key under which the source object to be copied is stored.
     *
     * @return The source bucket key under which the source object to be copied is stored.
     */
    public String getSourceKey() {
        return this.sourceKey;
    }

    /**
     * Sets the source bucket key under which the source object to be copied is stored.
     *
     * @param sourceKey The source bucket key under which the source object to be copied is stored.
     */
    public void setSourceKey(String sourceKey) {
        checkNotNull(sourceKey, "sourceKey should not be null");
        this.sourceKey = sourceKey;
    }

    /**
     * Sets the key in the source bucket under which the source object to be
     * copied is stored and returns this object, enabling additional method calls
     * to be chained together.
     *
     * @param sourceKey The key in the source bucket under which the source object to be copied is stored.
     * @return This <code>CopyObjectRequest</code> instance, enabling additional method calls to be chained together.
     */
    public CopyObjectRequest withSourceKey(String sourceKey) {
        this.setSourceKey(sourceKey);
        return this;
    }

    @Override
    public CopyObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the destination bucket which will contain the new,
     * copied object and returns this object, enabling additional method calls
     * to be chained together.
     *
     * @param bucketName The name of the destination bucket which will contain the new, copied object.
     * @return This <code>CopyObjectRequest</code>, enabling additional method calls to be  chained together.
     */
    @Override
    public CopyObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the destination bucket key under which the new, copied object
     * will be stored and returns this object, enabling additional method calls
     * can be chained together.
     *
     * @param key The destination bucket key under which the new, copied object will be stored.
     * @return This <code>CopyObjectRequest</code>, enabling additional method calls to be chained together.
     */
    @Override
    public CopyObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Gets the optional object metadata to set for the new, copied object.
     *
     * @return The object metadata to set for the newly copied object.
     * Returns <code>null</code> if no object metadata has been specified.
     */
    public ObjectMetadata getNewObjectMetadata() {
        return this.newObjectMetadata;
    }

    /**
     * Sets the object metadata to use for the new, copied object. By default
     * the object metadata from the source object is copied to the
     * destination object, but when setting object metadata with this method,
     * no metadata from the source object is copied. Instead, the new
     * destination object will have the metadata specified with this call.
     *
     * @param newObjectMetadata The object metadata to use for the newly copied object.
     */
    public void setNewObjectMetadata(ObjectMetadata newObjectMetadata) {
        this.newObjectMetadata = newObjectMetadata;
    }

    /**
     * Sets the object metadata to use for the new, copied object and returns
     * this object, enabling additional method calls to be chained together. By
     * default, the object metadata from the source object will be copied to the
     * destination object, but if callers set object metadata with this method,
     * it will be used instead.
     *
     * @param newObjectMetadata The object metadata to use for the newly copied object.
     * @return This <code>CopyObjectRequest</code>, enabling additional method calls to be chained together.
     */
    public CopyObjectRequest withNewObjectMetadata(ObjectMetadata newObjectMetadata) {
        this.setNewObjectMetadata(newObjectMetadata);
        return this;
    }

    /**
     * Gets the optional ETag that, when present, <b>must</b> be a match for the source object's current ETag
     * in order for the copy object request to be executed.
     *
     * @return The optional ETag that when present must be a match for the source object's current ETag in order for
     * this request to be executed.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the optional ETag that, when present, <b>must</b> be a match for the source object's current ETag
     * in order for the copy object request to be executed.
     *
     * @param eTag The optional ETag that when present must be a match for the source object's current ETag
     *     in order for this request to be executed.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Sets the optional ETag that, when present, <b>must</b> be a match for the source object's current ETag
     * in order for the copy object request to be executed.
     *
     * @param eTag The optional ETag that when present must be a match for the source object's current ETag
     *     in order for this request to be executed.
     * @return This <code>CopyObjectRequest</code>, enabling additional method calls to be chained together.
     */
    public CopyObjectRequest withETag(String eTag) {
        this.setETag(eTag);
        return this;
    }

    /**
     * Gets the storageClass of the input file which is to be copyed to Baidu Bos.
     * 
     * @return storageClass  The storageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass of the input file which is to be copyed to Baidu Bos.
     * 
     * @param storageClass  The storageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    /**
     * Sets the storageClass of the input file which is to be copyed to Baidu Bos.
     *
     * @param storageClass  The StorageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     * @return This CopyObjectRequest, so that additional method calls can be chained together.
     */
    public CopyObjectRequest withStorageClass(String storageClass) {
        this.setStorageClass(storageClass);
        return this;
    }

    /**
     * Gets the the value of the unmodifiedSinceConstraint,if the value is equal to or later than the actual
     * file modification time, then take the normal file transfer process.
     *
     * @return unmodifiedSinceConstraint
     */
    public String getUnmodifiedSinceConstraint() {
        return unmodifiedSinceConstraint;
    }

    /**
     * Sets the the value of the unmodifiedSinceConstraint,if the value is equal to or later than the actual
     * file modification time, then take the normal file transfer process.
     *
     * @param unmodifiedSinceConstraint
     */
    public void setUnmodifiedSinceConstraint(String unmodifiedSinceConstraint) {
        this.unmodifiedSinceConstraint = unmodifiedSinceConstraint;
    }

    /**
     * Sets the the value of the unmodifiedSinceConstraint,if the value is equal to or later than the actual
     * file modification time, then take the normal file transfer process.
     *
     * @param unmodifiedSinceConstraint
     * @return This <code>CopyObjectRequest</code>, enabling additional method calls to be chained together.
     */
    public CopyObjectRequest withUnmodifiedSinceConstraint(String unmodifiedSinceConstraint) {
        this.setUnmodifiedSinceConstraint(unmodifiedSinceConstraint);
        return this;
    }

    /**
     * Gets the the value of the modifiedSinceConstraint,if the value is less than the actual file modification
     * time, then take the normal file transfer process.
     * @return modifiedSinceConstraint
     */
    public String getModifiedSinceConstraint() {
        return modifiedSinceConstraint;
    }

    /**
     * Sets the the value of the modifiedSinceConstraint,if the value is less than the actual file modification
     * time, then take the normal file transfer process.
     * @param modifiedSinceConstraint
     */
    public void setModifiedSinceConstraint(String modifiedSinceConstraint) {
        this.modifiedSinceConstraint = modifiedSinceConstraint;
    }

    /**
     * Sets the the value of the modifiedSinceConstraint,if the value is less than the actual file modification
     * time, then take the normal file transfer process.
     *
     * @param modifiedSinceConstraint
     * @return This <code>CopyObjectRequest</code>, enabling additional method calls to be chained together.
     */
    public CopyObjectRequest withModifiedSinceConstraint(String modifiedSinceConstraint) {
        this.setModifiedSinceConstraint(modifiedSinceConstraint);
        return this;
    }

    /**
     * Gets the optional ETag that, when present, <b>must</b> be not a match for the source object's current 
     * ETag in order for the copy object request to be executed.
     * @return noneMatchETagConstraint
     */
    public String getNoneMatchETagConstraint() {
        return noneMatchETagConstraint;
    }

    /**
     * Sets the optional ETag that, when present, <b>must</b> be not a match for the source object's current 
     * ETag in order for the copy object request to be executed.
     * @param noneMatchETagConstraint
     */
    public void setNoneMatchETagConstraint(String noneMatchETagConstraint) {
        this.noneMatchETagConstraint = noneMatchETagConstraint;
    }

    /**
     * Sets the optional ETag that, when present, <b>must</b> be not a match for the source object's current 
     * ETag in order for the copy object request to be executed.
     *
     * @param noneMatchETagConstraint
     * @return This <code>CopyObjectRequest</code>, enabling additional method calls to be chained together.
     */
    public CopyObjectRequest withNoMatchingETagConstraint(String noneMatchETagConstraint) {
        this.setNoneMatchETagConstraint(noneMatchETagConstraint);
        return this;
    }
}
