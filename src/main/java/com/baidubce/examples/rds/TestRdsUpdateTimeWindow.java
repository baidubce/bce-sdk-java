package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUpdateTimeWindowRequest;


import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestRdsUpdateTimeWindow {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsUpdateTimeWindowRequest request = new RdsUpdateTimeWindowRequest();
        request.setInstanceId("rds-7xabvFUH");
        request.setMaintainDuration(1);
        request.setMaintainStartTime("01:00:00");
        AbstractBceResponse response = rdsClient.rdsUpdateTimeWindow(request);
        print("rdsUpdateTimeWindow", response);
    }
}
