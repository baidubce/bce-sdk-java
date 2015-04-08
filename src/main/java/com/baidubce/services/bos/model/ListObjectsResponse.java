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
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Contains the results of listing the objects in an Baidu Bos bucket.
 * This includes a list of objects describing the objects stored in
 * the bucket, a list of common prefixes if a delimiter was specified in the
 * request, information describing if this is a complete or partial
 * listing, and the original request parameters.
 */
public class ListObjectsResponse extends BosResponse {

    /**
     * The name of the Baidu Bos bucket containing the listed objects
     */
    private String bucketName;

    /**
     * The marker to use in order to request the next page of results - only
     * populated if the isTruncated member indicates that this object listing is truncated
     */
    private String nextMarker;

    /**
     * Indicates if this is a complete listing, or if the caller needs to make
     * additional requests to Baidu Bos to see the full object listing for an Bos bucket
     */
    private boolean isTruncated;

    /**
     * The prefix parameter originally specified by the caller when this object listing was returned
     */
    private String prefix;

    /**
     * A list of summary information describing the objects stored in the bucket.
     */
    private List<BosObjectSummary> contents = Lists.newArrayList();

    /**
     * A list of the common prefixes included in this object listing - common
     * prefixes will only be populated for requests that specified a delimiter.
     */
    @JsonDeserialize(using = CommonPrefixesDeserializer.class)
    private List<String> commonPrefixes;

    /**
     * The marker parameter originally specified by the caller when this object listing was returned
     */
    private String marker;

    /**
     * The maxKeys parameter originally specified by the caller when this object listing was returned
     */
    private int maxKeys;

    /**
     * The delimiter parameter originally specified by the caller when this object listing was returned
     */
    private String delimiter;

    /**
     * Gets the list of object summaries describing the objects stored in the Bos bucket.
     * Listings for large buckets can be truncated for performance reasons.
     *
     * @return A list of the object summaries describing the objects stored in the Bos bucket.
     */
    public List<BosObjectSummary> getContents() {
        return this.contents;
    }

    /**
     * Sets the list of object summaries describing the objects stored in the Bos bucket.
     * Listings for large buckets can be truncated for performance reasons.
     *
     * @param contents A list of the object summaries describing the objects stored in the Bos bucket.
     */
    public void setContents(List<BosObjectSummary> contents) {
        this.contents = contents;
    }

    /**
     * Gets the common prefixes included in this object listing. Common
     * prefixes are only present if a delimiter was specified in the original request.
     *
     * <p>
     * Each common prefix represents a set of keys in the Bos bucket that have
     * been condensed and omitted from the object summary results. This allows
     * applications to organize and browse their keys hierarchically,
     * similar to how a file system organizes files into directories.
     *
     * @return The list of common prefixes included in this object listing,
     *     which might be an empty list if no common prefixes were found.
     */
    public List<String> getCommonPrefixes() {
        return this.commonPrefixes;
    }

    /**
     * For internal use only. Sets the common prefixes for this object listing,
     * representing the key prefixes that were rolled up because of the request's delimiter parameter.
     *
     * @param commonPrefixes The common prefixes for this object listing.
     */
    public void setCommonPrefixes(List<String> commonPrefixes) {
        this.commonPrefixes = commonPrefixes;
    }

    /**
     * Gets the marker to use in the next <code>listObjects</code> request in order to see
     * the next page of results.
     *
     * <p>
     * If an object listing is not truncated, this method will return <code>null</code>. For
     * truncated requests, this value is equal to the greatest lexicographical value of the
     * object keys and common prefixes included in this listing.
     *
     * @return The marker to use in the next <code>listObjects</code> request in order to see
     *     the next page of results if this object listing is truncated.
     *     Returns <code>null</code> if this object listing isn't truncated.
     */
    public String getNextMarker() {
        return this.nextMarker;
    }

