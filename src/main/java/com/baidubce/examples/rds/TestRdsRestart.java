package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsRestartRequest;


import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestRdsRestart {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsRestartRequest request = new RdsRestartRequest();
        request.setInstanceId("rds-6QCusckC");
        AbstractBceResponse response = rdsClient.rdsRestart(request);
    }
}
