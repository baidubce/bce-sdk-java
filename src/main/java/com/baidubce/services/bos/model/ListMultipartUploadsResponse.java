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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

/**
 * The ListMultipartUploadsResponse contains all the information about the
 * ListMultipartUploads method.
 */
public class ListMultipartUploadsResponse extends BosResponse {

    /**
     * The name of the bucket containing the listed multipart uploads, as
     * specified in the original request.
     */
    private String bucketName;

    /**
     * The optional key marker specified in the original request to specify
     * where in the results to begin listing multipart uploads.
     */
    private String keyMarker;

    /**
     * The optional prefix specified in the original request to limit the
     * returned multipart uploads to those for keys that match this prefix.
     */
    private String prefix;

    /**
     * The optional delimiter specified in the original request to control how
     * multipart uploads for keys with common prefixes are condensed.
     */
    private String delimiter;

    /**
     * The optional maximum number of uploads to be listed, as specified in the
     * original request.
     */
    private int maxUploads;

    /**
     * Indicates if the listing is truncated, and additional requests need to be
     * made to get more results.
     */
    private boolean isTruncated;

    /**
     * If this listing is truncated, this is the next key marker that should be
     * used in the next request to get the next page of results.
     */
    private String nextKeyMarker;

    /** The list of multipart uploads. */
    @JsonProperty("uploads")
    private List<MultipartUploadSummary> multipartUploads;

    /**
     * A list of the common prefixes included in this multipart upload listing - common
     * prefixes will only be populated for requests that specified a delimiter, and indicate
     * additional key prefixes that contain more multipart uploads that have not been included
     * in this listing.
     */
    @JsonDeserialize(using = CommonPrefixesDeserializer.class)
    private List<String> commonPrefixes;

    /**
     * Returns the name of the bucket containing the listed multipart uploads,
     * as specified in the original request.
     *
     * @return The name of the bucket containing the listed multipart uploads,
     *     as specified in the original request.
     */
    public String getBucketName() {
        return this.bucketName;
    }

    /**
     * Sets the name of the bucket containing the listed multipart uploads, as
     * specified in the original request.
     *
     * @param bucketName The name of the bucket containing the listed multipart
     *     uploads, as specified in the original request.
     */
    @JsonProperty("bucket")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Returns the optional key marker specified in the original request to
     * specify where in the results to begin listing multipart uploads.
     *
     * @return The optional key marker specified in the original request to
     *     specify where in the results to begin listing multipart uploads.
     */
    public String getKeyMarker() {
        return this.keyMarker;
    }

    /**
     * Sets the optional key marker specified in the original request to specify
     * where in the results to begin listing multipart uploads.
     *
     * @param keyMarker The optional key marker specified in the original request to
     *     specify where in the results to begin listing multipart uploads.
     */
    public void setKeyMarker(String keyMarker) {
        this.keyMarker = keyMarker;
    }

    /**
     * Returns the next key marker that should be used in the next request to
     * get the next page of results. This value is only valid if
     * isTruncated indicates this listing is truncated.
     *
     * @return the next key marker that should be used in the next request to
     *     get the next page of results. This value is only valid if
     *     isTruncated() indicates this listing is truncated.
     */
    public String getNextKeyMarker() {
        return this.nextKeyMarker;
    }

    /**
     * Sets the next key marker that should be used in the next request to get
     * the next page of results.
     *
     * @param nextKeyMarker the next key marker that should be used in the next request to
     *     get the next page of results.
     */
    public void setNextKeyMarker(String nextKeyMarker) {
        this.nextKeyMarker = nextKeyMarker;
    }

    /**
     * Returns the optional maximum number of uploads to be listed, as specified
     * in the original request.
     *
     * @return The optional maximum number of uploads to be listed, as specified in the original request.
     */
    public int getMaxUploads() {
        return this.maxUploads;
    }

    /**
     * Sets the optional maximum number of uploads to be listed, as specified in
     * the original request.
     *
     * @param maxUploads The optional maximum number of uploads to be listed, as
     *     specified in the original request.
     */
    public void setMaxUploads(int maxUploads) {
        this.maxUploads = maxUploads;
    }

    /**
     * Returns true if the listing is truncated, and additional requests need to
     * be made to get more results.
     *
     * @return true if the listing is truncated, and additional requests need to
     *     be made to get more results.
     */
    public boolean isTruncated() {
        return this.isTruncated;
    }

    /**
     * Sets whether this listing is truncated, and additional requests need to
     * be made to get more results.
     *
     * @param isTruncated true if the listing is truncated, and additional requests need
     *     to be made to get more results.
     */
    @JsonProperty("isTruncated")
    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    /**
     * Returns the list of multipart uploads.
     *
     * @return The list of multipart uploads.
     */
    public List<MultipartUploadSummary> getMultipartUploads() {
        if (this.multipartUploads == null) {
            this.multipartUploads = new ArrayList<MultipartUploadSummary>();
        }
        return this.multipartUploads;
    }

    /**
     * Sets the list of multipart uploads.
     *
     * @param multipartUploads The list of multipart uploads.
     */
    public void setMultipartUploads(List<MultipartUploadSummary> multipartUploads) {
        this.multipartUploads = multipartUploads;
    }

    /**
     * Returns the common prefixes included in this multipart upload listing. Common
     * prefixes are only present if a delimiter was specified in the original
     * request.
     *
     * <p>
     * Each common prefix represents a set of keys in the Bos bucket that have
     * been condensed and omitted from the multipart upload listing results. This allows
     * applications to organize and browse their multipart uploads hierarchically,
     * similar to how a file system organizes files into directories.
     *
     * @return The list of common prefixes included in this multipart object listing,
     *     which might be an empty list if no common prefixes were found.
     */
    public List<String> getCommonPrefixes() {
        return this.commonPrefixes;
    }

    /**
     * For internal use only. Sets the common prefixes for this multipart upload
     * listing, representing the uploads for key prefixes that were rolled up
     * because of the request's delimiter parameter.
     *
     * @param commonPrefixes The common prefixes for this multipart upload listing.
     */
    public void setCommonPrefixes(List<String> commonPrefixes) {
        this.commonPrefixes = commonPrefixes;
    }

    /**
     * Returns the prefix parameter originally used to request this multipart
     * upload listing, or <code>null</code> if no prefix was specified. All
     * objects and common prefixes included in this multipart upload listing
     * start with the specified prefix.
     *
     * @return The prefix parameter originally used to request this multipart upload
     *     listing. Returns <code>null</code> if no prefix was specified.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * For internal use only. Sets the prefix parameter originally used to
     * request this multipart upload listing.
     *
     * @param prefix The prefix parameter originally used to request this multipart upload listing.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the delimiter parameter originally used to request this multipart
     * upload listing, or <code>null</code> if no delimiter specified.
     *
     * <p>
     * The delimiter value allows callers to condense multipart uploads for keys
     * with common prefixes. For example, if a caller specifies a delimiter of
     * "/" (a commonly used value for delimiter), any multipart uploads for keys
     * that contain a common prefix between the start of the key and the first
     * occurrence of "/" will not be included in the list of multipart uploads.
     * Instead, the common prefixes list will have one entry for the common
     * prefix.
     *
     * @return The delimiter parameter originally used to request this multipart
     *     upload listing. Returns <code>null</code> if no delimiter was specified.
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * For internal use only. Sets the delimiter parameter originally used to
     * request this multipart upload listing.
     *
     * @param delimiter The delimiter parameter originally used to request this multipart upload listing.
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
