package com.baidubce.examples.rds;


import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetNewPurchasePriceRequest;
import com.baidubce.services.rds.model.RdsGetNewPurchasePriceResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetNewPurchasePrice {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetNewPurchasePriceRequest request = new RdsGetNewPurchasePriceRequest();
        request.setDuration(1);
        RdsGetNewPurchasePriceRequest.Instance instances = new RdsGetNewPurchasePriceRequest.Instance();
        instances.setEngine("MySQL");
        instances.setCpuCount(1);
        instances.setAllocatedMemoryInGB(1);
        instances.setAllocatedStorageInGB(50);
        instances.setDiskIoType("cloud_high");
        request.setInstance(instances);
        request.setNumber(1);
        request.setProductType("postpay");
        RdsGetNewPurchasePriceResponse response = rdsClient.getNewPurchasePrice(request);
        print("getNewPurchasePrice", response);
    }
}
