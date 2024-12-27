package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetPriceDifferenceRequest;
import com.baidubce.services.rds.model.RdsGetPriceDifferenceResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetPriceDifference {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetPriceDifferenceRequest request = new RdsGetPriceDifferenceRequest();
        request.setInstanceId("rds-6QCusckC");
        request.setCpuCount(1);
        request.setAllocatedMemoryInGB(1);
        request.setAllocatedStorageInGB(50);
        request.setDiskIoType("cloud_high");
        RdsGetPriceDifferenceResponse response = rdsClient.getPriceDifference(request);
        print("getPriceDifference", response);
    }
}
