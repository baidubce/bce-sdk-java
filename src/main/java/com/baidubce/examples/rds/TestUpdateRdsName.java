package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUpdateNameRequest;


import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateRdsName {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsUpdateNameRequest request = new RdsUpdateNameRequest();
        request.setInstanceId("rds-17JzX6Wm");
        request.setInstanceName("gaojianPGSQL");
        AbstractBceResponse response = rdsClient.updateRdsName(request);
        print("updateRdsName", response);
    }
}
