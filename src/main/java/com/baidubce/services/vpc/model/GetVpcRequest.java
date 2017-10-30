package com.baidubce.services.vpc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for getting vpc
 */
public class GetVpcRequest extends AbstractBceRequest {

    /**
     * The id of vpc.
     */
    private String vpcId;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    /**
     * Configure the vpcId for the request.
     *
     * @param vpcId The id of network.
     * @return GetVpcRequest with specified vpcId.
     */
    public GetVpcRequest withVpcId(String vpcId) {
        this.setVpcId(vpcId);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetVpcRequest with credentials.
     */
    @Override
    public GetVpcRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
