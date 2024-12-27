package com.baidubce.services.vpc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * The request for GetVpcResourceIp.
 */
@Getter
@Setter
public class GetVpcResourceIpRequest extends AbstractBceRequest {
    /**
     * The id of the vpc to be queried
     */
    private String vpcId;

    /**
     * The id of the subnet to be queried
     */
    private String subnetId;

    /**
     * The resource's type to be queried
     */
    private String resourceType;

    /**
     * The page number of list.
     */
    private Integer pageNo = 1;

    /**
     * The page size of list, and valid range id [1,1000].
     */
    private Integer pageSize = 100;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetVpcResourceIpRequest with credentials.
     */
    @Override
    public GetVpcResourceIpRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
