package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetEnterpriseSecurityRequest;
import com.baidubce.services.rds.model.RdsInstanceEnterpriseSecurityDetail;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestGetEnterpriseSecurity {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetEnterpriseSecurityRequest request = new RdsGetEnterpriseSecurityRequest();
        request.setInstanceId("rds-xxx");
        RdsInstanceEnterpriseSecurityDetail response = rdsClient.getEnterpriseSecurity(request);
    }
}
