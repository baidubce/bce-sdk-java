package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsConfigModifyHistoryRequest;
import com.baidubce.services.rds.model.RdsConfigModifyHistoryResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetParameterHistoryList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsConfigModifyHistoryRequest request = new RdsConfigModifyHistoryRequest();
        request.setInstanceId("rds-oOPnK7J7");

        RdsConfigModifyHistoryResponse response = rdsClient.getParameterHistoryList(request);
        print("getParameterHistoryList", response);
    }
}
