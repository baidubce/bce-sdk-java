/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.eni;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eni.model.CreateEniRequest;
import com.baidubce.services.eni.model.CreateEniResponse;
import com.baidubce.services.eni.model.Eni;
import com.baidubce.services.eni.model.EniBindEipRequest;
import com.baidubce.services.eni.model.EniDetail;
import com.baidubce.services.eni.model.EniInstanceOperateRequest;
import com.baidubce.services.eni.model.EniPrivateIpBatchAddRequest;
import com.baidubce.services.eni.model.EniPrivateIpBatchOperateRequest;
import com.baidubce.services.eni.model.EniPrivateIpOperateRequest;
import com.baidubce.services.eni.model.EniUnBindEipRequest;
import com.baidubce.services.eni.model.EniUpdateEnterpriseSecurityGroupRequest;
import com.baidubce.services.eni.model.EniUpdateRequest;
import com.baidubce.services.eni.model.EniUpdateSecurityGroupRequest;
import com.baidubce.services.eni.model.GetEniDetailRequest;
import com.baidubce.services.eni.model.ListEniRequest;
import com.baidubce.services.eni.model.ListEniResponse;
import com.baidubce.services.eni.model.PrivateIp;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Elastic Network Interface Card client test
 */
public class EniClientTest {

    private static final Logger logger = LoggerFactory.getLogger(EniClientTest.class);
    // ak will be written
    private static final String AK = "ak_will_be_written";
    // sk will be written
    private static final String SK = "sk_will_be_written";
    // vpc short id will be written
    private static final String VPC_ID = "vpc_short_id_will_be_written";
    // subnet short id will be written
    private static final String SUBNET_ID = "subnet_short_id_will_be_written";
    // eni name will be written for list enis by name
    private static final String ENI_NAME = "eni_name_will_be_written_for_listing_enis_by_name";
    // security group short id will be written
    private static final String DEFAULT_SECURITY_GROUP_ID = "security_group_short_id_will_be_written";
    // security group short id for update eni normal security group
    private static final String MODIFY_SECURITY_GROUP_ID =
            "security_group_short_id_for_updating_eni_normal_security_group";
    // enterprise security group short id will be written
    private static final String ENTERPRISE_SECURITY_GROUP_ID = "enterprise_security_group_short_id_will_be_written";
    // instance short id will be written
    private static final String INSTANCE_ID = "instance_short_id_will_be_written";
    // eip address will be written
    private static final String PUBLIC_IP = "eip_address_will_be_written";
    private static final Integer WAIT_INSTANCE_OPERATE_LOOP_NUM = 10;
    private static final Integer WAIT_INSTANCE_OPERATE_SECOND = 10;
    private EniClient eniClient;

    private List<String> eniIds = new ArrayList<String>();
    private List<String> bindInstanceEniIds = new ArrayList<String>();
    private List<String> waitDetachFinishedEniIds = new ArrayList<String>();
    private List<String> bindEipInstanceIds = new ArrayList<String>();

    @Before
    public void setup() {
        EniClientConfiguration config = new EniClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        // online bj bcc.bj.baidubce.com
        config.setEndpoint("bcc.bj.baidubce.com");
        eniClient = new EniClient(config);
        eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
    }

    private CreateEniRequest createEniRequest() {
        List<PrivateIp> privateIpSet = new ArrayList<PrivateIp>();
        privateIpSet.add(PrivateIp.builder().primary(true).privateIpAddress("").build());
        privateIpSet.add(PrivateIp.builder().primary(false).privateIpAddress("").build());
        return CreateEniRequest.builder()
                .name("eni_java_sdk_name" + System.currentTimeMillis())
                .subnetId(SUBNET_ID)
                .privateIpSet(privateIpSet)
                .securityGroupIds(Lists.newArrayList(DEFAULT_SECURITY_GROUP_ID))
                .description("java sdk test create eni.")
                .build();
    }

