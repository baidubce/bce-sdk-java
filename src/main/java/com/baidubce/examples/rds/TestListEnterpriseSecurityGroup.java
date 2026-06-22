package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsEnterpriseSecurityGroupResponse;
import com.baidubce.services.rds.model.RdsListEnterpriseSecurityGroupRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestListEnterpriseSecurityGroup {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsListEnterpriseSecurityGroupRequest request = new RdsListEnterpriseSecurityGroupRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        RdsEnterpriseSecurityGroupResponse response = rdsClient.listEnterpriseSecurityGroup(request);
    }
}
