package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsRenewalRequest;
import com.baidubce.services.rds.model.RdsRenewalResponse;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestRdsRenewal {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsRenewalRequest request = new RdsRenewalRequest();
        request.setDuration("1");
        List<String> id = new ArrayList<>();
        id.add("rds-6f17R5R3");
        request.setInstanceIds(id);
        RdsRenewalResponse response = rdsClient.rdsRenewal(request);
        print("rdsRenewal", response);
    }
}