    @After
    public void tearDown() {
        EniDetail eniDetail;
        for (String bindInstanceEniId : bindInstanceEniIds) {
            Integer loopNum = WAIT_INSTANCE_OPERATE_LOOP_NUM;
            while (--loopNum >= 0) {
                eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(bindInstanceEniId).build());
                if ("inuse".equalsIgnoreCase(eniDetail.getStatus())) break;
                else {
                    try {
                        Thread.sleep(WAIT_INSTANCE_OPERATE_SECOND * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(bindInstanceEniId).build());
            if ("inuse".equalsIgnoreCase(eniDetail.getStatus())) {
                EniInstanceOperateRequest eniInstanceDetachRequest =
                        EniInstanceOperateRequest.EniInstanceOperateRequestBuilder()
                                .eniId(bindInstanceEniId).instanceId(INSTANCE_ID).action("detach").build();
                eniClient.detachEniInstance(eniInstanceDetachRequest);
                waitDetachFinishedEniIds.add(bindInstanceEniId);
                eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(bindInstanceEniId).build());
                toJsonPrettyString("detach instance, getEniDetail", eniDetail);
            } else {
                logger.error("eni status is not allowed to detach eni instance,please try again later");
            }
        }
        for (String waitDetachFinishedEniId : waitDetachFinishedEniIds) {
            Integer loopNum = WAIT_INSTANCE_OPERATE_LOOP_NUM;
            while (--loopNum >= 0) {
                eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder()
                        .eniId(waitDetachFinishedEniId).build());
                if ("available".equalsIgnoreCase(eniDetail.getStatus())) break;
                else {
                    try {
                        Thread.sleep(WAIT_INSTANCE_OPERATE_SECOND * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(waitDetachFinishedEniId).build());
            if ("available".equalsIgnoreCase(eniDetail.getStatus())) {
                eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(waitDetachFinishedEniId).build());
                toJsonPrettyString("detach instance finished, getEniDetail", eniDetail);
            } else {
                logger.error("eni is not detached finished,please try again later");
            }
        }

        for (String bindEipInstanceId : bindEipInstanceIds) {
            EniUnBindEipRequest eniUnBindEipRequest = EniUnBindEipRequest.EniUnBindEipRequestBuilder()
                    .eniId(bindEipInstanceId).action("unBind").publicIpAddress(PUBLIC_IP).build();
            eniClient.unBindEniPublicIp(eniUnBindEipRequest);
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(bindEipInstanceId).build());
            toJsonPrettyString("unbind publicIp, getEniDetail", eniDetail);
        }
        for (String eniId : eniIds) {
            // delete eni in test scene
            eniClient.deleteEni(eniId);
        }
        logger.info("eni test tearDown");
    }

    @Test
    public void testCreateEni() {
        // security group
        CreateEniRequest createEniRequest = createEniRequest();
        CreateEniResponse createEniResponse = eniClient.createEni(createEniRequest);
        assertThat(createEniResponse.getEniId(), notNullValue());
        toJsonPrettyString("createEni with security group", createEniResponse);
        eniIds.add(createEniResponse.getEniId());

        // enterprise security group
        List<PrivateIp> privateIpSet = new ArrayList<PrivateIp>();
        privateIpSet.add(PrivateIp.builder().primary(true).privateIpAddress("").build());
        privateIpSet.add(PrivateIp.builder().primary(false).privateIpAddress("").build());
        CreateEniRequest createEniRequest1 = CreateEniRequest.builder()
                .name("eni_java_sdk_name" + System.currentTimeMillis())
                .subnetId(SUBNET_ID)
                .privateIpSet(privateIpSet)
                .enterpriseSecurityGroupIds(Lists.newArrayList(ENTERPRISE_SECURITY_GROUP_ID))
                .description("java sdk test create eni.")
                .build();
        CreateEniResponse createEniResponse1 = eniClient.createEni(createEniRequest1);
        assertThat(createEniResponse1.getEniId(), notNullValue());
        toJsonPrettyString("createEni with enterprise security group", createEniResponse1);
        eniIds.add(createEniResponse1.getEniId());
        logger.info("test create eni finished.");
    }

    @Test
    public void testUpdateEni() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniUpdateRequest eniUpdateRequest = EniUpdateRequest.EniUpdateRequestBuilder().eniId(eniId)
                .name("test_eni_name_update")
                .description("test eni description update")
                .build();
        eniClient.updateEni(eniUpdateRequest);
        logger.info("test update eni finished.");
    }

