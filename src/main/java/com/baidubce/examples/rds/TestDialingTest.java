package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsDialingTestRequest;
import com.baidubce.services.rds.model.RdsDialingTestResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;
public class TestDialingTest {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsDialingTestRequest request = new RdsDialingTestRequest();
        request.setInstanceId("rds-6QCusckC");
        RdsDialingTestResponse response = rdsClient.dialingTest(request);
        print("dialingTest", response);
    }
}
