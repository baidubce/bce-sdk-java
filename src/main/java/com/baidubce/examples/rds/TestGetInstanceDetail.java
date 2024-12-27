package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsInstanceDetailRequest;
import com.baidubce.services.rds.model.RdsInstanceDetailResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetInstanceDetail {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsInstanceDetailRequest detailRequest = new RdsInstanceDetailRequest();
        detailRequest.setInstanceId("");
        RdsInstanceDetailResponse detailResponse = rdsClient.getRdsInstanceDetail(detailRequest);
        print("getInstanceDetail", detailResponse);
    }
}
