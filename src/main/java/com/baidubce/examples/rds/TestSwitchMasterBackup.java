package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSwitchMasterBackupRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestSwitchMasterBackup {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSwitchMasterBackupRequest request = new RdsSwitchMasterBackupRequest();
        request.setInstanceId("rds-b5GOdF2A");
        request.setExchangeType(1);
        request.setEffectiveTime("immediate");

        AbstractBceResponse response = rdsClient.switchMasterBackup(request);
        print("switchMasterBackup", response);
    }
}
