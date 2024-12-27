package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsModifyDatabaseDescriptionRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestModifyDatabaseDescription {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsModifyDatabaseDescriptionRequest request = new RdsModifyDatabaseDescriptionRequest();
        request.setInstanceId("rds-1a6K6qX8");
        request.setDbName("dbfakfl");
        request.setRemark("testRemark");
        AbstractBceResponse response = rdsClient.modifyDatabaseDescription(request);
    }
}
