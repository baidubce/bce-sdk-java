package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUpdateEncryptPolicyReq;
import com.baidubce.services.rds.model.RdsUpdateEncryptPolicyRequest;
import com.baidubce.services.rds.model.RdsUpdateEncryptPolicyRequest.EncryptPolicyRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateEncryptPolicy {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        EncryptPolicyRequest encryptStrategy = new EncryptPolicyRequest();
        encryptStrategy.setEncryptEnable(true);
        encryptStrategy.setKeyManagementType("self_kms");
        encryptStrategy.setKeyManagementServiceName("rds");

        RdsUpdateEncryptPolicyRequest backupPolicy = new RdsUpdateEncryptPolicyRequest();
        backupPolicy.setEncryptStrategy(encryptStrategy);

        RdsUpdateEncryptPolicyReq request = new RdsUpdateEncryptPolicyReq();
        request.setInstanceId("rds-b5GOdF2A");
        request.setBackupPolicy(backupPolicy);

        AbstractBceResponse response = rdsClient.updateEncryptPolicy(request);
        print("updateEncryptPolicy", response);
    }
}
