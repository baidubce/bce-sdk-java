package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogListRequest;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogListResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestSlowLogGetErrorLogList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSlowLogGetErrorLogListRequest request = new RdsSlowLogGetErrorLogListRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setDatetime("2024-07-11 00:00:00");
        RdsSlowLogGetErrorLogListResponse response = rdsClient.slowLogGetErrorLogList(request);
        print("slowLogGetErrorLogList", response);
    }
}
