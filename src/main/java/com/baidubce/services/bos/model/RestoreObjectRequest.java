/*
 * Copyright 2019 Baidu, Inc. All Rights Reserved.
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
 * Restore object request for restoring archive object
 */
public class RestoreObjectRequest extends GenericObjectRequest {

    /**
     * restore days kept after restoring archive object
     */
    private Integer restoreDays;
    /**
     * restore tier used when restoring archive object
     */
    private String restoreTier;

    /**
     * Sets the bucket and key of RestoreObjectRequest
     *
     * @param bucketName The name of the bucket in which to restore object
     * @param key        The key by which to be restored
     */
    public RestoreObjectRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    /**
     * Sets the key by which to restore, and hence, the
     * eventual object restored from RestoreObjectRequest.
     *
     * <p>
     * Returns this updated RestoreObjectRequest so that
     * additional method calls can be chained together.
     *
     * @param key The key by which to be restored
     * @return This updated RestoreObjectRequest object.
     */
    @Override
    public RestoreObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Sets the name of the bucket in which to restore object,
     * and hence, the eventual object restored from RestoreObjectRequest.
     *
     * <p>
     * Returns this updated RestoreObjectRequest so that
     * additional method calls can be chained together.
     *
     * @param bucketName The name of the bucket in which to restore object
     * @return This updated RestoreObjectRequest object.
     */
    @Override
    public RestoreObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the optional credentials to use for this request, overriding the default credentials set at the client
     * level.
     *
     * @param credentials The optional BCE security credentials to use for this request, overriding the default
     *                    credentials set at the client level.
     */
    @Override
    public RestoreObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Gets the restore days for this request
     *
     * @return restoreDays Days kept after restoring object
     */
    public Integer getRestoreDays() {
        return restoreDays;
    }

    /**
     * Sets the restore days for this request
     *
     * @param restoreDays Days kept after restoring object
     */
    public void setRestoreDays(Integer restoreDays) {
        this.restoreDays = restoreDays;
    }

    /**
     * Sets the restore days for this request
     *
     * @param restoreDays Days kept after restoring object
     * @return This RestoreObjectRequest, so that additional method calls to be chained together.
     */
    public RestoreObjectRequest withRestoreDays(Integer restoreDays) {
        this.setRestoreDays(restoreDays);
        return this;
    }

    /**
     * Get the restore tier used in this request
     *
     * @return restoreTier The restore tier used in restoring object
     */
    public String getRestoreTier() {
        return restoreTier;
    }

    /**
     * Set the restore tier used in this request
     *
     * @param restoreTier The restore tier used in restoring object
     */
    public void setRestoreTier(String restoreTier) {
        this.restoreTier = restoreTier;
    }

    /**
     * Set the restore tier used in this request
     *
     * @param restoreTier The restore tier used in restoring object
     * @return This RestoreObjectRequest, so that additional method calls to be chained together.
     */
    public RestoreObjectRequest withRestoreTier(String restoreTier) {
        this.setRestoreTier(restoreTier);
        return this;
    }
}
