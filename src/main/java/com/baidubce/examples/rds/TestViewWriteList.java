package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsViewWriteListRequest;
import com.baidubce.services.rds.model.RdsViewWriteListResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestViewWriteList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsViewWriteListRequest request = new RdsViewWriteListRequest();
        request.setInstanceId("rds-5WIldjI3");
        RdsViewWriteListResponse response = rdsClient.viewWriteList(request);
        print("viewWriteList", response);
    }
}
