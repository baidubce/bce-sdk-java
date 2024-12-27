package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsReleaseInstanceRequest;
import com.baidubce.services.rds.model.RdsReleaseInstanceResponse;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestReleaseInstance {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsReleaseInstanceRequest request = new RdsReleaseInstanceRequest();
        String instanceId = "rds-v1JOe6Di";
        List<String> instanceIds = new ArrayList<>();
        instanceIds.add(instanceId);
        request.setInstanceIds(instanceIds);
        RdsReleaseInstanceResponse response = rdsClient.releaseInstance(request);
        print("releaseInstance", response);;
    }
}
