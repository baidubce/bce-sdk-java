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

import com.fasterxml.jackson.annotation.JsonProperty;


public class DeleteDirectoryResponse extends BosResponse {

    /**
     * The name of the Baidu Bos bucket containing the listed objects
     */
    private String bucketName;

    /**
     * The marker to use in order to request the next page of results - only
     * populated if the isTruncated member indicates that this object listing is truncated
     */
    private String nextDeleteMarker;

    /**
     * Indicates if this is a complete listing, or if the caller needs to make
     * additional requests to Baidu Bos to see the full object listing for an Bos bucket
     */
    private boolean isTruncated;

    /**
     * The number of this delete objects request have deleted.
     */
    private int deleteNumber;


    /**
     * Gets the marker to use in the next <code>DeleteObject</code> request in order to delete
     * the next objects of namespace directory.
     * <p>
     *
     * @return The marker to use in the next <code>DeleteObject</code> request in order to see
     * the next objects of namespace directory if this directory deleting is truncated.
     * Returns <code>null</code> if this object listing isn't truncated.
     */
    public String getNextDeleteMarker() {
        return this.nextDeleteMarker;
    }

    /**
     * For internal use only. Sets the marker to use in the next delete objects request
     * in order to see the next objects of namespace directory for a truncated object deleting.
     *
     * @param nextDeleteMarker The marker to use in the next listObjects request in order to
     * see the  next objects of namespace directory for a truncated object deleting.
     */
    @JsonProperty("nextDeleteToken")
    public void setNextDeleteMarker(String nextDeleteMarker) {
        this.nextDeleteMarker = nextDeleteMarker;
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
     * @param bucketName The name of the Baidu Bos bucket containing the objects deleted in this BosObjectDeleting.
     */
    @JsonProperty("name")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Gets whether or not this objects deleting is complete.
     *
     * @return The value <code>true</code> if the object deleting is <b>not complete</b>.
     * Returns the value <code>false</code> if otherwise.
     * When returning <code>true</code>, additional calls to Baidu Bos may be needed in order to
     * obtain more results.
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
     * The value <code>false</code> if otherwise.
     */
    @JsonProperty("isTruncated")
    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    /**
     * Gets the number of this delete objects request have deleted.
     *
     * @return The number of this delete objects request have deleted.
     */
    public int getDeleteNumber() {
        return deleteNumber;
    }

    /**
     * For internal use only. Sets the number of this delete objects request have deleted.
     *
     * @param deleteNumber the number of this delete objects request have deleted.
     */
    @JsonProperty("deleteNumber")
    public void setDeleteNumber(int deleteNumber) {
        this.deleteNumber = deleteNumber;
    }
}