package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsModifyAccountRemarksRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestModifyAccountRemark {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsModifyAccountRemarksRequest request = new RdsModifyAccountRemarksRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setRemark("remark1111111944499994");
        request.setAccountName("test_account123");
        AbstractBceResponse response = rdsClient.modifyAccountRemark(request);
    }
}
