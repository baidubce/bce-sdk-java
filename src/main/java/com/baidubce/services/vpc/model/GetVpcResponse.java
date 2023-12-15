package com.baidubce.services.vpc.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for GetVpcRequest.
 */
public class GetVpcResponse extends AbstractBceResponse {

    /**
     * A vpc detail model contains all of the information of specific vpc.
     */
    private ShowVpcModel vpc;

    public ShowVpcModel getVpc() {
        return vpc;
    }

    public void setVpc(ShowVpcModel vpc) {
        this.vpc = vpc;
    }

    @Override
    public String toString() {
        return "GetVpcResponse{" +
                "vpc=" + vpc +
                '}';
    }
}
