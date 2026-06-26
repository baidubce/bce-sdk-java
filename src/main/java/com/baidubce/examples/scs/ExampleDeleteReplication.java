/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsOrderResponse;
import com.baidubce.services.scs.model.instance.ScsReplication;
import com.baidubce.services.scs.model.instance.ScsReplicationRequest;
import com.baidubce.util.JsonUtils;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.scs.ScsExampleUtil.CLIENT_TOKEN;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.SUBNET_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.ZONE_NAME;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleDeleteReplication {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsReplicationRequest request = new ScsReplicationRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setClientToken(CLIENT_TOKEN);
        request.setResizeType("delete");

        List<ScsReplication> replications = new ArrayList<>();
        ScsReplication masterReplication = new ScsReplication();
        masterReplication.setAvailabilityZone(ZONE_NAME);
        masterReplication.setSubnetId(SUBNET_ID);
        masterReplication.setIsMaster(1);
        replications.add(masterReplication);

        ScsReplication slave1Replication = new ScsReplication();
        slave1Replication.setAvailabilityZone(ZONE_NAME);
        slave1Replication.setSubnetId(SUBNET_ID);
        slave1Replication.setIsMaster(0);
        replications.add(slave1Replication);

        ScsReplication slave2Replication = new ScsReplication();
        slave2Replication.setAvailabilityZone(ZONE_NAME);
        slave2Replication.setSubnetId(SUBNET_ID);
        slave2Replication.setIsMaster(0);
        replications.add(slave2Replication);
        request.setReplicationInfo(replications);

        ScsOrderResponse response = client.deleteReplication(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
