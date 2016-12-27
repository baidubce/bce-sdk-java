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
 * Container for the parameters of the ListMultipartUploads operation.
 *
 * <p>
 * Required Parameters: BucketName
 */
public class ListMultipartUploadsRequest extends GenericBucketRequest {

    /**
     * Optional parameter restricting the response to multipart uploads for keys
     * which begin with the specified prefix. You can use prefixes to separate a
     * bucket into different sets of keys in a way similar to how a file system
     * uses folders.
     */
    private String prefix;

    /**
     * The optional key marker indicating where in the results to begin listing.
     *
     * <p>
     * Together with the upload ID marker, specifies the multipart upload after
     * which listing should begin.
     *
     * <p>
     * If the upload ID marker is not specified, only the keys lexicographically
     * greater than the specified key-marker will be included in the list.
     *
     * <p>
     * If the upload ID marker is specified, any multipart uploads for a key
     * equal to the key-marker may also be included, provided those multipart
     * uploads have upload IDs lexicographically greater than the specified
     * marker.
     */
    private String keyMarker;

    /**
     * Optional parameter that causes multipart uploads for keys that contain
     * the same string between the prefix and the first occurrence of the
     * delimiter to be rolled up into a single result element. These rolled-up
     * keys are not returned elsewhere in the response. The most commonly used
     * delimiter is "/", which simulates a hierarchical organization similar to
     * a file system directory structure.
     */
    private String delimiter;

    /**
     * The optional maximum number of uploads to return.
     */
    private int maxUploads = -1;

    /**
     * Constructs a new ListMultipartUploadsRequest to list the multipart uploads from the specified bucket.
     *
     * @param bucketName The name of the bucket containing the uploads to list.
     */
    public ListMultipartUploadsRequest(String bucketName) {
        this(bucketName, null);
    }

    /**
     * Constructs a new ListMultipartUploadsRequest to list the multipart uploads from the specified bucket.
     *
     * @param bucketName The name of the bucket containing the uploads to list.
     * @param prefix The prefix of the object.
     */
    public ListMultipartUploadsRequest(String bucketName, String prefix) {
        super(bucketName);
        this.prefix = prefix;
    }

    @Override
    public ListMultipartUploadsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets The name of the bucket containing the uploads to list, and returns
     * this updated ListMultipartUploadsRequest object so that additional method
     * calls can be chained together.
     *
     * @param bucketName The name of the bucket containing the uploads to list.
     * @return This updated ListMultipartUploadsRequest object.
     */
    @Override
    public ListMultipartUploadsRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Returns the optional maximum number of uploads to return, or null if no
     * maximum number of uploads has been set for this request.
     *
     * @return The optional maximum number of uploads to return.
     */
    public int getMaxUploads() {
        return this.maxUploads;
    }

    /**
     * Sets the optional maximum number of uploads to return.
     *
     * @param maxUploads The maximum number of uploads to return.
     */
    public void setMaxUploads(int maxUploads) {
        this.maxUploads = maxUploads;
    }

    /**
     * Sets the optional maximum number of uploads to return and returns this
     * updated ListMultipartUploadsRequest object so that additional method
     * calls can be chained together.
     *
     * @param maxUploads The optional maximum number of uploads to return.
     * @return This updated ListMultipartUploadsRequest object.
     */
    public ListMultipartUploadsRequest withMaxUploads(int maxUploads) {
        this.maxUploads = maxUploads;
        return this;
    }

    /**
     * Returns the optional key marker indicating where in the results to begin
     * listing.
     *
     * <p>
     * Together with the upload ID marker, specifies the multipart upload after
     * which listing should begin.
     *
     * <p>
     * If the upload ID marker is not specified, only the keys lexicographically
     * greater than the specified key-marker will be included in the list.
     *
     * <p>
     * If the upload ID marker is specified, any multipart uploads for a key
     * equal to the key-marker may also be included, provided those multipart
     * uploads have upload IDs lexicographically greater than the specified
     * marker.
     *
     * @return The optional key marker indicating where in the results to begin
     * listing.
     */
    public String getKeyMarker() {
        return this.keyMarker;
    }

