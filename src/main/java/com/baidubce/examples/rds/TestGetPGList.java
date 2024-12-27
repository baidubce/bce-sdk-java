package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetPGListRequest;
import com.baidubce.services.rds.model.RdsGetPGListResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetPGList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetPGListRequest request = new RdsGetPGListRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setDate("2023-06-14");
        RdsGetPGListResponse response = rdsClient.getPGList(request);
        print("getPGList", response);
    }
}
