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
package com.baidubce.services.nat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.Reservation;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.nat.model.BatchAddDnatRulesRequest;
import com.baidubce.services.nat.model.BatchAddSnatRuleRequest;
import com.baidubce.services.nat.model.BindDnatEipRequest;
import com.baidubce.services.nat.model.BindEipRequest;
import com.baidubce.services.nat.model.CreateDnatRule;
import com.baidubce.services.nat.model.CreateDnatRuleRequest;
import com.baidubce.services.nat.model.CreateNatRequest;
import com.baidubce.services.nat.model.CreateNatResponse;
import com.baidubce.services.nat.model.CreateSnatRuleRequest;
import com.baidubce.services.nat.model.DeleteNatRuleRequest;
import com.baidubce.services.nat.model.GetNatResponse;
import com.baidubce.services.nat.model.ListNatRequest;
import com.baidubce.services.nat.model.ListNatResponse;
import com.baidubce.services.nat.model.ListNatRuleRequest;
import com.baidubce.services.nat.model.ModifyNatRequest;
import com.baidubce.services.nat.model.PurchaseReservedNatRequest;
import com.baidubce.services.nat.model.ReleaseNatRequest;
import com.baidubce.services.nat.model.UpdateDnatRuleRequest;
import com.baidubce.services.nat.model.UpdateSnatRuleRequest;
import com.baidubce.util.JsonUtils;

/**
 * NatClient test
 */
