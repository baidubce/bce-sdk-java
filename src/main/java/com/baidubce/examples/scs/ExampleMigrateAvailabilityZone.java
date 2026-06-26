/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsInstanceDetailRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailResponse;
import com.baidubce.services.scs.model.instance.ScsMigrateAvailabilityZoneRequest;
import com.baidubce.services.scs.model.instance.ScsReplication;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.SUBNET_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.ZONE_NAME;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleMigrateAvailabilityZone {
    public static void main(String[] args) {
        ScsClient client = createClient();

        ScsInstanceDetailRequest instanceDetailRequest = new ScsInstanceDetailRequest();
        instanceDetailRequest.setInstanceId(INSTANCE_ID);
        ScsInstanceDetailResponse instanceDetail = client.getInstanceDetail(instanceDetailRequest);
        List<ScsReplication> replications = instanceDetail.getReplicationInfo();
        List<ScsReplication> newReplications = new ArrayList<>();
        for (ScsReplication replication : replications) {
            ScsReplication newReplication = new ScsReplication();
            newReplication.setAvailabilityZone(ZONE_NAME);
            newReplication.setSubnetId(SUBNET_ID);
//            newReplication.setAvailabilityZone("cn-bj-e");
//            newReplication.setSubnetId("sbn-h8d022nf4eqf");
            newReplication.setIsMaster(replication.getIsMaster());
            newReplications.add(newReplication);
        }

        ScsMigrateAvailabilityZoneRequest request = new ScsMigrateAvailabilityZoneRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setIsDefer(false);
        request.setReplicationInfo(newReplications);
        client.migrateAvailabilityZone(request);
    }
}
