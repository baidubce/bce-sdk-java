package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsPageSecurityResponse;
import com.baidubce.services.rds.model.RdsSecurityGroupPageRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestListSecurityGroupByVpc {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSecurityGroupPageRequest request = new RdsSecurityGroupPageRequest();
        request.setVpcId("vpc-xxx");
        request.setPageNo(1);
        request.setPageSize(10);
        RdsPageSecurityResponse response = rdsClient.listSecurityGroupByVpc(request);
    }
}
