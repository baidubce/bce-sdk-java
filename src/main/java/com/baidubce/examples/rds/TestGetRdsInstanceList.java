package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsInstanceListRequest;
import com.baidubce.services.rds.model.RdsInstanceListResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestGetRdsInstanceList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsInstanceListRequest request = new RdsInstanceListRequest();
        RdsInstanceListResponse rdsInstanceList = rdsClient.getRdsInstanceList(request);
    }
}
