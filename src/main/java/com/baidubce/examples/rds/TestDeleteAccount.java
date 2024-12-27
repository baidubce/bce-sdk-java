package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsDeleteAccountRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestDeleteAccount {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsDeleteAccountRequest request = new RdsDeleteAccountRequest();
        request.setInstanceId("rds-RhmXpFKn");
        request.setAccountName("delaccount");
        AbstractBceResponse response = rdsClient.deleteAccount(request);
    }
}