    @Test
    public void testDeleteEni() {
        if (!eniIds.isEmpty()) {
            String willDeletedEniId = eniIds.get(0);
            eniClient.deleteEni(willDeletedEniId);
            eniIds.remove(willDeletedEniId);
            logger.info("test delete eni finished.");
        }
    }

    @Test
    public void testListEni() {
        // list all Enis
        ListEniRequest listEniRequest = ListEniRequest.builder().vpcId(VPC_ID).build();
        ListEniResponse listEniResponse = eniClient.listEni(listEniRequest);
        assertThat(listEniResponse.getEnis(), notNullValue());
        toJsonPrettyString("list all Enis", listEniResponse);

        // list ENIs by privateIps
        List<String> privateIpAddresses = new ArrayList<String>();
        for (Eni eni : listEniResponse.getEnis()) {
            privateIpAddresses.add(eni.getPrivateIpSet().get(0).getPrivateIpAddress());
        }
        ListEniRequest listEniRequest1 = ListEniRequest.builder().vpcId(VPC_ID).privateIpAddress(privateIpAddresses).build();
        ListEniResponse listEniByPrivateIps = eniClient.listEni(listEniRequest1);
        assertThat(listEniByPrivateIps.getEnis(), notNullValue());
        toJsonPrettyString("list Enis by PrivateIps", listEniByPrivateIps);

        ListEniRequest listEniRequest2 = ListEniRequest.builder().vpcId(VPC_ID)
                .privateIpAddress(Lists.newArrayList("192.168.10")).build();
        ListEniResponse listEniByPrivateIp = eniClient.listEni(listEniRequest2);
        assertThat(listEniByPrivateIp.getEnis(), notNullValue());
        toJsonPrettyString("list Enis by PrivateIp", listEniByPrivateIp);

        // list Eni by instanceId
        ListEniRequest listEniRequest3 = ListEniRequest.builder().vpcId(VPC_ID).instanceId(INSTANCE_ID).build();
        ListEniResponse listEniByInstanceId = eniClient.listEni(listEniRequest3);
        assertThat(listEniByInstanceId.getEnis(), notNullValue());
        toJsonPrettyString("list Enis by instanceId", listEniByInstanceId);

        // list Eni by name
        ListEniRequest listEniRequest4 = ListEniRequest.builder().vpcId(VPC_ID).name(ENI_NAME).build();
        ListEniResponse listEniByName = eniClient.listEni(listEniRequest4);
        assertThat(listEniByName.getEnis(), notNullValue());
        toJsonPrettyString("list Enis by name", listEniByName);
    }

