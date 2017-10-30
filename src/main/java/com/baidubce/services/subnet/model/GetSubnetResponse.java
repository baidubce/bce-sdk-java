package com.baidubce.services.subnet.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for GetSubnetRequest.
 */
public class GetSubnetResponse extends AbstractBceResponse {

    /**
     * A subnet detail model contains all of the information of specific subnet.
     */
    private Subnet subnet;

    public Subnet getSubnet() {
        return subnet;
    }

    public void setSubnet(Subnet subnet) {
        this.subnet = subnet;
    }
}
