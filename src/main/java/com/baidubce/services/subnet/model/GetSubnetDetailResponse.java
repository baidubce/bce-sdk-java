package com.baidubce.services.subnet.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for GetSubnetRequest.
 */
public class GetSubnetDetailResponse extends AbstractBceResponse {

    /**
     * A subnet detail model contains all of the information of specific subnet.
     */
    private SubnetDetail subnet;

    public SubnetDetail getSubnet() {
        return subnet;
    }

    public void setSubnet(SubnetDetail subnet) {
        this.subnet = subnet;
    }
}
