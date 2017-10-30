package com.baidubce.services.vpc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * The request for listing vpc
 */
public class ListVpcsRequest extends ListRequest {

    /**
     * The option param determine the result whether only return default vpc.
     * Return default vpc if the value is true
     * Return user-defined vpc if the value is false
     * Return all if the value is null
     */
    private Boolean isDefault;

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * Configure isDefault param for the request.
     *
     * @param isDefault The option param determine the result whether only return default vpc.
     * @return ListVpcsRequest with isDefault
     */
    public ListVpcsRequest withIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListVpcsRequest with specified marker.
     */
    @Override
    public ListVpcsRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListVpcsRequest with specified maxKeys.
     */
    @Override
    public ListVpcsRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListVpcsRequest with credentials.
     */
    @Override
    public ListVpcsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
