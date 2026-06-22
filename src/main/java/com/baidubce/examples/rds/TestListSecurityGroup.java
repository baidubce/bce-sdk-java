package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsListSecurityGroupRequest;
import com.baidubce.services.rds.model.RdsOpenApiSecurityGroupResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestListSecurityGroup {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsListSecurityGroupRequest request = new RdsListSecurityGroupRequest();
        request.setInstanceId("rds-xxx");
        RdsOpenApiSecurityGroupResponse response = rdsClient.listSecurityGroup(request);
    }
}
