/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsClusterType;
import com.baidubce.services.scs.model.ScsCreateRequest;
import com.baidubce.services.scs.model.ScsCreateResponse;
import com.baidubce.services.scs.model.instance.ScsReplication;
import com.baidubce.util.JsonUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.baidubce.examples.scs.ScsExampleUtil.CLIENT_TOKEN;
import static com.baidubce.examples.scs.ScsExampleUtil.SUBNET_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.VPC_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.ZONE_NAME;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.examplePostpaidBilling;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleTag;
public class ExampleCreateInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsCreateRequest request = createRedisClusterInstanceRequest();
//        ScsCreateRequest request = createRedisMasterSlaveInstanceRequest();
//        ScsCreateRequest request = createPegaInstanceRequest();
        System.out.println(JsonUtils.toJsonString(request));
        ScsCreateResponse response = client.createInstance(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    public static ScsCreateRequest createRedisClusterInstanceRequest() {
        ScsCreateRequest request = new ScsCreateRequest();
        request.setClientToken(CLIENT_TOKEN);
        request.setInstanceName("java-sdk-example-redis-cluster");
        request.setNodeType("cache.n1.small");
        request.setPort(6379);
        request.setEngineVersion("6.0");
        request.setEngine(2);
        request.setStoreType(0);
        request.setPurchaseCount(1);
        request.setShardNum(3);
        request.setClusterType(ScsClusterType.CLUSTER);
        request.setVpcId(VPC_ID);

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

//        request.setReplicationNum(1);
//        request.setSubnets(Collections.singletonList(exampleSubnet()));
        request.setBilling(examplePostpaidBilling());
        request.setClientAuth("example1234");
        request.setAutoBackupConfig("Tue,Wed,Thu,Fri,Sta,Sun,Mon;16:20:01;5");
        request.setTags(Collections.singletonList(exampleTag()));
//        request.setDeployIdList(Collections.singletonList("deploy-id"));
//        request.setBcmInstanceGroups(Collections.singletonList(exampleBcmInstanceGroupRequest()));
//        request.setBgwGroupId("");
//        request.setConfTpl();
        return request;
    }

    public static ScsCreateRequest createRedisMasterSlaveInstanceRequest() {
        ScsCreateRequest request = new ScsCreateRequest();
        request.setClientToken(CLIENT_TOKEN);
        request.setInstanceName("java-sdk-example-redis-master-slave");
        request.setNodeType("cache.n1.small");
        request.setPort(6379);
        request.setEngineVersion("6.0");
        request.setEngine(2);
        request.setStoreType(0);
        request.setPurchaseCount(3);
        request.setShardNum(1);
        request.setClusterType(ScsClusterType.MASTER_SLAVE);
        request.setVpcId(VPC_ID);

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

//        request.setReplicationNum(1);
//        request.setSubnets(Collections.singletonList(exampleSubnet()));
        request.setBilling(examplePostpaidBilling());
        request.setClientAuth("example1234");
        request.setAutoBackupConfig("Tue,Wed,Thu,Fri,Sta,Sun,Mon;16:20:01;5");
        request.setTags(Collections.singletonList(exampleTag()));
//        request.setDeployIdList(Collections.singletonList("deploy-id"));
//        request.setBcmInstanceGroups(Collections.singletonList(exampleBcmInstanceGroupRequest()));
//        request.setBgwGroupId("");
//        request.setConfTpl();
        return request;
    }

    public static ScsCreateRequest createPegaInstanceRequest() {
        ScsCreateRequest request = new ScsCreateRequest();
        request.setClientToken(CLIENT_TOKEN);
        request.setInstanceName("java-sdk-example-pegadb");
        request.setNodeType("pega.g4s1.micro");
        request.setPort(6379);
//        request.setEngineVersion("6.0");
        request.setEngine(3);
        request.setStoreType(3);
        request.setPurchaseCount(3);
        request.setShardNum(3);
        request.setClusterType(ScsClusterType.CLUSTER);
        request.setVpcId(VPC_ID);

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

//        request.setReplicationNum(1);
//        request.setSubnets(Collections.singletonList(exampleSubnet()));
        request.setBilling(examplePostpaidBilling());
        request.setClientAuth("example1234");
        request.setAutoBackupConfig("Tue,Wed,Thu,Fri,Sta,Sun,Mon;16:20:01;5");
        request.setTags(Collections.singletonList(exampleTag()));
//        request.setDeployIdList(Collections.singletonList("deploy-id"));
//        request.setBcmInstanceGroups(Collections.singletonList(exampleBcmInstanceGroupRequest()));
//        request.setBgwGroupId("");
//        request.setConfTpl();
        request.setDiskFlavor(100);
//        request.setDiskType();
        return request;
    }
}
