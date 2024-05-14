/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.AutoRenewEipRequest;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eip.model.BindEipRequest;
import com.baidubce.services.eip.model.CreateEipRequest;
import com.baidubce.services.eip.model.CreateEipResponse;
import com.baidubce.services.eip.model.DirectEipRequest;
import com.baidubce.services.eip.model.ListEipsRequest;
import com.baidubce.services.eip.model.ListEipsResponse;
import com.baidubce.services.eip.model.ListRecycleEipsRequest;
import com.baidubce.services.eip.model.ListRecycleEipsResponse;
import com.baidubce.services.eip.model.OptionalReleaseEipRequest;
import com.baidubce.services.eip.model.PurchaseReservedEipRequest;
import com.baidubce.services.eip.model.RecycleOperateEipRequest;
import com.baidubce.services.eip.model.ReleaseEipRequest;
import com.baidubce.services.eip.model.ResizeEipRequest;
import com.baidubce.services.eip.model.StopAutoRenewEipRequest;
import com.baidubce.services.eip.model.UnbindEipRequest;
import com.baidubce.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * eipClient unit test
 */
public class EipClientTest {
    private static final Logger logger = LoggerFactory.getLogger(EipClientTest.class);
    private static final String EIP_AK = "";
    private static final String EIP_SK = "";

    protected EipClient eipClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(EIP_AK, EIP_SK));
        config.setEndpoint("http://eip.api-sandbox.baidu.com");
        eipClient = new EipClient(config);
    }

    @Test
    public void createEipTest() {
        CreateEipRequest request = new CreateEipRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setBandwidthInMbps(1);
        request.setName("cdhTest");
        Billing billing = new Billing();
        billing.setPaymentTiming("");
        billing.setBillingMethod("ByBandwidth");
        request.setBilling(billing);

        CreateEipResponse response = this.eipClient.createEip(request);
        // logger.info(JsonUtils.toJsonString(response));
    }

    public void resizeEipTest() {
        ResizeEipRequest request = new ResizeEipRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEip("10.107.245.112");
        request.setNewBandwidthInMbps(2);

        this.eipClient.resizeEip(request);
    }

    public void purchaseReservedEipTest() {
        PurchaseReservedEipRequest request = new PurchaseReservedEipRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEip("10.107.245.97");
        Billing billing = new Billing();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        billing.setReservation(reservation);
//        request.setBilling(billing);

        this.eipClient.purchaseReservedEip(request);
    }

    public void bindEipTest() {
        BindEipRequest request = new BindEipRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEip("10.107.245.97");
        request.setInstanceId("i-VPKFiIGz");
        request.setInstanceType("BCC");

        this.eipClient.bindEip(request);
    }

    public void unbindEipTest() {
        UnbindEipRequest request = new UnbindEipRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEip("10.107.245.97");

        this.eipClient.unbindEip(request);
    }

    public void releaseEipTest() {
        ReleaseEipRequest request = new ReleaseEipRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEip("10.107.245.112");

        this.eipClient.releaseEip(request);
    }

    @Test
    public void listEipsTest() {
        ListEipsRequest request = new ListEipsRequest();
//        request.setInstanceId("i-VPKFiIGz");
//        request.setInstanceType("BCC");
//        request.setEip("10.107.246.253");
        ListEipsResponse response = this.eipClient.listEips(request);
        logger.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void autoRenewEipTest() {
        AutoRenewEipRequest request = new AutoRenewEipRequest();
        request.withEip("100.88.11.128").withAutoRenewTimeUnit("month").withAutoRenewTime(1);
        this.eipClient.startAutoRenew(request);
    }

    @Test
    public void stopAutoRenewEipTest() {
        StopAutoRenewEipRequest request = new StopAutoRenewEipRequest();
        request.withEip("100.88.11.128");
        this.eipClient.stopAutoRenew(request);
    }

    @Test
    public void directEipTest() {
        DirectEipRequest request = new DirectEipRequest();
        request.withEip("100.88.11.128");
        this.eipClient.directEip(request);
    }

    @Test
    public void unDirectEipTest() {
        DirectEipRequest request = new DirectEipRequest();
        request.withEip("100.88.11.128");
        this.eipClient.unDirectEip(request);
    }

    @Test
    public void listRecycleEipsTest() {
        ListRecycleEipsRequest request = new ListRecycleEipsRequest();
        ListRecycleEipsResponse response = this.eipClient.listRecycleEips(request);
        logger.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void optionalReleaseEipTest() {
        OptionalReleaseEipRequest request = new OptionalReleaseEipRequest();
        request.setEip("100.88.7.75");
        request.setReleaseToRecycle(true);
        eipClient.optionalReleaseEip(request);
    }


    @Test
    public void releaseEipFromRecycleTest() {
        RecycleOperateEipRequest request = new RecycleOperateEipRequest();
        request.setEip("100.88.7.75");
        eipClient.releaseEipFromRecycle(request);
    }

    @Test
    public void restoreEipFromRecycleTest() {
        RecycleOperateEipRequest request = new RecycleOperateEipRequest();
        request.setEip("192.168.225.3");
        eipClient.restoreEipFromRecycle(request);
    }

}
