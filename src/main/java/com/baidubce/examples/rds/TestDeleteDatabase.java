package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsDeleteDatabaseRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestDeleteDatabase {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsDeleteDatabaseRequest request = new RdsDeleteDatabaseRequest();
        request.setInstanceId("rds-Qk2LpXBj");
        request.setDbName("testMysqlDatabase");
        AbstractBceResponse response = rdsClient.deleteDatabase(request);
    }
}
