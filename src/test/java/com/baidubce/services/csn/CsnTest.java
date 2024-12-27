/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.csn;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bec.BecClientConfiguration;
import com.baidubce.services.csn.model.AttachInstanceRequest;
import com.baidubce.services.csn.model.BindCsnBpRequest;
import com.baidubce.services.csn.model.CreateAssociationRequest;
import com.baidubce.services.csn.model.CreateCsnBpLimitRequest;
import com.baidubce.services.csn.model.CreateCsnBpRequest;
import com.baidubce.services.csn.model.CreateCsnBpResponse;
import com.baidubce.services.csn.model.CreateCsnRequest;
import com.baidubce.services.csn.model.CreatePropagationRequest;
import com.baidubce.services.csn.model.CreateRouteRuleRequest;
import com.baidubce.services.csn.model.CsnBpPriceRequest;
import com.baidubce.services.csn.model.CsnBpPriceResponse;
import com.baidubce.services.csn.model.DeleteCsnBpLimitRequest;
import com.baidubce.services.csn.model.DetachInstanceRequest;
import com.baidubce.services.csn.model.GetCsnBpResponse;
import com.baidubce.services.csn.model.GetCsnResponse;
import com.baidubce.services.csn.model.ListAssociationResponse;
import com.baidubce.services.csn.model.ListCsnBpLimitByCsnIdResponse;
import com.baidubce.services.csn.model.ListCsnBpLimitResponse;
import com.baidubce.services.csn.model.ListCsnBpResponse;
import com.baidubce.services.csn.model.ListCsnResponse;
import com.baidubce.services.csn.model.ListInstanceResponse;
import com.baidubce.services.csn.model.ListPropagationResponse;
import com.baidubce.services.csn.model.ListRouteRuleResponse;
import com.baidubce.services.csn.model.ListRouteTableResponse;
import com.baidubce.services.csn.model.ListTgwResponse;
import com.baidubce.services.csn.model.ListTgwRuleResponse;
import com.baidubce.services.csn.model.ResizeCsnBpRequest;
import com.baidubce.services.csn.model.UnbindCsnBpRequest;
import com.baidubce.services.csn.model.UpdateCsnBpLimitRequest;
import com.baidubce.services.csn.model.UpdateCsnBpRequest;
import com.baidubce.services.csn.model.UpdateCsnRequest;
import com.baidubce.services.csn.model.UpdateTgwRequest;
import com.baidubce.services.tag.model.Tag;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.UUID;

/**
 * unit case
 */
