package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUnbindEnterpriseSecurityRequest;

import java.util.Collections;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestUnbindEnterpriseSecurityGroup {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsUnbindEnterpriseSecurityRequest request = new RdsUnbindEnterpriseSecurityRequest();
        request.setInstanceId("rds-xxx");
        request.setEsgUuids(Collections.singletonList("esg-uuid-xxx"));
        AbstractBceResponse response = rdsClient.unbindEnterpriseSecurityGroup(request);
    }
}
