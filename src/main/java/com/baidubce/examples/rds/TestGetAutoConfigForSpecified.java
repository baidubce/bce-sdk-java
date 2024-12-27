package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetAutoConfigForSpecifiedRequest;
import com.baidubce.services.rds.model.RdsGetAutoConfigForSpecifiedResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;
public class TestGetAutoConfigForSpecified {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetAutoConfigForSpecifiedRequest request = new RdsGetAutoConfigForSpecifiedRequest();
        request.setInstanceId("rds-7xabvFUH");
        RdsGetAutoConfigForSpecifiedResponse response = rdsClient.getAutoConfigForSpecified(request);
        print("testGetAutoConfigForSpecified", response);
    }
}
