package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsAccount;
import com.baidubce.services.rds.model.RdsAccountInfoRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetSpecificAccountInfo {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsAccountInfoRequest infoRequest = new RdsAccountInfoRequest();
        infoRequest.setInstanceId("rds-jHqrZCEk");
        infoRequest.setAccountName("test_account123");
        RdsAccount accountInfo = rdsClient.getSpecificAccountInfo(infoRequest);
        print("getSpecificAccountInfo", accountInfo);

    }
}
