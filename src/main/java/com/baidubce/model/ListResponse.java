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
package com.baidubce.model;

/**
 * The base response contains the base information about list operation.
 */
public abstract class ListResponse extends AbstractBceResponse {

    /**
     * The optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     */
    private String marker;

    /**
     * Indicates if the listing is truncated, and additional requests need to be
     * made to get more results.
     */
    private boolean isTruncated;

    /**
     * If this listing is truncated, this is the next marker that should be
     * used in the next request to get the next page of results.
     */
    private String nextMarker;

    /**
     * The optional parameter in the original request to specifies the max number of list result to return .
     */
    private Integer maxKeys;

    /**
     * Returning the optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     *
     * @return The optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     */
    public String getMarker() {
        return marker;
    }

    /**
     * Setting the optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * Returning <code>false</code> to indicate that there is not more result in the next page,
     * otherwise returning <code>true</code> meaning there is more result in next page.
     *
     * @return Returning <code>false</code> to indicate that there is not more result in the next page,
     * otherwise returning <code>true</code> meaning there is more result in next page.
     */
    public boolean getIsTruncated() {
        return isTruncated;
    }

    /**
     * Setting the boolean value to indicate that there is not more result in the next page.
     *
     * Setting <code>false</code> to indicate that there is not more result in the next page,
     * Setting <code>true</code> meaning there is more result in next page.
     *
     * @param isTruncated The boolean value to indicate that there is not more result in the next page.
     */
    public void setIsTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    /**
     * Returns the next marker that should be used in the next request to
     * get the next page of results. This value is only valid if
     * isTruncated indicates this listing is truncated.
     *
     * @return the next key marker that should be used in the next request to
     *     get the next page of results. This value is only valid if
     *     isTruncated() indicates this listing is truncated.
     */
    public String getNextMarker() {
        return nextMarker;
    }

    /**
     * Sets the next marker that should be used in the next request to get
     * the next page of results.
     *
     * @param nextMarker The next marker that should be used in the next request to
     *     get the next page of results.
     */
    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    /**
     * Returning the optional parameter in the original request to specifies the max number of list result to return .
     *
     * @return The optional parameter in the original request to specifies the max number of list result to return .
     */
    public Integer getMaxKeys() {
        return maxKeys;
    }

    /**
     * Setting the optional parameter in the original request to specifies the max number of list result to return
     *
     * @param maxKeys The optional parameter in the original request to specifies the max number of list result to return .
     */
    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

}
