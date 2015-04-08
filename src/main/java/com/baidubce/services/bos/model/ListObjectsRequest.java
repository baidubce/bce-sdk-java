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
 * Contains options to return a list of summary information about the objects in the specified
 * bucket. Depending on the request parameters, additional information is returned,
 * such as common prefixes if a delimiter was specified. List
 * results are <i>always</i> returned in lexicographic (alphabetical) order.
 */
public class ListObjectsRequest extends GenericBucketRequest {

    /**
     * Optional parameter restricting the response to keys which begin with the
     * specified prefix. You can use prefixes to separate a bucket into
     * different sets of keys in a way similar to how a file system uses
     * folders.
     */
    private String prefix;

    /**
     * Optional parameter indicating where in the bucket to begin listing. The
     * list will only include keys that occur lexicographically after the
     * marker.
     */
    private String marker;

    /**
     * Optional parameter that causes keys that contain the same string between
     * the prefix and the first occurrence of the delimiter to be rolled up into
     * a single result element. These rolled-up keys
     * are not returned elsewhere in the response. The most commonly used
     * delimiter is "/", which simulates a hierarchical organization similar to
     * a file system directory structure.
     */
    private String delimiter;

    /**
     * Optional parameter indicating the maximum number of keys to include in
     * the response. Baidu Bos might return fewer than this, but will not return
     * more. Even if maxKeys is not specified, Baidu Bos will limit the number
     * of results in the response.
     */
    private int maxKeys = -1;

    /**
     * Constructs a new ListObjectsRequest object and initializes all required and optional object fields.
     *
     * @param bucketName The name of the bucket whose objects are to be listed.
     */
    public ListObjectsRequest(String bucketName) {
        this(bucketName, null);
    }

    /**
     * Constructs a new ListObjectsRequest object and initializes all required and optional object fields.
     *
     * @param bucketName The name of the bucket whose objects are to be listed.
     * @param prefix The prefix restricting what keys will be listed.
     */
    public ListObjectsRequest(String bucketName, String prefix) {
        super(bucketName);
        this.prefix = prefix;
    }

    @Override
    public ListObjectsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the Baidu Bos bucket whose objects are to be listed.
     * Returns this ListObjectsRequest, enabling additional method calls to be chained together.
     *
     * @param bucketName The name of the Baidu Bos bucket whose objects are to be listed.
     * @return This {@link ListObjectsRequest}, enabling additional method calls to be chained together.
     */
    @Override
    public ListObjectsRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Gets the optional prefix parameter and restricts the response to keys
     * that begin with the specified prefix. Use prefixes to separate a
     * bucket into different sets of keys, similar to how a file system organizes files
     * into directories.
     *
     * @return The optional prefix parameter restricting the response to keys
     *     that begin with the specified prefix.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Sets the optional prefix parameter, restricting the response to keys that
     * begin with the specified prefix.
     *
     * @param prefix The optional prefix parameter, restricting the response to keys
     *     that begin with the specified prefix.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Sets the optional prefix parameter restricting the response to keys that
     * begin with the specified prefix.
     * Returns this ListObjectsRequest, enabling additional method calls to be chained together.
     *
     * @param prefix The optional prefix parameter restricting the response to keys
     *     that begin with the specified prefix.
     * @return This ListObjectsRequest, enabling additional method calls to be chained together.
     */
    public ListObjectsRequest withPrefix(String prefix) {
        this.setPrefix(prefix);
        return this;
    }

    /**
     * Gets the optional marker parameter indicating where in the bucket to begin
     * listing. The list will only include keys that occur lexicographically
     * after the marker.
     *
     * @return The optional marker parameter indicating where in the bucket to begin
     *     listing. The list will only include keys that occur lexicographically after the marker.
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * Sets the optional marker parameter indicating where in the bucket to begin
     * listing. The list will only include keys that occur lexicographically
     * after the marker.
     *
     * @param marker The optional marker parameter indicating where in the bucket to begin
     *     listing. The list will only include keys that occur lexicographically after the marker.
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * Sets the optional marker parameter indicating where in the bucket to begin listing.
     * Returns this ListObjectsRequest, enabling additional method calls to be chained together.
     * The list will only include keys that occur lexicographically after the marker.
     *
     * @param marker The optional parameter indicating where in the bucket to begin
     *     listing. The list will only include keys that occur lexicographically after the marker.
     * @return This ListObjectsRequest, enabling additional method calls to be chained together.
     */
    public ListObjectsRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Gets the optional delimiter parameter that causes keys that contain
     * the same string between the prefix and the first occurrence of the
     * delimiter to be combined into a single result element. These combined keys
     * are not returned elsewhere in the response. The most commonly used
     * delimiter is "/", which simulates a hierarchical organization similar to
     * a file system directory structure.
     *
     * @return The optional delimiter parameter that causes keys that contain
     *     the same string between the prefix and the first occurrence of
     *     the delimiter to be combined into a single result element.
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * Sets the optional delimiter parameter that causes keys that contain the
     * same string between the prefix and the first occurrence of the delimiter
     * to be combined into a single result element.
     *
     * @param delimiter The optional delimiter parameter that causes keys that contain
     *     the same string between the prefix and the first occurrence of
     *     the delimiter to be combined into a single result element.
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Sets the optional delimiter parameter that causes keys that contain the
     * same string between the prefix and the first occurrence of the delimiter
     * to be rolled up into a single result element.
     * Returns this ListObjectsRequest, enabling additional method
     * calls to be chained together.
     *
     * @param delimiter The optional delimiter parameter that causes keys that contain
     *     the same string between the prefix and the first occurrence of
     *     the delimiter to be rolled up into a single result element.
     * @return This ListObjectsRequest, enabling additional method calls to be chained together.
     * @see ListObjectsRequest#getDelimiter()
     * @see ListObjectsRequest#setDelimiter(String)
     */
    public ListObjectsRequest withDelimiter(String delimiter) {
        this.setDelimiter(delimiter);
        return this;
    }

    /**
     * Gets the optional <code>maxKeys</code> parameter indicating the maximum number of keys to
     * include in the response. Baidu Bos might return fewer keys than specified, but will
     * never return more. Even if the optional parameter is not specified,
     * Baidu Bos will limit the number of results in the response.
     *
     * @return The optional parameter indicating the maximum number of keys to include in the response.
     */
    public int getMaxKeys() {
        return this.maxKeys;
    }

    /**
     * Sets the optional <code>maxKeys</code> parameter indicating the maximum number of keys to
     * include in the response.
     *
     * @param maxKeys The optional parameter indicating the maximum number of keys to include in the response.
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    /**
     * Sets the optional <code>maxKeys</code> parameter indicating the maximum number of keys to
     * include in the response.
     * Returns this ListObjectsRequest, enabling additional method calls to be chained together.
     *
     * @param maxKeys The optional parameter indicating the maximum number of keys to include in the response.
     * @return This ListObjectsRequest, enabling additional method calls to be chained together.
     */
    public ListObjectsRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

}