    @Test
    public void testGetEniDetail() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("getEniDetail", eniDetail);
    }

    @Test
    public void testAddPrivateIp() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        eniClient.addPrivateIp(EniPrivateIpOperateRequest.builder().eniId(eniId).privateIpAddress("").build());
        logger.info("test add privateIp finished.");
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("after add privateIp getEniDetail", eniDetail);
    }

    @Test
    public void testDeletePrivateIp() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        eniClient.addPrivateIp(EniPrivateIpOperateRequest.builder().eniId(eniId).privateIpAddress("").build());
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("add privateIp before test delete privateIp, getEniDetail", eniDetail);
        List<PrivateIp> privateIpSet = eniDetail.getPrivateIpSet();
        if (!CollectionUtils.isEmpty(privateIpSet)) {
            String willDeletePrivateIp = "";
            for (PrivateIp privateIp : privateIpSet) {
                if (!privateIp.getPrimary()) willDeletePrivateIp = privateIp.getPrivateIpAddress();
            }
            eniClient.deletePrivateIp(EniPrivateIpOperateRequest.builder()
                    .eniId(eniId)
                    .privateIpAddress(willDeletePrivateIp)
                    .build());
            EniDetail eniDetailAfterDeletePrivateIp =
                    eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            toJsonPrettyString("after delete privateIp, getEniDetail", eniDetailAfterDeletePrivateIp);
        }
    }

    @Test
    public void testBatchAddPrivateIp() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        List<String> privateIpAddresses = new ArrayList<String>();
        privateIpAddresses.add("");
        privateIpAddresses.add("");
        EniPrivateIpBatchAddRequest privateIpBatchAddRequest =
                EniPrivateIpBatchAddRequest.EniPrivateIpBatchAddRequestBuilder()
                        .eniId(eniId).privateIpAddresses(privateIpAddresses).build();
        eniClient.batchAddPrivateIp(privateIpBatchAddRequest);
        EniDetail eniDetailAfterBatchAdd = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("after batch add privateIp getEniDetail", eniDetailAfterBatchAdd);
    }

    @Test
    public void testBatchDeletePrivateIp() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniPrivateIpBatchAddRequest privateIpBatchAddRequest =
                EniPrivateIpBatchAddRequest.EniPrivateIpBatchAddRequestBuilder()
                        .eniId(eniId).privateIpAddressCount(3).build();
        eniClient.batchAddPrivateIp(privateIpBatchAddRequest);
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("add privateIp before test batch delete privateIp, getEniDetail", eniDetail);
        List<PrivateIp> privateIpSet = eniDetail.getPrivateIpSet();
        if (!CollectionUtils.isEmpty(privateIpSet) && privateIpSet.size() >= 3) {
            List<String> willDeletedPrivateIpAddresses = new ArrayList<String>();
            for (PrivateIp privateIp : privateIpSet) {
                if (!privateIp.getPrimary()) willDeletedPrivateIpAddresses.add(privateIp.getPrivateIpAddress());
            }
            eniClient.batchDeletePrivateIp(EniPrivateIpBatchOperateRequest.builder()
                    .eniId(eniId)
                    .privateIpAddresses(willDeletedPrivateIpAddresses)
                    .build());
            EniDetail eniDetailAfterBatchDelete =
                    eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            toJsonPrettyString("after batch delete privateIp, getEniDetail", eniDetailAfterBatchDelete);
        }
    }

    @Test
    public void testAttachEniInstance() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniInstanceOperateRequest eniInstanceAttachRequest = EniInstanceOperateRequest.EniInstanceOperateRequestBuilder()
                .eniId(eniId).instanceId(INSTANCE_ID).action("attach").build();
        eniClient.attachEniInstance(eniInstanceAttachRequest);
        bindInstanceEniIds.add(eniId);
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("after attach instance, getEniDetail", eniDetail);
    }

    @Test
    public void testDetachEniInstance() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        if (!bindInstanceEniIds.isEmpty()) {
            eniId = bindInstanceEniIds.get(0);
        }
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        if (StringUtils.isEmpty(eniDetail.getInstanceId())) {
            EniInstanceOperateRequest eniInstanceAttachRequest =
                    EniInstanceOperateRequest.EniInstanceOperateRequestBuilder()
                            .eniId(eniId).instanceId(INSTANCE_ID).action("attach").build();
            eniClient.attachEniInstance(eniInstanceAttachRequest);
            bindInstanceEniIds.add(eniId);
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            toJsonPrettyString("attach instance before test detach eni instance, getEniDetail", eniDetail);
        }
        Integer loopNum = WAIT_INSTANCE_OPERATE_LOOP_NUM;
        while (--loopNum >= 0) {
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            if ("inuse".equalsIgnoreCase(eniDetail.getStatus())) break;
            else {
                try {
                    Thread.sleep(WAIT_INSTANCE_OPERATE_SECOND * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        if ("inuse".equalsIgnoreCase(eniDetail.getStatus())) {
            EniInstanceOperateRequest eniInstanceDetachRequest =
                    EniInstanceOperateRequest.EniInstanceOperateRequestBuilder()
                            .eniId(eniId).instanceId(INSTANCE_ID).action("detach").build();
            eniClient.detachEniInstance(eniInstanceDetachRequest);
            bindInstanceEniIds.remove(eniId);
            waitDetachFinishedEniIds.add(eniId);
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            toJsonPrettyString("after detach instance, getEniDetail", eniDetail);
        } else {
            logger.error("eni status is not allowed to detach eni instance,please try again later");
        }
    }

    @Test
    public void testBindEniPublicIp() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        if (CollectionUtils.isEmpty(eniDetail.getPrivateIpSet())) {
            eniClient.addPrivateIp(EniPrivateIpOperateRequest.builder().eniId(eniId).privateIpAddress("").build());
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            toJsonPrettyString("add privateIp before test bind eip, getEniDetail", eniDetail);
        }
        String privateIp = eniDetail.getPrivateIpSet().get(0).getPrivateIpAddress();
        eniClient.bindEniPublicIp(EniBindEipRequest.EniBindEipRequestBuilder()
                .eniId(eniId).action("bind").privateIpAddress(privateIp).publicIpAddress(PUBLIC_IP).build());
        bindEipInstanceIds.add(eniId);
        eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("after bind publicIp, getEniDetail", eniDetail);
    }

    @Test
    public void testUnBindEniPublicIp() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniDetail eniDetail;
        if (!bindEipInstanceIds.isEmpty()) {
            eniId = bindInstanceEniIds.get(0);
        } else {
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            String privateIp = eniDetail.getPrivateIpSet().get(0).getPrivateIpAddress();
            eniClient.bindEniPublicIp(EniBindEipRequest.EniBindEipRequestBuilder()
                    .eniId(eniId).action("bind").privateIpAddress(privateIp).publicIpAddress(PUBLIC_IP).build());
            eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
            bindEipInstanceIds.add(eniId);
            toJsonPrettyString("bind eip before test unbind eip, getEniDetail", eniDetail);
        }
        EniUnBindEipRequest eniUnBindEipRequest = EniUnBindEipRequest.EniUnBindEipRequestBuilder()
                .eniId(eniId).action("unBind").publicIpAddress(PUBLIC_IP).build();
        eniClient.unBindEniPublicIp(eniUnBindEipRequest);
        bindEipInstanceIds.remove(eniId);
        eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("after unbind publicIp, getEniDetail", eniDetail);
    }

    @Test
    public void testUpdateEniSecurityGroup() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniUpdateSecurityGroupRequest bindSgReq = EniUpdateSecurityGroupRequest.EniUpdateSecurityGroupRequestBuilder()
                .eniId(eniId).action("bindSg")
                .securityGroupIds(Lists.newArrayList(MODIFY_SECURITY_GROUP_ID)).build();
        eniClient.updateEniSecurityGroup(bindSgReq);
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("after update security group, getEniDetail", eniDetail);
    }

    @Test
    public void testUpdateEniEnterpriseSecurityGroup() {
        if (eniIds.isEmpty()) {
            eniIds.add(eniClient.createEni(createEniRequest()).getEniId());
        }
        String eniId = eniIds.get(0);
        EniUpdateEnterpriseSecurityGroupRequest bindEsgReq = EniUpdateEnterpriseSecurityGroupRequest
                .EniUpdateEnterpriseSecurityGroupRequestBuilder()
                .eniId(eniId).action("bindEsg")
                .enterpriseSecurityGroupIds(Lists.newArrayList(ENTERPRISE_SECURITY_GROUP_ID)).build();
        eniClient.updateEniEnterpriseSecurityGroup(bindEsgReq);
        EniDetail eniDetail = eniClient.getEniDetail(GetEniDetailRequest.builder().eniId(eniId).build());
        toJsonPrettyString("after update enterprise security group, getEniDetail", eniDetail);
    }

    @Test
    public void testGetEniStatus(){
        eniClient.getEniStatus("eni-wjz6683jaswn");
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
