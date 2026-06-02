package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUpgradeMajorVersionRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpgradeMajorVersion {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsUpgradeMajorVersionRequest request = new RdsUpgradeMajorVersionRequest();
        request.setInstanceId("rds-b5GOdF2A");
        request.setTargetMajorVersion("8.0");
        request.setEffectiveTime("immediate");

        AbstractBceResponse response = rdsClient.upgradeMajorVersion(request);
        print("upgradeMajorVersion", response);
    }
}
