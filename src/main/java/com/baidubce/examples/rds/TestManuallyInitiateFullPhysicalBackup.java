package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsFullPhysicalBackupRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestManuallyInitiateFullPhysicalBackup {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsFullPhysicalBackupRequest request = new RdsFullPhysicalBackupRequest();
        request.setInstanceId("rds-5WIldjI3");
        AbstractBceResponse response = rdsClient.manuallyInitiateFullPhysicalBackup(request);
        print("manuallyInitiateFullPhysicalBackup", response);
    }
}
