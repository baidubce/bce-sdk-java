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

import com.baidubce.auth.BceCredentials;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides options for fetching an Baidu Bos object from url.
 * <p>
 * All <code>FetchObjectRequest</code> must specify a sourceUrl and mode, along with a destination bucket and key.
 */
public class FetchObjectRequest extends GenericObjectRequest {
    public static final String MODE_SYNC = "sync";
    public static final String MODE_ASYNC = "async";

    /**
     * The url string of the sourceUrl to be fetched
     */
    private String sourceUrl;

    /**
     * The mode of this fetch job, should be sync or async.
     */
    private String mode;

    /**
     * The storage class is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    private String storageClass;

    /**
     * Constructs a new FetchObjectRequest with only basic options.
     *
     * @param bucketName The name of the Bos bucket to which the new object will be fetched.
     * @param key The destination bucket key under which the new object will be fetched.
     * @param sourceUrl The name of the Bos bucket containing the object to fetch.
     */
    public FetchObjectRequest(String bucketName, String key, String sourceUrl) {
        super(bucketName, key);
        this.setSourceUrl(sourceUrl);
        this.setMode(MODE_SYNC);
    }

    /**
     * Gets the url string of the sourceUrl to be fetched.
     *
     * @return The url string of the sourceUrl to be fetched.
     */
    public String getSourceUrl() {
        return this.sourceUrl;
    }

    /**
     * Sets the url string of the sourceUrl to be fetched.
     *
     * @param sourceUrl The url string of the sourceUrl to be fetched.
     */
    public void setSourceUrl(String sourceUrl) {
        checkNotNull(sourceUrl, "sourceUrl should not be null");
        this.sourceUrl = sourceUrl;
    }

    /**
     * Sets the url string of the sourceUrl to be fetched,
     * and returns this object, enabling additional method calls to be chained together.
     *
     * @param sourceUrl The url string of the sourceUrl to be fetched.
     * @return This <code>FetchObjectRequest</code> instance,
     * enabling additional method calls to be chained together.
     */
    public FetchObjectRequest withSourceUrl(String sourceUrl) {
        this.setSourceUrl(sourceUrl);
        return this;
    }

    /**
     * Gets the mode of this fetching job.
     *
     * @return The mode of this fetching job.
     */
    public String getMode() {
        return this.mode;
    }

    /**
     * Sets the mode of this fetching job.
     *
     * @param mode The mode of this fetching job.
     */
    public void setMode(String mode) {
        checkNotNull(mode, "mode should not be null");
        if (MODE_ASYNC.equals(mode) || MODE_SYNC.equals(mode)) {
            this.mode = mode;
        } else {
            throw new IllegalArgumentException("illegal mode: " + mode);
        }
    }

    /**
     * Sets the mode of this fetching job, enabling additional method calls to be chained together.
     *
     * @param mode The mode of this fetching job.
     * @return This <code>FetchObjectRequest</code> instance, enabling additional method calls to be chained together.
     */
    public FetchObjectRequest withMode(String mode) {
        this.setMode(mode);
        return this;
    }

    @Override
    public FetchObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the destination bucket which will contain the new,
     * fetched object and returns this object, enabling additional method calls
     * to be chained together.
     *
     * @param bucketName The name of the destination bucket which will contain the new object of fetching job.
     * @return This <code>FetchObjectRequest</code>, enabling additional method calls to be  chained together.
     */
    @Override
    public FetchObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the destination bucket key under which the new, fetched object
     * will be stored and returns this object, enabling additional method calls
     * can be chained together.
     *
     * @param key The destination bucket key under which the new, fetched object will be stored.
     * @return This <code>FetchObjectRequest</code>, enabling additional method calls to be chained together.
     */
    @Override
    public FetchObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }


    /**
     * Gets the storageClass of the input file which is to be fetched to Baidu Bos.
     *
     * @return storageClass  The storageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass of the input file which is to be fetched to Baidu Bos.
     *
     * @param storageClass The storageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    /**
     * Sets the storageClass of the input file which is to be fetched to Baidu Bos.
     *
     * @param storageClass The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     * @return This FetchObjectRequest, so that additional method calls can be chained together.
     */
    public FetchObjectRequest withStorageClass(String storageClass) {
        this.setStorageClass(storageClass);
        return this;
    }

}
