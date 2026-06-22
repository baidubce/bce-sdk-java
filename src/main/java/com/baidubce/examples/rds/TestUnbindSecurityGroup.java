package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSecurityGroupUpdateRequest;

import java.util.Collections;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestUnbindSecurityGroup {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSecurityGroupUpdateRequest request = new RdsSecurityGroupUpdateRequest();
        request.setInstanceId("rds-xxx");
        request.setSecurityGroupIds(Collections.singletonList("g-xxx"));
        AbstractBceResponse response = rdsClient.unbindSecurityGroup(request);
    }
}
