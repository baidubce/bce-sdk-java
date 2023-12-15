package com.baidubce.services.vpc.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The response for GetVpcPrivateAddressInfoRequest.
 */
@Getter
@Setter
public class GetVpcPrivateAddressInfoResponse extends AbstractBceResponse {

    /**
     * List of vpc private ip address info
     */
    private List<VpcPrivateIpAddress> vpcPrivateIpAddresses;

    @Override
    public String toString() {
        return "GetVpcPrivateAddressInfoResponse{" +
                "vpcPrivateIpAddresses=" + vpcPrivateIpAddresses +
                '}';
    }
}
