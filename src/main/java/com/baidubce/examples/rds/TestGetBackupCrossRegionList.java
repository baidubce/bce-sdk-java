package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsBackupTargetRegionResponse;
import com.baidubce.services.rds.model.RdsTargetRegionListRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetBackupCrossRegionList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsTargetRegionListRequest request = new RdsTargetRegionListRequest();
        request.setInstanceId("rds-b5GOdF2A");

        RdsBackupTargetRegionResponse response = rdsClient.getTargetRegionList(request);
        print("getBackupTargetRegionList", response);
    }
}
