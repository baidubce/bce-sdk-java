package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSupportEnableAutoExpansionRequest;
import com.baidubce.services.rds.model.RdsSupportEnableAutoExpansionResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestSupportEnableAutoExpansion {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSupportEnableAutoExpansionRequest request = new RdsSupportEnableAutoExpansionRequest();
        request.setInstanceId("rds-k1dffnQn");
        RdsSupportEnableAutoExpansionResponse response = rdsClient.supportEnableAutoExpansion(request);
        print("supportEnableAutoExpansion", response);
    }
}
