package com.baidubce.services.vpc.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for CreateVpcRequest.
 */
public class CreateVpcResponse extends AbstractBceResponse {
    /**
     * the id of vpc created.
     */
    private String vpcId;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    @Override
    public String toString() {
        return "vpcId{"
                + "vpcId=" + vpcId
                + '}';
    }
}
