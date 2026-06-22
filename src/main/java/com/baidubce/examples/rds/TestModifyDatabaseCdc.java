package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsModifyDatabaseCdcRequest;
import com.baidubce.services.rds.model.RdsModifyDatabaseCdcResponse;

import java.util.Arrays;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestModifyDatabaseCdc {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsModifyDatabaseCdcRequest request = new RdsModifyDatabaseCdcRequest();
        request.setInstanceId("rds-Qk2LpXBj");
        request.setDbNames(Arrays.asList("testSqlServerDatabase"));
        request.setModifyType("enable");
        RdsModifyDatabaseCdcResponse response = rdsClient.modifyDatabaseCdc(request);
        System.out.println("taskId: " + response.getTaskId());
    }
}
