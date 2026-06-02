package com.baidubce.services.bcc.model.userop;

import com.baidubce.model.AbstractBceRequest;

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
    public Integer getMaxKeys() {
        return maxKeys;
    }

    /**
     * Setting the optional parameter to specifies the max number of list result to return
     *
     * @param maxKeys The optional parameter in the original request to specifies the max number of list result to return .
     */
    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListRequest with specified maxKeys.
     */
    public ListRequest withMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
        return this;
    }
}
