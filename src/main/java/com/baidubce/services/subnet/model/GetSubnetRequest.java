package com.baidubce.services.subnet.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for getting subnet
 */
public class GetSubnetRequest extends AbstractBceRequest {

    /**
     * The id of subnet.
     */
    private String subnetId;

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    /**
     * Configure the subnetId for the request.
     *
     * @param subnetId The id of subnet.
     * @return GetSubnetRequest with specified subnetId.
     */
    public GetSubnetRequest withSubnetId(String subnetId) {
        this.setSubnetId(subnetId);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetSubnetRequest with credentials.
     */
    @Override
    public GetSubnetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
