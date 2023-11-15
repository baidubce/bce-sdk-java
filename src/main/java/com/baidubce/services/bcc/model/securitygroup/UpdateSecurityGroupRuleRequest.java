package com.baidubce.services.bcc.model.securitygroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * The request for updateSecurityGroupRule
 */
public class UpdateSecurityGroupRuleRequest extends AbstractBceRequest {

    /**
     * The id of the security group rule to be updated
     */
    private String securityGroupRuleId;

    /**
     * The remark of the security group rule
     */
    private String remark;

    /**
     * The port range of the security group rule
     */
    private String portRange;

    /**
     * The source ip of the security group rule
     */
    private String sourceIp;

    /**
     * The id of the source security group
     */
    private String sourceGroupId;

    /**
     * The destination ip address
     */
    private String destIp;

    /**
     * The id of the destination security group
     */
    private String destGroupId;

    /**
     * The protocol type
     */
    private String protocol;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return UpdateSecurityGroupRuleRequest with credentials.
     */
    @Override
    public UpdateSecurityGroupRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
