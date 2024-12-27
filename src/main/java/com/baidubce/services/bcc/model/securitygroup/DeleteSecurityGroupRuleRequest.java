package com.baidubce.services.bcc.model.securitygroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * The request for delete SecurityGroupRule
 */
public class DeleteSecurityGroupRuleRequest extends AbstractBceRequest {

    /**
     * The id of the security group rule
     */
    private String securityGroupRuleId;

    /**
     * Configure security group rule id for the request.
     *
     * @param securityGroupRuleId The rule id of the security group to be deleted.
     * @return DeleteSecurityGroupRuleRequest with security group rule id.
     */
    public DeleteSecurityGroupRuleRequest withSecurityGroupRuleId(String securityGroupRuleId) {
        this.securityGroupRuleId = securityGroupRuleId;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return DeleteSecurityGroupRuleRequest with credentials.
     */
    @Override
    public DeleteSecurityGroupRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
