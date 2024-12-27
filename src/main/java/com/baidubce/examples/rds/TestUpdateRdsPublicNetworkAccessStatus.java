package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsNetworkStatusRequest;


import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateRdsPublicNetworkAccessStatus {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsNetworkStatusRequest request = new RdsNetworkStatusRequest();
        request.setInstanceId("rds-17JzX6Wm");
        request.setPublicAccess(false);
        AbstractBceResponse response = rdsClient.updateRdsPublicNetworkAccessStatus(request);
        print("updateRdsPublicNetworkAccessStatus", response);
    }
}
