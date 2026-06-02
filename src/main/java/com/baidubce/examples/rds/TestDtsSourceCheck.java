package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsDtsSourceCheckRequest;
import com.baidubce.services.rds.model.RdsDtsSourceCheckResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestDtsSourceCheck {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsDtsSourceCheckRequest request = new RdsDtsSourceCheckRequest();
        request.setInstanceId("rds-b5GOdF2A");
        RdsDtsSourceCheckResponse response = rdsClient.dtsSourceCheck(request);
        print("dtsSourceCheck", response);
    }
}
