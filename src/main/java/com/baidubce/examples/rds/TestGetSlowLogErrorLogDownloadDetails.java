package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSlowLogErrorLogDownloadDetailsRequest;
import com.baidubce.services.rds.model.RdsSlowLogErrorLogDownloadDetailsResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetSlowLogErrorLogDownloadDetails {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();
        RdsSlowLogErrorLogDownloadDetailsRequest request = new RdsSlowLogErrorLogDownloadDetailsRequest();
        request.setInstanceId("rds-1AfwpHXs");
        request.setLogId("postgresql-2024-07-11_000000.log");
        request.setDownloadValidTimeInSec(1090);
        RdsSlowLogErrorLogDownloadDetailsResponse response = rdsClient.getSlowLogErrorLogDownloadDetails(request);
        print("getSlowLogErrorLogDownloadDetails", response);
    }
}