    /**
     * For internal use only. Sets the marker to use in the next list objects request
     * in order to see the next page of results for a truncated object listing.
     *
     * @param nextMarker The marker to use in the next listObjects request in order to
     *     see the next page of results for a truncated object listing.
     */
    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    /**
     * Gets the name of the Baidu Bos bucket containing the objects.
     *
     * @return The name of the Baidu Bos bucket containing the objects.
     */
    public String getBucketName() {
        return this.bucketName;
    }

    /**
     * For internal use only. Sets the name of the Baidu Bos
     * bucket containing the objects listed in this BosObjectListing.
     *
     * @param bucketName The name of the Baidu Bos bucket containing the objects listed in this BosObjectListing.
     */
    @JsonProperty("name")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Gets the prefix parameter originally used to request this object listing, or
     * <code>null</code> if no prefix was specified.
     *
     * <p>
     * All objects and common prefixes included in this object listing start with the specified prefix.
     *
     * @return The prefix parameter originally used to request this object
     *     listing. Returns <code>null</code> if no prefix was specified.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * For internal use only.
     * Sets the prefix parameter originally used to request this object listing.
     *
     * @param prefix The prefix parameter originally used to request this object listing.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the marker parameter originally used to request this object listing, or
     * <code>null</code> if no marker was specified.
     * If specified, all objects and common prefixes included in this object listing will occur
     * alphabetically after the specified marker.
     *
     * @return The marker parameter originally used to request this object
     *     listing. Returns <code>null</code> if no marker was specified.
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * For internal use only.
     * Sets the marker parameter originally used to request this object listing.
     *
     * @param marker The marker parameter originally used to request this object listing.
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * Gets the <code>maxKeys</code> parameter originally used to request this object
     * listing, or the default <code>maxKeys</code> value provided by Baidu Bos if the
     * requester didn't specify a value. The <code>maxKeys</code> parameter limits the
     * combined number of objects and common prefixes included in this object
     * listing. An object listing will never contain more objects plus common
     * prefixes than indicated by the <code>maxKeys</code>, but can of course contain less.
     *
     * @return The <code>maxKeys</code parameter originally used to request this object
     *     listing. Returns the default <code>maxKeys</code> value applied by Baidu Bos if
     *     no value was specified.
     */
    public int getMaxKeys() {
        return this.maxKeys;
    }

    /**
     * For internal use only.
     * Sets the <code>maxKeys</code> parameter
     * originally used to request this object listing, or the default maxKeys
     * applied by Baidu Bos if the requester didn't specify a value.
     *
     * @param maxKeys The <code>maxKeys</code> parameter originally used to request this object
     *     listing, or the default <code>maxKeys</code> value applied by Baidu Bos if
     *     the requester didn't specify a value.
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    /**
     * Gets the delimiter parameter originally used to request this object
     * listing, or <code>null</code> if no delimiter specified.
     *
     * <p>
     * The delimiter value allows
     * callers to condense Bos keys into common prefix listings. For example, if
     * a caller specifies a delimiter of "/" (a common used value for
     * delimiter), any keys that contain a common prefix between the start
     * of the key and the first occurrence of "/" will not be included in the
     * list of object summaries. Instead, the common prefixes list will have
     * one entry for the common prefix.
     *
     * @return The delimiter parameter originally used to request this object
     *     listing. Returns <code>null</code> if no delimiter was specified.
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * For internal use only. Sets the delimiter parameter
     * originally used to request this object listing.
     *
     * @param delimiter The delimiter parameter originally used to request this object listing.
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Gets whether or not this object listing is complete.
     *
     * @return The value <code>true</code> if the object listing is <b>not complete</b>.
     *     Returns the value <code>false</code> if otherwise.
     *     When returning <code>true</code>, additional calls to Baidu Bos may be needed in order to
     *     obtain more results.
     */
    public boolean isTruncated() {
        return this.isTruncated;
    }

    /**
     * For internal use only.  Sets the truncated property for
     * this object listing, indicating if this is a complete listing or not and
     * whether the caller needs to make additional calls to Bos to get more
     * object summaries.
     *
     * @param isTruncated The value <code>true</code> if the object listing is <b>not complete</b>.
     *     The value <code>false</code> if otherwise.
     */
    @JsonProperty("isTruncated")
    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

}
