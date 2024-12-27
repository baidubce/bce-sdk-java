package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsAutoRenewRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.readJson;


public class TestAutoRenew {
    public static void main(String[] args) {
        RdsClient rdsClient= createRdsClient();
        RdsAutoRenewRequest request = readJson("rds/json/TestAutoRenew.json", RdsAutoRenewRequest.class);
        AbstractBceResponse response = rdsClient.autoRenew(request);
    }
}