    /**
     * Sets the optional key marker indicating where in the results to begin
     * listing.
     *
     * <p>
     * Together with the upload ID marker, specifies the multipart upload after
     * which listing should begin.
     *
     * <p>
     * If the upload ID marker is not specified, only the keys lexicographically
     * greater than the specified key-marker will be included in the list.
     *
     * <p>
     * If the upload ID marker is specified, any multipart uploads for a key
     * equal to the key-marker may also be included, provided those multipart
     * uploads have upload IDs lexicographically greater than the specified marker.
     *
     * @param keyMarker The optional key marker indicating where in the results to begin listing.
     */
    public void setKeyMarker(String keyMarker) {
        this.keyMarker = keyMarker;
    }

    /**
     * Sets the KeyMarker property for this request.
     *
     * @param keyMarker The value that KeyMarker is set to
     * @return the request with the KeyMarker set
     */
    public ListMultipartUploadsRequest withKeyMarker(String keyMarker) {
        this.keyMarker = keyMarker;
        return this;
    }

    /**
     * Returns the optional delimiter parameter that causes multipart uploads for
     * keys that contain the same string between the prefix and the first
     * occurrence of the delimiter to be combined into a single result element. These
     * combined keys are not returned elsewhere in the response. The most
     * commonly used delimiter is "/", which simulates a hierarchical
     * organization similar to a file system directory structure.
     *
     * @return The optional delimiter parameter that causes multipart uploads
     * for keys that contain the same string between the prefix and the
     * first occurrence of the delimiter to be combined into a single
     * result element.
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * Sets the optional delimiter parameter that causes multipart uploads for
     * keys that contain the same string between the prefix and the first
     * occurrence of the delimiter to be combined into a single result element.
     *
     * @param delimiter The optional delimiter parameter that causes multipart uploads
     *     for keys that contain the same string between the prefix and
     *     the first occurrence of the delimiter to be combined into a single result element.
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Sets the optional delimiter parameter that causes multipart uploads for
     * keys that contain the same string between the prefix and the first
     * occurrence of the delimiter to be rolled up into a single result element.
     * Returns this {@link ListMultipartUploadsRequest}, enabling additional method
     * calls to be chained together.
     *
     * @param delimiter The optional delimiter parameter that causes multipart uploads
     *     for keys that contain the same string between the prefix and
     *     the first occurrence of the delimiter to be rolled up into a single result element.
     * @return This {@link ListMultipartUploadsRequest}, enabling additional
     *     method calls to be chained together.
     */
    public ListMultipartUploadsRequest withDelimiter(String delimiter) {
        this.setDelimiter(delimiter);
        return this;
    }

    /**
     * Returns the optional prefix parameter that restricts the response to
     * multipart uploads for keys that begin with the specified prefix. Use
     * prefixes to separate a bucket into different sets of keys, similar to how
     * a file system organizes files into directories.
     *
     * @return The optional prefix parameter restricting the response to
     *     multipart uploads for keys that begin with the specified prefix.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Sets the optional prefix parameter, restricting the response to multipart
     * uploads for keys that begin with the specified prefix.
     *
     * @param prefix The optional prefix parameter, restricting the response to
     *     multipart uploads for keys that begin with the specified prefix.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Sets the optional prefix parameter restricting the response to multipart
     * uploads for keys that begin with the specified prefix. Returns this
     * {@link ListMultipartUploadsRequest}, enabling additional method calls to
     * be chained together.
     *
     * @param prefix The optional prefix parameter restricting the response to
     *     multipart uploads for keys that begin with the specified prefix.
     * @return This {@link ListMultipartUploadsRequest}, enabling additional
     *     method calls to be chained together.
     */
    public ListMultipartUploadsRequest withPrefix(String prefix) {
        this.setPrefix(prefix);
        return this;
    }

}
