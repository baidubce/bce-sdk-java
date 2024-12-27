package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetBinLogInfoRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetBinLogInfo {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetBinLogInfoRequest request = new RdsGetBinLogInfoRequest();
        request.setInstanceId("rds-1Cm4tVAO");
        request.setLogId("1720685193973036601");
        request.setDownloadValidTimeInSec("1800");
        AbstractBceResponse response = rdsClient.getBinLogInfo(request);
        print("getBinLogInfo", response);
    }
}