public class CsnTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsnTest.class);
    private static final String AK = "Your AK";
    private static final String SK = "Your SK";

    // vpc short id will be written
    private static final String VPC_ID = "vpc_short_id_will_be_written";
    private static final String ACCOUNT_ID = "account_id_will_be_written";
    private CsnClient client;

    @Before
    public void setUp() {
        BecClientConfiguration config = new BecClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("csn.baidubce.com");
        // config.setProtocol(Protocol.HTTPS);
        client = new CsnClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            LOGGER.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /*    -------------------------- 云智能网相关 --------------------------    */

    @Test
    public void createCsnTest() {
        CreateCsnRequest request = new CreateCsnRequest();
        request.setName("javaSdkTest");
        request.setDescription("java sdk test");

        Tag tag = new Tag();
        tag.setTagKey("key");
        tag.setTagValue("value");
        request.setTags(Arrays.asList(tag));

        String clientToken = UUID.randomUUID().toString();
        client.createCsn(request, clientToken);
    }

    @Test
    public void updateCsnTest() {
        UpdateCsnRequest request = new UpdateCsnRequest();
        request.setName("javaSdkTestUpdate");
        request.setDescription("java sdk test update");
        client.updateCsn("csn-2jssjbhvyd8v1gxn", request, null);
    }

    @Test
    public void deleteCsnTest() {
        client.deleteCsn("csn-2jssjbhvyd8v1gxn", null);
    }

    @Test
    public void listCsnTest() {
        ListCsnResponse listCsnResponse = client.listCsn(null, null);
        toJsonPrettyString("list csn", listCsnResponse);
    }

    @Test
    public void listInstanceTest() {
        String testCsnId = "csn-b65m2hez6h60iwa1";
        String marker = "";
        Integer maxKeys = 10;
        ListInstanceResponse listInstanceResponse = client.listInstance(testCsnId, marker, maxKeys);
        toJsonPrettyString("list csn bind instance", listInstanceResponse);
    }

    @Test
    public void getCsnTest() {
        String testCsnId = "csn-b65m2hez6h60iwa1";
        GetCsnResponse getCsnResponse = client.getCsn(testCsnId);
        toJsonPrettyString("get csn detail", getCsnResponse);
    }

    @Test
    public void detachInstanceTest() {
        String testCsnId = "csn-b65m2hez6h60iwa1";
        DetachInstanceRequest detachInstanceRequest = DetachInstanceRequest.builder()
                .instanceType("vpc")
                .instanceId(VPC_ID)
                .instanceRegion("bj")
                .instanceAccountId(ACCOUNT_ID)
                .build();
        String clientToken = UUID.randomUUID().toString();
        client.detachInstance(testCsnId, detachInstanceRequest, clientToken);
    }

    @Test
    public void attachInstanceTest() {
        String testCsnId = "csn-b65m2hez6h60iwa1";
        AttachInstanceRequest attachInstanceRequest = AttachInstanceRequest.builder()
                .instanceType("vpc")
                .instanceId(VPC_ID)
                .instanceRegion("bj")
                .instanceAccountId(ACCOUNT_ID)
                .build();
        String clientToken = UUID.randomUUID().toString();
        client.attachInstance(testCsnId, attachInstanceRequest, clientToken);
    }

    @Test
    public void csnBpPriceTest() {
        CsnBpPriceRequest request = new CsnBpPriceRequest();
        request.setName("test_prepay");
        request.setBandwidth(10);
        request.setGeographicA("China");
        request.setGeographicB("China");

        CreateCsnBpRequest.Billing.Reservation reservation = CreateCsnBpRequest.Billing.Reservation.builder()
                .reservationTimeUnit("month")   // 时间单位，当前仅支持按月，取值month
                .reservationLength(1)   // 时长，[1,2,3,4,5,6,7,8,9,12,24,36]
                .build();
        CreateCsnBpRequest.Billing billing = CreateCsnBpRequest.Billing.builder()
                .paymentTiming("Prepaid")   // 付款时间，预支付（Prepaid）和后支付（Postpaid）
                .billingMethod("ByBandwidth")   // 计费方式
                .reservation(reservation)   // 保留信息
                .build();
        request.setBilling(billing);

        CsnBpPriceResponse response = client.csnBpPrice(request); // 获取价格
        toJsonPrettyString("price", response);
    }

    /*    -------------------------- 路由管理相关 --------------------------    */

    @Test
    public void createRouteRuleTest() {
        String csnRtId = "csnRt-bhnis7ubpgnmyk5f";
        CreateRouteRuleRequest createRouteRuleRequest = new CreateRouteRuleRequest();
        createRouteRuleRequest.setAttachId("tgwAttach-5ttgra63p7v4q69z");
        createRouteRuleRequest.setDestAddress("0.0.0.0/0");
        createRouteRuleRequest.setRouteType("custom");
        String clientToken = UUID.randomUUID().toString();
        client.createRouteRule(csnRtId, createRouteRuleRequest, clientToken);
    }

    @Test
    public void listRouteRuleTest() {
        String csnRtId = "csnRt-bhnis7ubpgnmyk5f";
        String marker = "";
        Integer maxKeys = 10;
        ListRouteRuleResponse response = client.listRouteRule(csnRtId, marker, maxKeys);
        toJsonPrettyString("list route rule", response);
    }

    @Test
    public void deleteRouteRuleTest() {
        String csnRtId = "csnRt-bhnis7ubpgnmyk5f";
        String csnRtRuleId = "3a8e78e4-6eed-49eb-bc7b-c7e18698e62c";
        String clientToken = UUID.randomUUID().toString();
        client.deleteRouteRule(csnRtId, csnRtRuleId, clientToken);
    }

    @Test
    public void createPropagationTest() {
        String csnRtId = "csnRt-bhnis7ubpgnmyk5f";
        CreatePropagationRequest createPropagationRequest = new CreatePropagationRequest();
        createPropagationRequest.setAttachId("tgwAttach-5ttgra63p7v4q69z");
        String clientToken = UUID.randomUUID().toString();
        client.createPropagation(csnRtId, createPropagationRequest, clientToken);
    }

    @Test
    public void listPropagationTest() {
        ListPropagationResponse response = client.listPropagation("csnRt-bhnis7ubpgnmyk5f");
        toJsonPrettyString("list propagation", response);
    }

    @Test
    public void deletePropagationTest() {
        String csnRtId = "csnRt-bhnis7ubpgnmyk5f";
        String attachId = "tgwAttach-5ttgra63p7v4q69z";
        String clientToken = UUID.randomUUID().toString();
        client.deletePropagation(csnRtId, attachId, clientToken);
    }

    @Test
    public void createAssociationTest() {
        String csnRtId = "csnRt-szxfb2vcr62x41ui";
        CreateAssociationRequest createAssociationRequest = CreateAssociationRequest.builder()
                .attachId("tgwAttach-9kkj5xa21ygx9tmg")
                .description("create csn association description.")
                .build();
        String clientToken = UUID.randomUUID().toString();
        client.createAssociation(csnRtId, createAssociationRequest, clientToken);
    }

    @Test
    public void listAssociationTest() {
        ListAssociationResponse response = client.listAssociation("csnRt-szxfb2vcr62x41ui");
        toJsonPrettyString("list association", response);
    }

    @Test
    public void deleteAssociationTest() {
        String csnRtId = "csnRt-szxfb2vcr62x41ui";
        String attachId = "tgwAttach-9kkj5xa21ygx9tmg";
        String clientToken = UUID.randomUUID().toString();
        client.deleteAssociation(csnRtId, attachId, clientToken);
    }

    @Test
    public void listRouteTableTest() {
        String csnId = "csn-b65m2hez6h60iwa1";
        String marker = "";
        Integer maxKeys = 10;
        ListRouteTableResponse response = client.listRouteTable(csnId, marker, maxKeys);
        toJsonPrettyString("list route table", response);
    }

    /*    -------------------------- 带宽包相关 --------------------------    */

    @Test
    public void listCsnBpTest() {
        String marker = "";
        Integer maxKeys = 10;
        ListCsnBpResponse listCsnBpResponse = client.listCsnBp(marker, maxKeys);
        toJsonPrettyString("list csnBp", listCsnBpResponse);
    }

    @Test
    public void getCsnBpTest() {
        String csnBpId = "csnBp-wij88bjtsqt2";
        GetCsnBpResponse csnBpDetail = client.getCsnBp(csnBpId);
        toJsonPrettyString("get csnBp detail", csnBpDetail);
    }

    @Test
    public void createCsnBpTest() {
        // 预付费
        CreateCsnBpRequest.Billing.Reservation reservation = CreateCsnBpRequest.Billing.Reservation.builder()
                .reservationTimeUnit("month")
                .reservationLength(1)
                .build();
        CreateCsnBpRequest.Billing prepaidBilling = CreateCsnBpRequest.Billing.builder()
                .paymentTiming("Prepaid")
                .reservation(reservation)
                .build();
        CreateCsnBpRequest prepaidCreateCsnBpRequest = CreateCsnBpRequest.builder()
                .name("prepaidCsnBpTest")
                .bandwidth(100)
                .geographicA("China")
                .geographicB("China")
                .interworkType("center")
                .billing(prepaidBilling)
                .build();
        String prepaidClientToken = UUID.randomUUID().toString();
        CreateCsnBpResponse prepaidCsnBp = client.createCsnBp(prepaidCreateCsnBpRequest, prepaidClientToken);
        toJsonPrettyString("create prepaid csnBp", prepaidCsnBp);

        // 后付费
        CreateCsnBpRequest.Billing postpaidBilling = CreateCsnBpRequest.Billing.builder()
                .paymentTiming("Postpaid")
                .build();
        CreateCsnBpRequest postpaidCreateCsnBpRequest = CreateCsnBpRequest.builder()
                .name("postpaidCsnBpTest")
                .bandwidth(100)
                .geographicA("China")
                .geographicB("China")
                .interworkType("center")
                .billing(postpaidBilling)
                .build();
        String postpaidClientToken = UUID.randomUUID().toString();
        CreateCsnBpResponse postpaidCsnBp = client.createCsnBp(postpaidCreateCsnBpRequest, postpaidClientToken);
        toJsonPrettyString("create postpaid csnBp", postpaidCsnBp);
    }

    @Test
    public void updateCsnBpTest() {
        String csnBpId = "csnBp-zi24hdrdb1gf";
        UpdateCsnBpRequest updateCsnBpRequest = UpdateCsnBpRequest.builder().name("updateNameCsnBpTest").build();
        String clientToken = UUID.randomUUID().toString();
        client.updateCsnBp(csnBpId, updateCsnBpRequest, clientToken);
    }

    @Test
    public void deleteCsnBpTest() {
        String csnBpId = "csnBp-0wgzdc384nyh";
        String clientToken = UUID.randomUUID().toString();
        client.deleteCsnBp(csnBpId, clientToken);
    }

    @Test
    public void resizeCsnBpTest() {
        String csnBpId = "csnBp-wij88bjtsqt2";
        ResizeCsnBpRequest resizeCsnBpRequest = ResizeCsnBpRequest.builder().bandwidth(50).build();
        String clientToken = UUID.randomUUID().toString();
        client.resizeCsnBp(csnBpId, resizeCsnBpRequest, clientToken);
    }

    @Test
    public void unbindCsnBpTest() {
        String csnBpId = "csnBp-p4uyr82s0sju";
        UnbindCsnBpRequest unbindCsnBpRequest = UnbindCsnBpRequest.builder().csnId("csn-b65m2hez6h60iwa1").build();
        String clientToken = UUID.randomUUID().toString();
        client.unbindCsnBp(csnBpId, unbindCsnBpRequest, clientToken);
    }

    @Test
    public void bindCsnBpTest() {
        String csnBpId = "csnBp-p4uyr82s0sju";
        BindCsnBpRequest bindCsnBpRequest = BindCsnBpRequest.builder().csnId("csn-b65m2hez6h60iwa1").build();
        String clientToken = UUID.randomUUID().toString();
        client.bindCsnBp(csnBpId, bindCsnBpRequest, clientToken);
    }

    /*    -------------------------- 区域带宽相关 --------------------------    */

    @Test
    public void listCsnBpLimitTest() {
        String csnBpId = "csnBp-p4uyr82s0sju";
        ListCsnBpLimitResponse listCsnBpLimitResponse = client.listCsnBpLimit(csnBpId);
        toJsonPrettyString("list csnBpLimit", listCsnBpLimitResponse);
    }

    @Test
    public void createCsnBpLimitTest() {
        String csnBpId = "csnBp-p4uyr82s0sju";
        CreateCsnBpLimitRequest createCsnBpLimitRequest = CreateCsnBpLimitRequest.builder()
                .localRegion("bj")
                .peerRegion("gz")
                .bandwidth(10)
                .build();
        String clientToken = UUID.randomUUID().toString();
        client.createCsnBpLimit(csnBpId, createCsnBpLimitRequest, clientToken);
    }

    @Test
    public void updateCsnBpLimitTest() {
        String csnBpId = "csnBp-p4uyr82s0sju";
        UpdateCsnBpLimitRequest updateCsnBpLimitRequest = UpdateCsnBpLimitRequest.builder()
                .localRegion("bj")
                .peerRegion("gz")
                .bandwidth(20)
                .build();
        String clientToken = UUID.randomUUID().toString();
        client.updateCsnBpLimit(csnBpId, updateCsnBpLimitRequest, clientToken);
    }

    @Test
    public void deleteCsnBpLimitTest() {
        String csnBpId = "csnBp-p4uyr82s0sju";
        DeleteCsnBpLimitRequest deleteCsnBpLimitRequest = DeleteCsnBpLimitRequest.builder()
                .localRegion("bj")
                .peerRegion("gz")
                .build();
        String clientToken = UUID.randomUUID().toString();
        client.deleteCsnBpLimit(csnBpId, deleteCsnBpLimitRequest, clientToken);
    }

    @Test
    public void listCsnBpLimitByCsnIdTest() {
        String csnId = "csn-b65m2hez6h60iwa1";
        ListCsnBpLimitByCsnIdResponse listCsnBpLimitByCsnIdResponse =
                client.listCsnBpLimitByCsnId(csnId);
        toJsonPrettyString("list csnBpLimit by csnId", listCsnBpLimitByCsnIdResponse);
    }

    /*    -------------------------- TGW相关 --------------------------    */

    @Test
    public void listTgwTest() {
        String csnId = "csn-2qi3430b3vqbcfgd";
        String marker = "";
        Integer maxKeys = 10;
        ListTgwResponse response = client.listTgw(csnId, marker, maxKeys);
        toJsonPrettyString("list tgw", response);
    }

    @Test
    public void updateTgwTest() {
        String csnId = "csn-2qi3430b3vqbcfgd";
        String tgwId = "tgw-5yvyjar8bi32dwru";
        UpdateTgwRequest updateTgwRequest = new UpdateTgwRequest();
        updateTgwRequest.setName("tgw_1");
        updateTgwRequest.setDescription("描述");
        client.updateTgw(csnId, tgwId, updateTgwRequest, null);
    }

    @Test
    public void listTgwRuleTest() {
        String csnId = "csn-2qi3430b3vqbcfgd";
        String tgwId = "tgw-5yvyjar8bi32dwru";
        String marker = "";
        Integer maxKeys = 10;
        ListTgwRuleResponse response = client.listTgwRule(csnId, tgwId, marker, maxKeys);
        toJsonPrettyString("list tgw rule", response);
    }
}
