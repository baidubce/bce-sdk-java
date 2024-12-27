package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsModifyAccountPasswordRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestModifyAccountPassword {
    public static void main(String[] args) {
        try {
            RdsClient rdsClient = createRdsClient();

            RdsModifyAccountPasswordRequest request = new RdsModifyAccountPasswordRequest();
            request.setInstanceId("rds-tXjFULZA");
            request.setAccountName("nosuper");
            request.setPassword("123jklMN");
            AbstractBceResponse response = rdsClient.modifyAccountPassword(request);
        } catch (Throwable t) {
            System.exit(1);
        }
    }
}
