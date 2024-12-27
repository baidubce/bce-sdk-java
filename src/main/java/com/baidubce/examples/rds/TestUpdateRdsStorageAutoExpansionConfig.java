package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUpdateStorageAutoExpansionConfigRequest;
import com.baidubce.services.rds.model.RdsUpdateStorageAutoExpansionConfigResPonse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateRdsStorageAutoExpansionConfig {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsUpdateStorageAutoExpansionConfigRequest request = new RdsUpdateStorageAutoExpansionConfigRequest();
        request.setInstanceId("rds-lZIdjcC3");
        request.setAction("close");
        RdsUpdateStorageAutoExpansionConfigResPonse response = rdsClient.updateRdsStorageAutoExpansionConfig(request);
        print("updateRdsStorageAutoExpansionConfig", response);
    }
}
