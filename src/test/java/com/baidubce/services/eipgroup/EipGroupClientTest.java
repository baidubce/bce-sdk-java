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
package com.baidubce.services.eipgroup;

import com.baidubce.services.eipgroup.model.EipGroupOperateRequest;
import com.baidubce.services.eipgroup.model.MoveInRequest;
import com.baidubce.services.eipgroup.model.MoveOutRequest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eipgroup.model.BandwidthInMbpsRequest;
import com.baidubce.services.eipgroup.model.CreateEipGroupRequest;
import com.baidubce.services.eipgroup.model.EipCountRequest;
import com.baidubce.services.eipgroup.model.EipNameRequest;
import com.baidubce.services.eipgroup.model.GetEipGroupRequest;
import com.baidubce.services.eipgroup.model.GetEipGroupResponse;
import com.baidubce.services.eipgroup.model.IdResponse;
import com.baidubce.services.eipgroup.model.ListEipGroupRequest;
import com.baidubce.services.eipgroup.model.ListEipGroupResponse;
import com.baidubce.services.eipgroup.model.PurchaseReservedEipGroupRequest;
import com.baidubce.util.JsonUtils;

import java.util.Arrays;
import java.util.List;

/**
 * EipGroupClient test
 *
 */
public class EipGroupClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(EipGroupClientTest.class);
    private static final String AK = "";
    private static final String SK = "";
    private EipGroupClient client;

    private String egId = "eg-7e358ead";

    @Before
    public void setUp() {
        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("eip.api-sandbox.baidu.com");
        client = new EipGroupClient(config);
    }

    @Test
    public void testCreate() {
        CreateEipGroupRequest request = new CreateEipGroupRequest();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("Month");
        Billing billing = new Billing();
        billing.setPaymentTiming("Prepaid");
        billing.setBillingMethod("ByTraffic");
        billing.setReservation(reservation);
        request.setBilling(billing);
        request.setName("EipGroupTest");
        request.setEipCount(2);
        request.setBandwidthInMbps(10);
        LOG.info("request is " + JsonUtils.toJsonString(request));

        IdResponse idResponse = client.createEipGroup(request);
        egId = idResponse.getId();
        LOG.info("egId is " + egId);
    }

    @Test
    public void testResizeBandwidth() {
        BandwidthInMbpsRequest request = new BandwidthInMbpsRequest();
        request.setBandwidthInMbps(20);
        request.setId(egId);
        client.resizeBandwidth(request);

        GetEipGroupRequest request1 = new GetEipGroupRequest();
        request1.setId(egId);
        GetEipGroupResponse getEipGroupResponse = client.getEipGroup(request1);
        LOG.info(JsonUtils.toJsonString(getEipGroupResponse));
    }

    @Test
    public void testAddCount() {
        EipCountRequest request = new EipCountRequest();
        request.setEipAddCount(2);
        request.setId(egId);
        client.addCount(request);

        GetEipGroupRequest request1 = new GetEipGroupRequest();
        request1.setId(egId);
        GetEipGroupResponse getEipGroupResponse = client.getEipGroup(request1);
        LOG.info(JsonUtils.toJsonString(getEipGroupResponse));
    }

    @Test
    public void testUpdateName() {
        EipNameRequest request = new EipNameRequest();
        request.setName("updateName");
        request.setId(egId);
        client.update(request);

        GetEipGroupRequest request1 = new GetEipGroupRequest();
        request1.setId(egId);
        GetEipGroupResponse getEipGroupResponse = client.getEipGroup(request1);
        LOG.info(JsonUtils.toJsonString(getEipGroupResponse));
    }

    @Test
    public void testList() {
        ListEipGroupRequest request = new ListEipGroupRequest();
        request.setName("updateName");
        ListEipGroupResponse listEipGroupResponse = client.listEipGroup(request);
        LOG.info(JsonUtils.toJsonString(listEipGroupResponse));
    }

    @Test
    public void testPurchaseReserved() {
        PurchaseReservedEipGroupRequest request = new PurchaseReservedEipGroupRequest();
        request.setId(egId);
        Billing billing = new Billing();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        billing.setReservation(reservation);
        request.setBilling(billing);
        client.purchaseReservedEipGroup(request);

        GetEipGroupRequest request1 = new GetEipGroupRequest();
        request1.setId(egId);
        GetEipGroupResponse getEipGroupResponse = client.getEipGroup(request1);
        LOG.info(JsonUtils.toJsonString(getEipGroupResponse));
    }

    @Test
    public void testReleaseEipGroup() {
        EipGroupOperateRequest request = new EipGroupOperateRequest();
        request.setId("eg-aXT5LIDG");
        client.releaseEipGroup(request);
    }

    @Test
    public void testMoveOutEips() {
        MoveOutRequest request = new MoveOutRequest();
        request.setId("eg-8yxeMV47");
        MoveOutRequest.EipMoveOutModel moveOutModel = new MoveOutRequest.EipMoveOutModel();
        moveOutModel.setEip("100.88.9.177");
        moveOutModel.setBandwidthInMbps(100);
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        billing.setBillingMethod("ByTraffic");
        moveOutModel.setBilling(billing);
        request.setMoveOutEips(Arrays.asList(moveOutModel));
        client.moveOutEips(request);
    }

    @Test
    public void testMoveInEips() {
        MoveInRequest request = new MoveInRequest();
        request.setId("eg-8yxeMV47");
        List<String> eips = Arrays.asList("100.88.9.177");
        request.setEips(eips);
        client.moveInEips(request);
    }

}
