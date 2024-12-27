package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSubnetRequest;
import com.baidubce.services.rds.model.RdsSubnetResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetSubnetList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSubnetRequest request = new RdsSubnetRequest();
        RdsSubnetResponse subnetResponse = rdsClient.getSubnetList(request);
        print("getSubnetList", subnetResponse);
    }
}
