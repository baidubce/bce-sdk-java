package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsDeleteSpecifiedBackUpRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestDeleteTheSpecifiedBackupSet {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsDeleteSpecifiedBackUpRequest request = new RdsDeleteSpecifiedBackUpRequest();
        request.setInstanceId("rdsmsn17thg0i4p");
        request.setSnapshotId("1720671279584629601");
        AbstractBceResponse response = rdsClient.deleteTheSpecifiedBackupSet(request);
        print("deleteTheSpecifiedBackupSet", response);

    }
}
