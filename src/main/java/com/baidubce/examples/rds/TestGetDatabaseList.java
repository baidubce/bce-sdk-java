package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetDatabaseListRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestGetDatabaseList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetDatabaseListRequest request = new RdsGetDatabaseListRequest();
        request.setInstanceId("rds-Ml7QDBqz");
        AbstractBceResponse response = rdsClient.getDatabaseList(request);
    }
}
