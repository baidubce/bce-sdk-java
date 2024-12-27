package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsChangeDatabasePortRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestChangeDatabasePort {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsChangeDatabasePortRequest request = new RdsChangeDatabasePortRequest();
        request.setInstanceId("rds-Ml7QDBqz");
        request.setEntryPort(13206);
        AbstractBceResponse response = rdsClient.changeDatabasePort(request);
    }
}
