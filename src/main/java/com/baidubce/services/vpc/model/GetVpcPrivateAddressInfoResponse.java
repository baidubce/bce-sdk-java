package com.baidubce.services.vpc.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
}
