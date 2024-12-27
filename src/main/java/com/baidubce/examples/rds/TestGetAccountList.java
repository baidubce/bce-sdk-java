package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsAccountListRequest;
import com.baidubce.services.rds.model.RdsAccountListResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetAccountList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsAccountListRequest listRequest = new RdsAccountListRequest();
        listRequest.setInstanceId("rds-jHqrZCEk");
        RdsAccountListResponse listResponse = rdsClient.getAccountList(listRequest);
        print("getAccountList", listResponse);
    }
}
