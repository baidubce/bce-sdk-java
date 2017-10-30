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
 *  Container for the parameters of the list operation.
 */
public abstract class ListRequest extends AbstractBceRequest {

    /**
     * The optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     * <p>
     * Together with the marker, specifies the list result which listing should begin.
     * <p>
     * If the marker is not specified, the list result will listing from the first one.
     *
     */
    private String marker;

    /**
     * The optional parameter to specifies the max number of list result to return.
     */
    private int maxKeys = -1;

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
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListRequest with specified marker.
     */
    public ListRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * Returning the optional parameter that specifies the max number of list result to return .
     *
     * @return The optional parameter in the original request to specifies the max number of list result to return .
     */
    public int getMaxKeys() {
        return maxKeys;
    }

    /**
     * Setting the optional parameter to specifies the max number of list result to return
     *
     * @param maxKeys The optional parameter in the original request to specifies the max number of list result to return .
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListRequest with specified maxKeys.
     */
    public ListRequest withMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
        return this;
    }

}