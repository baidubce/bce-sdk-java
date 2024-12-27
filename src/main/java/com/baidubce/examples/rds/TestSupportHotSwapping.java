package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsSupportHotSwappingRequest;
import com.baidubce.services.rds.model.RdsSupportHotSwappingResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestSupportHotSwapping {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsSupportHotSwappingRequest request = new RdsSupportHotSwappingRequest();
        request.setInstanceId("rds-6QCusckC");
        request.setBackupAzone("cn-bj-d");
        request.setAllocatedMemoryInMB(1024);
        request.setAllocatedStorageInGB(50);
        request.setCpuCount(1);
        request.setMasterAzone("cn-bj-d");
        request.setSubnetId("sbn-dqafncqsy3y4");
        RdsSupportHotSwappingResponse response = rdsClient.supportHotSwapping(request);
        print("supportHotSwapping", response);
    }

}
