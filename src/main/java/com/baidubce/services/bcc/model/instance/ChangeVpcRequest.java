package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;
import java.util.List;

@Data
public class ChangeVpcRequest extends AbstractBceRequest {
    private String instanceId;
    private String subnetId;
    private String internalIp;
    private List<String> securityGroupIds;
    private List<String> enterpriseSecurityGroupIds;

    public ChangeVpcRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public ChangeVpcRequest withSubnetId(String subnetId) {
        this.subnetId = subnetId;
        return this;
    }

    public ChangeVpcRequest withInternalIp(String internalIp) {
        this.internalIp = internalIp;
        return this;
    }

    public ChangeVpcRequest withSecurityGroupIds(List<String> securityGroupIds) {
        this.securityGroupIds = securityGroupIds;
        return this;
    }

    public ChangeVpcRequest withEnterpriseSecurityGroupIds(List<String> enterpriseSecurityGroupIds) {
        this.enterpriseSecurityGroupIds = enterpriseSecurityGroupIds;
        return this;
    }

    @Override
    public ChangeVpcRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
