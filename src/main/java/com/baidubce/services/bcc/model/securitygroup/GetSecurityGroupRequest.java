package com.baidubce.services.bcc.model.securitygroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for get securityGroup
 */
public class GetSecurityGroupRequest extends AbstractBceRequest {
    /**
     * The id of securitygroup.
     */
    private String securityGroupId;

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    /**
     * Configure securityGroupId for the request.
     *
     * @param securityGroupId The id of securitygroup.
     * @return GetSecurityGroupRequest with securityGroupId.
     */
    public GetSecurityGroupRequest withSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetSecurityGroupRequest with credentials.
     */
    @Override
    public GetSecurityGroupRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
