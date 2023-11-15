package com.baidubce.services.vpc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The request for getVpcPrivateIpAddressInfo
 */
@Getter
@Setter
public class GetVpcPrivateIpAddressInfoRequest extends AbstractBceRequest {

    /**
     * The id of the vpc to be queried
     */
    private String vpcId;

    /**
     * The list of private ip addresses to be queried
     */
    private List<String> privateIpAddresses;

    /**
     * The private ip range to be queried
     */
    private String privateIpRange;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetVpcPrivateIpAddressInfoRequest with credentials.
     */
    @Override
    public GetVpcPrivateIpAddressInfoRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
