/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import com.baidubce.model.AbstractBceResponse;

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
     * If<code>true</code> it means there are more result in the next list page,
     * otherwise it means the list result is last list page.
     */
    private boolean isTruncated;

    /**
     * The next marker to list next page result to list begin,if there is no more result in the next page,
     * this field it will not appear.
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
     * Returning the next marker to list next page result to list begin,if there is no more result in the next page,
     * this field it will not appear.
     * @return The next marker to list next page result to list begin.
     */
    public String getNextMarker() {
        return nextMarker;
    }

    /**
     * Setting the next marker to list next page result to list begin.
     * @param nextMarker The next marker to list next page result to list begin.
     */
    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    /**
     * Returning the optional parameter in the original request to specifies the max number of list result to return .
     * @return The optional parameter in the original request to specifies the max number of list result to return .
     */
    public Integer getMaxKeys() {
        return maxKeys;
    }

    /**
     * Setting the optional parameter in the original request to specifies the max number of list result to return .
     * @param maxKeys The optional parameter in the original request to specifies the max number of list result to return .
     */
    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }
}
