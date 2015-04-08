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

import static com.google.common.base.Preconditions.checkArgument;

import com.baidubce.auth.BceCredentials;

/**
 * Provides options for downloading an Baidu Bos object.
 *
 * <p>
 * All <code>GetObjectRequests</code> must specify a bucket name and key.
 * Beyond that, requests can also specify: * <p>
 *  <p>
 * <ul>
 *   <li>The range of bytes within the object to download,
 * </ul>
 */
public class GetObjectRequest extends GenericObjectRequest {

    /**
     * Optional member indicating the byte range of data to retrieve
     */
    private long[] range;

    public GetObjectRequest() {
        super();
    }

    /**
     * Constructs a new GetObjectRequest with all the required parameters.
     *
     * @param bucketName The name of the bucket containing the desired object.
     * @param key        The key in the specified bucket under which the object is stored.
     */
    public GetObjectRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    @Override
    public GetObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket containing the object to be downloaded.
     * Returns this GetObjectRequest, enabling additional method calls to be chained together.
     *
     * @param bucketName The name of the bucket containing the object to be downloaded.
     * @return This GetObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public GetObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key under which the object to be downloaded is stored.
     * Returns this GetObjectRequest, enabling additional method calls to be chained together.
     *
     * @param key The key under which the object to be downloaded is stored.
     * @return This GetObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public GetObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /*
     * Optional Request Parameters
     */

    /**
     * Gets the optional inclusive byte range within the desired object that will be downloaded by this request.
     *
     * <p>
     * The range is returned as a two element array, containing the start and end index of the byte range.
     * If no byte range has been specified, the entire object is downloaded and this method returns <code>null</code>.
     *
     * @return A two element array indicating the inclusive start index and end index
     *     within the object being downloaded by this request.
     *     Returns <code>null</code> if no range has been specified, and the whole object is to be downloaded.
     */
    public long[] getRange() {
        return this.range == null ? null : this.range.clone();
    }

    /**
     * Sets the optional inclusive byte range within the desired object that will be downloaded by this request.
     *
     * <p>
     * The first byte in an object has  position 0; as an example, the first ten bytes of an object can be
     * downloaded by specifying a range of 0 to 9.
     *
     * <p>
     * If no byte range is specified, this request downloads the entire object from Baidu Bos.
     *
     * @param start The start of the inclusive byte range to download.
     * @param end The end of the inclusive byte range to download.
     */
    public void setRange(long start, long end) {
        checkArgument(start >= 0, "start should be non-negative.");
        checkArgument(start <= end, "start should not be greater than end");
        this.range = new long[]{start, end};
    }

    /**
     * Sets the optional inclusive byte range within the desired object that will be downloaded by this request.
     * Returns this GetObjectRequest, enabling additional method calls to be chained together.
     *
     * <p>
     * The first byte in an object has position 0; as an example, the first ten bytes of an object can be
     * downloaded by specifying a range of 0 to 9.
     *
     * <p>
     * If no byte range is specified, this request downloads the entire object from Baidu Bos.
     *
     * @param start The start of the inclusive byte range to download.
     * @param end The end of the inclusive byte range to download.
     * @return This GetObjectRequest, enabling additional method calls to be chained together.
     */
    public GetObjectRequest withRange(long start, long end) {
        this.setRange(start, end);
        return this;
    }

}
