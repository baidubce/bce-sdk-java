package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsConnInformationRequest;


import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateRdsConnectionInformation {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsConnInformationRequest request = new RdsConnInformationRequest();
        request.setInstanceId("rds-6f17R5R3");
        request.setAddress("fkajsdlk");
        AbstractBceResponse response = rdsClient.updateRdsConnectionInformation(request);
        print("updateRdsConnectionInformation", response);
    }
}
