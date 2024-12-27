package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSyncModeRequest;


import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateRdsSyncMode {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSyncModeRequest request = new RdsSyncModeRequest();
        request.setSyncMode("async");
        request.setInstanceId("rds-17JzX6Wm");
        AbstractBceResponse response = rdsClient.updateRdsSyncMode(request);
        print("updateRdsSyncMode", response);
    }
}
