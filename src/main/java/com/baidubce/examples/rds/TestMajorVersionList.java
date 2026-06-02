package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsMajorVersionListRequest;
import com.baidubce.services.rds.model.RdsMajorVersionListResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestMajorVersionList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsMajorVersionListRequest request = new RdsMajorVersionListRequest();
         request.setInstanceId("rds-b5GOdF2A");

        RdsMajorVersionListResponse response = rdsClient.majorVersionList(request);
        print("majorVersionList", response);
    }
}
