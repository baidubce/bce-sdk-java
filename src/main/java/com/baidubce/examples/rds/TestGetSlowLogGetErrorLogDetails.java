package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogDetailsRequest;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogDetailsResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetSlowLogGetErrorLogDetails {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSlowLogGetErrorLogDetailsRequest request = new RdsSlowLogGetErrorLogDetailsRequest();
        request.setInstanceId("rds-b3VFCjMA");
        request.setStartTime("2024-07-10T16:00:00Z");
        request.setEndTime("2024-07-10T16:00:05Z");
        request.setPageNo("1");
        request.setPageSize("10");
        request.setKeyWord("Logging");
        RdsSlowLogGetErrorLogDetailsResponse response = rdsClient.getSlowLogGetErrorLogDetails(request);
        print("getSlowLogGetErrorLogDetails", response);
    }
}
