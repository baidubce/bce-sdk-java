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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baidubce.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ListPartsResponse contains all the information about the ListParts method.
 */
public class ListPartsResponse extends BosResponse {

    /**
     * The name of the bucket containing the listed parts, as specified in the
     * original request.
     */
    private String bucketName;

    /**
     * The initiated time of the associated multipart upload.
     */
    private Date initiated;

    /**
     * Indicates if the listing is truncated, and additional requests need to be
     * made to get more results.
     */
    private boolean isTruncated;

    /**
     * The key value specified in the original request used to identify which
     * multipart upload contains the parts to list.
     */
    private String key;

    /**
     * The optional max parts value specified in the original request to limit
     * how many parts are listed.
     */
    private Integer maxParts;

    /**
     * If this listing is truncated, this is the part number marker that should
     * be used in the next request to get the next page of results.
     */
    private int nextPartNumberMarker;

    /**
     * The user who owns the associated multipart upload.
     */
    private User owner;

    /**
     * The optional part number marker specified in the original request to
     * specify where in the results to begin listing parts.
     */
    private int partNumberMarker;

    /**
     * The list of parts described in this part listing.
     */
    private List<PartSummary> parts;

    /**
     * The upload ID value specified in the original request used to identify
     * which multipart upload contains the parts to list.
     */
    private String uploadId;

    /**
     * Returns the name of the bucket containing the listed parts, as specified
     * in the original request.
     *
     * @return the name of the bucket containing the listed parts, as specified
     * in the original request.
     */
    public String getBucketName() {
        return this.bucketName;
    }

    /**
     * Sets the name of the bucket containing the listed parts, as specified in
     * the original request.
     *
     * @param bucketName the name of the bucket containing the listed parts, as
     *                   specified in the original request.
     */
    @JsonProperty("bucket")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Returns the key value specified in the original request used to identify
     * which multipart upload contains the parts to list.
     *
     * @return the key value specified in the original request used to identify
     * which multipart upload contains the parts to list.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key value specified in the original request used to identify
     * which multipart upload contains the parts to list.
     *
     * @param key the key value specified in the original request used to
     *            identify which multipart upload contains the parts to list.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the upload ID value specified in the original request used to
     * identify which multipart upload contains the parts to list.
     *
     * @return the upload ID value specified in the original request used to
     * identify which multipart upload contains the parts to list.
     */
    public String getUploadId() {
        return this.uploadId;
    }

    /**
     * Sets the upload ID value specified in the original request used to
     * identify which multipart upload contains the parts to list.
     *
     * @param uploadId the upload ID value specified in the original request used to
     *                 identify which multipart upload contains the parts to list.
     */
    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    /**
     * Returns details on the user who owns the associated multipart
     * upload.
     *
     * @return details on the user who owns the associated multipart
     * upload.
     */
    public User getOwner() {
        return this.owner;
    }

    /**
     * Sets the user who owns the associated multipart upload.
     *
     * @param owner the user who owns the associated multipart upload.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Returns the initiated time of the multipart upload.
     *
     * @return the initiated time of the multipart upload.
     */
    public Date getInitiated() {
        return this.initiated;
    }

    /**
     * Sets the initiated time of the multipart upload.
     *
     * @param initiated the initiated time of the multipart upload.
     */
    public void setInitiated(Date initiated) {
        this.initiated = initiated;
    }

    /**
     * Returns the optional part number marker specified in the original request
     * to specify where in the results to begin listing parts.
     *
     * @return the optional part number marker specified in the original request
     * to specify where in the results to begin listing parts.
     */
    public int getPartNumberMarker() {
        return this.partNumberMarker;
    }

    /**
     * Sets the optional part number marker specified in the original request to
     * specify where in the results to begin listing parts.
     *
     * @param partNumberMarker the optional part number marker specified in the original
     *                         request to specify where in the results to begin listing
     *                         parts.
     */
    public void setPartNumberMarker(int partNumberMarker) {
        this.partNumberMarker = partNumberMarker;
    }

    /**
     * Returns the next part number marker. If this listing is truncated, this
     * is the part number marker that should be used in the next request to get
     * the next page of results.
     *
     * @return The next part number marker, to be used for pagination if this
     * part listing is truncated.
     */
    public int getNextPartNumberMarker() {
        return this.nextPartNumberMarker;
    }

    /**
     * Sets the next part number marker. If this listing is truncated, this is
     * the part number marker that should be used in the next request to get the
     * next page of results.
     *
     * @param nextPartNumberMarker The next part number marker.
     */
    public void setNextPartNumberMarker(int nextPartNumberMarker) {
        this.nextPartNumberMarker = nextPartNumberMarker;
    }

    /**
     * Returns the optional max parts value specified in the original request to
     * limit how many parts are listed.
     *
     * @return The optional max parts value specified in the original request to
     * limit how many parts are listed.
     */
    public Integer getMaxParts() {
        return this.maxParts;
    }

    /**
     * Sets the optional max parts value specified in the original request to
     * limit how many parts are listed.
     *
     * @param maxParts The optional max parts value specified in the original request
     *                 to limit how many parts are listed.
     */
    public void setMaxParts(int maxParts) {
        this.maxParts = maxParts;
    }

    /**
     * Returns whether or not this listing is truncated, and additional requests
     * need to be made to get more results.
     *
     * @return whether or not this listing is truncated, and additional requests
     * need to be made to get more results.
     */
    public boolean isTruncated() {
        return this.isTruncated;
    }

    /**
     * Sets whether or not this listing is truncated, and additional requests
     * need to be made to get more results.
     *
     * @param isTruncated whether or not this listing is truncated, and additional
     *                    requests need to be made to get more results.
     */
    @JsonProperty("isTruncated")
    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    /**
     * Returns the list of parts described in this part listing.
     *
     * @return The list of parts described in this part listing.
     */
    public List<PartSummary> getParts() {
        if (this.parts == null) {
            this.parts = new ArrayList<PartSummary>();
        }
        return this.parts;
    }

    /**
     * Sets the list of parts described in this part listing.
     *
     * @param parts The list of parts described in this part listing.
     */
    public void setParts(List<PartSummary> parts) {
        this.parts = parts;
    }

}
