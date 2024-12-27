package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsClusterStatusCheckRequest;
import com.baidubce.services.rds.model.RdsClusterStatusCheckResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestClusterStatusCheck {
    public static void main(String[] args) {
        RdsClient rdsClient= createRdsClient();

        RdsClusterStatusCheckRequest request = new RdsClusterStatusCheckRequest();
        request.setInstanceId("rds-k1dffnQn");
        RdsClusterStatusCheckResponse response = rdsClient.clusterStatusCheck(request);
        print("clusterStatusCheck", response);
    }
}
