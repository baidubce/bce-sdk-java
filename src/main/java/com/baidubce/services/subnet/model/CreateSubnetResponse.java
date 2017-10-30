package com.baidubce.services.subnet.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for CreateSubnetRequest.
 */
public class CreateSubnetResponse extends AbstractBceResponse {
    /**
     * The id of subnet created.
     */
    private String subnetId;

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    @Override
    public String toString() {
        return "subnetId{"
                + "subnetId=" + subnetId
                + '}';
    }
}
