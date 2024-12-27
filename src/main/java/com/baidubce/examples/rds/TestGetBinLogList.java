package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetBinLogListRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetBinLogList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetBinLogListRequest request = new RdsGetBinLogListRequest();
        request.setInstanceId("rds-1Cm4tVAO");
        request.setDatetime("2024-07-11T06:48:29Z");
        AbstractBceResponse response = rdsClient.getBinLogList(request);
        print("getBinLogList", response);
    }
}
