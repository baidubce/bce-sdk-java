/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * list base class
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
     * The default value is -1.
     */
    private int maxKeys = -1;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public int getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    public ListRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    public ListRequest withMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
        return this;
    }

    public ListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
