/**
 * Copyright 2020 Baidu, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bes;

import com.baidubce.services.bes.model.*;
import com.baidubce.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BesClientTest extends AbstractBesClientTest {
    private String clusterId;

    @Before
    public void setUp() {
        super.setUp();
        clusterId = "489268134948245504";
    }

    @Test
    public void testGetClustersList() {
        BesGetClusterListRequest besGetClusterListRequest = new BesGetClusterListRequest();
        besGetClusterListRequest.setPageNo(1);
        besGetClusterListRequest.setPageSize(20);
        BesGetClusterListResponse besGetClusterListResponse = this.besClient.getClusterList(besGetClusterListRequest);
        System.out.println(JsonUtils.toJsonString(besGetClusterListResponse));
    }

    @Test
    public void testCreateCluster() {
        BesCreateClusterRequest createOrderRequestVo = new BesCreateClusterRequest();
        BesCreateClusterRequest.ModuleInfo moduleInfo = new BesCreateClusterRequest.ModuleInfo();
        BesCreateClusterRequest.ModuleInfo moduleInfo2 = new BesCreateClusterRequest.ModuleInfo();
        BesCreateClusterRequest.ModuleInfo moduleInfo3 = new BesCreateClusterRequest.ModuleInfo();
        List<BesCreateClusterRequest.ModuleInfo> moduleInfoList = new ArrayList<BesCreateClusterRequest.ModuleInfo>();
        BesCreateClusterRequest.ClusterBilling clusterBilling = new BesCreateClusterRequest.ClusterBilling();
        DiskSlotInfo diskSlotInfo = new DiskSlotInfo();
        DiskSlotInfo diskSlotInfo2 = new DiskSlotInfo();
        createOrderRequestVo.setName("testMaster22");
        createOrderRequestVo.setPassword("123456aA");

        moduleInfo.setType(ModuleType.ES_NODE);
        moduleInfo.setInstanceNum(1);
        moduleInfo.setSlotType(SlotType.BES_C3_CPU_2_MEM_4);
        diskSlotInfo.setSize(50);
        diskSlotInfo.setType(DiskType.PREMIUM_SSD);
        moduleInfo.setDiskSlotInfo(diskSlotInfo);

        moduleInfo2.setType(ModuleType.KIBANA);
        moduleInfo2.setInstanceNum(1);
        moduleInfo2.setSlotType(SlotType.KIBANA_C3_CPU_1_MEM_2);


        moduleInfo3.setType(ModuleType.ES_DEDICATED_MASTER);
        moduleInfo3.setInstanceNum(3);
        moduleInfo3.setSlotType(SlotType.BES_G3_CPU_2_MEM_8);
        diskSlotInfo2.setSize(21);
        diskSlotInfo2.setType(DiskType.PREMIUM_SSD);
        moduleInfo3.setDiskSlotInfo(diskSlotInfo2);

        moduleInfoList.add(moduleInfo);
        moduleInfoList.add(moduleInfo2);
        moduleInfoList.add(moduleInfo3);

        clusterBilling.setPaymentType(PaymentType.POSTPAY);
        clusterBilling.setTime(0);
        createOrderRequestVo.setModules(moduleInfoList);
        createOrderRequestVo.setBilling(clusterBilling);
        createOrderRequestVo.setVersion("7.4.2");
        createOrderRequestVo.setAvailableZone("cn-bj-a");
        createOrderRequestVo.setSecurityGroupId("g-e1kvq4vdbb6q");
        createOrderRequestVo.setSubnetUuid("sbn-s9c90t8w0x61");
        createOrderRequestVo.setVpcId("vpc-5mstq2fdse7v");
        createOrderRequestVo.setOldPackage(false);
//        online
//        createOrderRequestVo.setSecurityGroupId("g-1iguvn3s81jv");
//        createOrderRequestVo.setSubnetUuid("sbn-hn64qzg70puf");
//        createOrderRequestVo.setVpcId("vpc-362ira58mkcw");
        BesCreateClusterResponse deploy = this.besClient.createCluster(createOrderRequestVo);
        System.out.println(JsonUtils.toJsonString(deploy));
    }

    @Test
    public void testResizeCluster() {
        BesResizeClusterRequest besResizeClusterRequest = new BesResizeClusterRequest();
        BesResizeClusterRequest.ModuleDesc moduleDesc = new BesResizeClusterRequest.ModuleDesc();
        BesResizeClusterRequest.ModuleDesc moduleDesc2 = new BesResizeClusterRequest.ModuleDesc();
        DiskSlotInfo diskSlotInfo = new DiskSlotInfo();
        List<BesResizeClusterRequest.ModuleDesc> modules = new ArrayList<BesResizeClusterRequest.ModuleDesc>();
        besResizeClusterRequest.setRegion("bj");
        besResizeClusterRequest.setName("isTest0309");
        besResizeClusterRequest.setPaymentType(PaymentType.POSTPAY);
        besResizeClusterRequest.setClusterId(clusterId);

        moduleDesc.setType(ModuleType.ES_NODE);
        moduleDesc.setSlotType(SlotType.BES_G3_CPU_8_MEM_32);
        moduleDesc.setDesireInstanceNum(2);
        diskSlotInfo.setType(DiskType.PREMIUM_SSD);
        diskSlotInfo.setSize(60);
        moduleDesc.setDiskSlotInfo(diskSlotInfo);

        moduleDesc2.setType(ModuleType.KIBANA);
        moduleDesc2.setSlotType(SlotType.BES_C3_CPU_2_MEM_4);
        moduleDesc2.setDesireInstanceNum(1);

        modules.add(moduleDesc);
        modules.add(moduleDesc2);
        besResizeClusterRequest.setModules(modules);
        BesCreateClusterResponse besCreateClusterResponse = this.besClient.resizeCluster(besResizeClusterRequest);
        System.out.println(JsonUtils.toJsonString(besCreateClusterResponse));
    }

    @Test
    public void testGetClusterDetail() {
        BesGetClusterDetailRequest besGetClusterDetailRequest = new BesGetClusterDetailRequest();
        besGetClusterDetailRequest.setClusterId(clusterId);
        BesGetClusterDetailResponse besGetClusterDetailResponse =
                this.besClient.getClusterDetail(besGetClusterDetailRequest);
        System.out.println(JsonUtils.toJsonString(besGetClusterDetailResponse));
    }

    @Test
    public void testDeleteCluster() {
        BesDeleteClusterRequest besDeleteClusterRequest = new BesDeleteClusterRequest();
        besDeleteClusterRequest.setClusterId(clusterId);
        BesDeleteClusterResponse besDeleteClusterResponse = this.besClient.deleteCluster(besDeleteClusterRequest);
        System.out.println(JsonUtils.toJsonString(besDeleteClusterResponse));
    }

    @Test
    public void testStartDeploy() {
        BesStartClusterRequest besStartClusterRequest = new BesStartClusterRequest();
        besStartClusterRequest.setClusterId(clusterId);
        BesStartClusterResponse besStartClusterResponse = this.besClient.startCluster(besStartClusterRequest);
        System.out.println(JsonUtils.toJsonString(besStartClusterResponse));
    }

    @Test
    public void testStopDeploy() {
        BesStopClusterRequest besStopClusterRequest = new BesStopClusterRequest();
        besStopClusterRequest.setClusterId(clusterId);
        BesStopClusterResponse besStopClusterResponse = this.besClient.stopCluster(besStopClusterRequest);
        System.out.println(besStopClusterResponse);
    }

    @Test
    public void testInstanceStop() {
        BesStopInstanceRequest besInstanceRequestVo = new BesStopInstanceRequest();
        besInstanceRequestVo.setClusterId(clusterId);
        besInstanceRequestVo.setInstanceId("443755843180171264");
        BesStopInstanceResponse besStopInstanceResponse = this.besClient.stopClusterInstance(besInstanceRequestVo);
    }

    @Test
    public void testInstanceStart() {
        BesStartInstanceRequest besInstanceRequestVo = new BesStartInstanceRequest();
        besInstanceRequestVo.setClusterId(clusterId);
        besInstanceRequestVo.setInstanceId("418956027598344195");
        BesStartInstanceResponse besStartInstanceResponse = this.besClient.startClusterInstance(besInstanceRequestVo);
    }

    @Test
    public void testGetRenewList() {
        BesGetRenewListRequest listAutoRenew = new BesGetRenewListRequest();
        listAutoRenew.setOrder("desc");
        listAutoRenew.setOrderBy("expireTime");
        listAutoRenew.setPageSize(15);
        listAutoRenew.setPageNo(1);
        listAutoRenew.setDaysToExpiration(-1);
        BesGetRenewListResponse renewList = this.besClient.getRenewList(listAutoRenew);
        System.out.println(JsonUtils.toJsonString(renewList));
    }

    @Test
    public void testRenewCluster() {
        BesRenewClusterRequest besRenewClusterRequest = new BesRenewClusterRequest();
        besRenewClusterRequest.setClusterId(clusterId);
        besRenewClusterRequest.setTime(1);
        BesRenewClusterResponse besRenewClusterResponse = this.besClient.renewCluster(besRenewClusterRequest);
        System.out.println(JsonUtils.toJsonString(besRenewClusterResponse));
    }

    @Test
    public void testGetAutoRenewRuleList() {
        BesGetAutoRenewRuleListResponse autoRenewRuleList = this.besClient.getAutoRenewRuleList();
        System.out.println(JsonUtils.toJsonString(autoRenewRuleList));
    }

    @Test
    public void testCreateAutoRenewRule() {
        BesCreateAutoRenewRuleRequest createAutoRenewRuleRequest = new BesCreateAutoRenewRuleRequest();
        List<String> clusterIds = new ArrayList<String>();
        clusterIds.add(clusterId);
        createAutoRenewRuleRequest.setClusterIds(clusterIds);
        createAutoRenewRuleRequest.setUserId("xxx");
        createAutoRenewRuleRequest.setRenewTime(1);
        createAutoRenewRuleRequest.setRenewTimeUnit("month");
        BesCreateAutoRenewRuleResponse autoRenewRule = this.besClient.createAutoRenewRule(createAutoRenewRuleRequest);
        System.out.println(JsonUtils.toJsonString(autoRenewRule));
    }

    @Test
    public void testUpdateAutoRenewRule() {
        BesUpdateAutoRenewRuleRequest updateAutoRenewRuleRequest = new BesUpdateAutoRenewRuleRequest();
        updateAutoRenewRuleRequest.setClusterId(clusterId);
        updateAutoRenewRuleRequest.setRenewTime(2);
        updateAutoRenewRuleRequest.setRenewTimeUnit("month");
        this.besClient.updateAutoRenewRule(updateAutoRenewRuleRequest);
        System.out.println(JsonUtils.toJsonString(updateAutoRenewRuleRequest));
    }

    @Test
    public void testDeleteAutoRenewRule() {
        BesDeleteAutoRenewRuleRequest deleteAutoRenewRuleRequest = new BesDeleteAutoRenewRuleRequest();
        deleteAutoRenewRuleRequest.setClusterId(clusterId);
        this.besClient.deleteAutoRenewRule(deleteAutoRenewRuleRequest);
        System.out.println(JsonUtils.toJsonString(deleteAutoRenewRuleRequest));
    }
}