public class NatClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(NatClientTest.class);
    private static final String AK = "";
    private static final String SK = "";
    private NatClient natClient;

    private String natId = "nat-bfgn9g9xxse8";

    private String vpcId = "vpc-mivwn2w9xicb";

    private String eip = "10.107.245.58";

    private static final String MODIFY_NAME = "modify";

    @Before
    public void setUp() {
        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        natClient = new NatClient(config);
    }

    @Test
    public void testCreateNat() {
        CreateNatRequest request = new CreateNatRequest();
        Reservation reservation = new Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        billing.setReservation(reservation);
        request.setBilling(billing);
        // request.setEips();
        request.setName("NatTest");
        request.setSpec("small");
        request.setVpcId(vpcId);
        List<TagModel> tags = new ArrayList<TagModel>();
        tags.add(new TagModel().withTagKey("testKey").withTagValue("testValue"));
        request.setTags(tags);
        CreateNatResponse createNatResponse = natClient.createNat(request);
        natId = createNatResponse.getNatId();
        LOG.info("NatId is " + natId);
    }

    @Test
    public void testListNat() {
        ListNatRequest request = new ListNatRequest();
        request.setVpcId(vpcId);
        ListNatResponse response = natClient.listNat(request);
        LOG.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetNat() {
        GetNatResponse response = natClient.getNat(natId);
        LOG.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void testModify() {
        ModifyNatRequest request = new ModifyNatRequest();
        request.setNatId(natId);
        request.setName(MODIFY_NAME);
        natClient.modifyNat(request);
        GetNatResponse response = natClient.getNat(natId);
        LOG.info(response.getName());
        Assert.assertEquals(MODIFY_NAME, response.getName());
    }

    @Test
    public void testBind() {
        BindEipRequest request = new BindEipRequest();
        request.setEips(Arrays.asList(eip));
        request.setNatId(natId);
        natClient.bindEip(request);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GetNatResponse response = natClient.getNat(natId);
        LOG.info(JsonUtils.toJsonString(response));
        Assert.assertEquals(eip, response.getEips().get(0));
    }

    @Test
    public void testUnbind() {
        BindEipRequest request = new BindEipRequest();
        request.setEips(Arrays.asList(eip));
        request.setNatId(natId);
        natClient.unbindEip(request);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GetNatResponse response = natClient.getNat(natId);
        LOG.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void testPurchaseReserved() {
        PurchaseReservedNatRequest request = new PurchaseReservedNatRequest();
        request.setNatId(natId);
        Billing billing = new Billing();
        Reservation reservation = new Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        billing.setReservation(reservation);
        request.setBilling(billing);
        natClient.purchaseReservedNat(request);
    }

    @Test
    public void testRelease() {
        ReleaseNatRequest request = new ReleaseNatRequest();
        request.setNatId(natId);
        natClient.releaseNat(request);
    }

    @Test
    public void testBindDnatEip() {
        BindDnatEipRequest request = new BindDnatEipRequest();
        request.setNatId(natId);
        request.setDnatEips(Arrays.asList("180.76.173.115"));
        natClient.bindDnatEip(request);
    }

    @Test
    public void testUnbindDnatEip() {
        BindDnatEipRequest request = new BindDnatEipRequest();
        request.setNatId(natId);
        List<String> dnatEips = new ArrayList<String>();
        dnatEips.add("180.76.173.115");
        request.setDnatEips(dnatEips);
        natClient.unbindDnatEip(request);
    }

    @Test
    public void testCreateSnatRule(){
        CreateSnatRuleRequest request = new CreateSnatRuleRequest();
        request.setNatId(natId);
        request.setRuleName("rule2");
        request.setSourceCIDR("172.16.0.64/29");
        request.setPublicIpsAddress(Arrays.asList("180.76.169.179"));
        LOG.info(JsonUtils.toJsonString(natClient.createSnatRule(request)));
    }

    @Test
    public void testDeleteSnatRule(){
        DeleteNatRuleRequest request = new DeleteNatRuleRequest();
        request.setNatId(natId);
        request.setRuleId("rule-2z93chy4m3ew");
        natClient.deleteSnatRule(request);
    }

    @Test
    public void testUpdateSnatRule(){
        UpdateSnatRuleRequest request = new UpdateSnatRuleRequest();
        request.setNatId(natId);
        request.setRuleId("rule-5zqkydxp8sgw");
        request.setRuleName("rule2");
        request.setSourceCIDR("172.16.0.64/29");
        request.setPublicIpsAddress(Arrays.asList("180.76.169.179"));
        natClient.updateSnatRule(request);
    }

    @Test
    public void testListSnatRule1(){
        LOG.info(JsonUtils.toJsonString(natClient.listSnatRule("nat-btdt4cd8ends")));
    }

    @Test
    public void testListSnatRule2(){
        ListNatRuleRequest request = new ListNatRuleRequest();
        request.setNatId("nat-btdt4cd8ends");
        LOG.info(JsonUtils.toJsonString(natClient.listSnatRule(request)));
    }

    @Test
    public void testCreateDnatRule(){
        CreateDnatRuleRequest request = new CreateDnatRuleRequest();
        request.setNatId(natId);
        request.setRuleName("ruleTest");
        request.setPublicIpAddress("180.76.173.115");
        request.setPrivateIpAddress("172.16.0.100");
        request.setProtocol("all");
        LOG.info(JsonUtils.toJsonString(natClient.createDnatRule(request)));
    }

    @Test
    public void testDeleteDnatRule(){
        DeleteNatRuleRequest request = new DeleteNatRuleRequest();
        request.setNatId(natId);
        request.setRuleId("rule-dufifxtj45ue");
        natClient.deleteDnatRule(request);
    }

    @Test
    public void testUpdateDnatRule(){
        UpdateDnatRuleRequest request = new UpdateDnatRuleRequest();
        request.setNatId(natId);
        request.setRuleId("rule-p42cc31h6exc");
        request.setRuleName("rule2");
        request.setProtocol("UDP");
        request.setPrivatePort(80);
        request.setPublicPort(80);
        request.setPrivateIpAddress("172.16.0.101");
        natClient.updateDnatRule(request);
    }

    @Test
    public void testListDnatRule1(){
        LOG.info(JsonUtils.toJsonString(natClient.listDnatRule("nat-btdt4cd8ends")));
    }

    @Test
    public void testListDnatRule2() {
        ListNatRuleRequest request = new ListNatRuleRequest();
        request.setMaxKeys(10);
        request.setNatId("nat-btdt4cd8ends");
        LOG.info(JsonUtils.toJsonString(natClient.listDnatRule(request)));
    }

    @Test
    public void batchCreateSnatRule() {
        BatchAddSnatRuleRequest batchAddSnatRuleRequest = new BatchAddSnatRuleRequest();
        batchAddSnatRuleRequest.setNatId("nat-bmnbjkvm8h24");
        BatchAddSnatRuleRequest.CreateSnatRule createSnatRule = new BatchAddSnatRuleRequest.CreateSnatRule();
        createSnatRule.setRuleName("name");
        createSnatRule.setSourceCIDR("192.168.0.0/24");
        createSnatRule.setPublicIpsAddress(Arrays.asList("100.88.0.51"));
        batchAddSnatRuleRequest.setSnatRules(Arrays.asList(createSnatRule));
        natClient.batchAddSnatRules(batchAddSnatRuleRequest);
    }

    @Test
    public void batchCreateDnatRule() {
        BatchAddDnatRulesRequest batchAddDnatRulesRequest = new BatchAddDnatRulesRequest();
        batchAddDnatRulesRequest.setNatId("nat-bmnbjkvm8h24");
        CreateDnatRule createDnatRule = new CreateDnatRule();
        createDnatRule.setRuleName("aa");
        createDnatRule.setProtocol("TCP");
        createDnatRule.setPrivatePort(8990);
        createDnatRule.setPrivateIpAddress("192.168.0.25");
        createDnatRule.setPublicIpAddress("100.88.10.213");
        createDnatRule.setPublicPort(8991);
        batchAddDnatRulesRequest.setRules(Arrays.asList(createDnatRule));
        natClient.batchAddDnatRules(batchAddDnatRulesRequest);
    }
}
