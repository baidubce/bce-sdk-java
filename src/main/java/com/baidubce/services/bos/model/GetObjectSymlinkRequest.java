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

/**
 * Provides options for get an Baidu Bos object symlink.
 * <p>
 * All <code>GetObjectSymlinkRequest</code> must specify a bucket name and key.
 * Beyond that, requests can also specify:
 * <ul>
 * <li>The range of bytes within the object to download,
 * </ul>
 */
public class GetObjectSymlinkRequest  extends GenericObjectRequest {


    public GetObjectSymlinkRequest() { super(); }

    /**
     * Constructs a new GetObjectSymlinkRequest with all the required parameters.
     *
     * @param bucketName The name of the bucket containing the desired object.
     * @param key The symlink key in the specified bucket under which the object is stored.
     */
    public GetObjectSymlinkRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    /**
     * Sets the symlink key.
     * Returns this GetObjectSymlinkRequest, enabling additional method calls to be chained together.
     *
     * @param key The symlink key
     * @return This GetObjectSymlinkRequest, enabling additional method calls to be chained together.
     */
    @Override
    public GetObjectSymlinkRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Sets the name of the bucket containing the symlink object.
     * Returns this GetObjectSymlinkRequest, enabling additional method calls to be chained together.
     *
     * @param bucketName The name of the bucket containing the object to be downloaded.
     * @return This GetObjectSymlinkRequest, enabling additional method calls to be chained together.
     */
    @Override
    public GetObjectSymlinkRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetObjectSymlinkRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
