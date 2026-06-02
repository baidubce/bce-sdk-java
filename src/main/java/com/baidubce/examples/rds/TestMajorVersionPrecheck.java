package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsMajorVersionPrecheckRequest;
import com.baidubce.services.rds.model.RdsMajorVersionPrecheckResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestMajorVersionPrecheck {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsMajorVersionPrecheckRequest request = new RdsMajorVersionPrecheckRequest();
         request.setInstanceId("rds-b5GOdF2A");
         request.setCheckType("trigger");

        RdsMajorVersionPrecheckResponse response = rdsClient.majorVersionPrecheck(request);
        print("majorVersionPrecheck", response);
    }
}
