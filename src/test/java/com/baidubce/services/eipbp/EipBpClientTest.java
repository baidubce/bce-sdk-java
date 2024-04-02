/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipbp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eipbp.model.CreateEipBpRequest;
import com.baidubce.services.eipbp.model.CreateEipBpResponse;
import com.baidubce.services.eipbp.model.EipBpDetailResponse;
import com.baidubce.services.eipbp.model.GetEipBpRequest;
import com.baidubce.services.eipbp.model.ListEipBpsResponse;
import com.baidubce.util.JsonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * eipClient unit test
 */
public class EipBpClientTest {
    private static final Logger logger = LoggerFactory.getLogger(EipBpClientTest.class);
    private static final String EIP_AK = "";
    private static final String EIP_SK = "";
    private static final String endpoint = "";

    protected EipBpClient eipBpClient;

    private String eipBpId;
    private Integer createBandwidthInMbps = 1;
    private Integer resizeBandwidthInMbps = 2;
    private String name = "test";
    private String newName = "test1";
    private String eip = "";
    private String autoReleaseTime = "2020-07-20T00:00:00Z";
    private String newAutoReleaseTime = "2020-07-23T12:00:00Z";
    private String bindType = "eip";
    private String type = "BandwidthPackage";


    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(EIP_AK, EIP_SK));
        config.setEndpoint(endpoint);
        eipBpClient = new EipBpClient(config);
    }

    public void createEipBpTest() {
        CreateEipBpRequest request = new CreateEipBpRequest();
        request.withBandwidthInMbps(createBandwidthInMbps).withName(name).withEip(eip).
                withAutoReleaseTime(autoReleaseTime);
        CreateEipBpResponse response = eipBpClient.createEipBp(request);
        logger.info(JsonUtils.toJsonString(response));
        eipBpId = response.getId();
    }


    public void getEipBpDetailTest() throws Exception {
        do {
            GetEipBpRequest request = new GetEipBpRequest();
            request.setId(eipBpId);
            request.withId(eipBpId);
            try {
                logger.info(JsonUtils.toJsonString(eipBpClient.getEipBpDetail(request)));
                EipBpDetailResponse eipBpDetailResponse = eipBpClient.getEipBpDetail(eipBpId);
                if (eipBpDetailResponse.getBandwidthInMbps() == createBandwidthInMbps) {
                    return;
                }
            } catch (Exception e) {
                Thread.sleep(5000);
            }
        } while (true);
    }


    public void resizeEipBpTest() throws Exception {
        eipBpClient.resizeEipBp(eipBpId, resizeBandwidthInMbps);
        do {
            try {
                EipBpDetailResponse eipBpDetailResponse = eipBpClient.getEipBpDetail(eipBpId);
                if (eipBpDetailResponse.getBandwidthInMbps() == resizeBandwidthInMbps) {
                    return;
                }
            } catch (Exception e) {
                Thread.sleep(5000);
            }
        } while (true);
    }

    public void listEipBpsTest() {
        logger.info(JsonUtils.toJsonString(eipBpClient.listEipBps()));
        ListEipBpsResponse listEipBpsResponse = eipBpClient.listEipBps(eipBpId, name, bindType, type);
        Assert.assertEquals(listEipBpsResponse.getBpList().size(), 1);
    }

    public void renameEipBpTest() {
        eipBpClient.renameEipBp(eipBpId, newName);
        Assert.assertEquals(eipBpClient.getEipBpDetail(eipBpId).getName(), newName);
    }

    public void updateAutoReleaseTimeTest() {
        eipBpClient.updateAutoReleaseTime(eipBpId, newAutoReleaseTime);
        Assert.assertEquals(eipBpClient.getEipBpDetail(eipBpId).getAutoReleaseTime(), newAutoReleaseTime);
    }

    public void releaseEipBpTest() {
        eipBpClient.releaseEipBp(eipBpId);
    }

    @Test
    public void testEipBpClient() throws Exception {
        createEipBpTest();
        getEipBpDetailTest();
        resizeEipBpTest();
        listEipBpsTest();
        renameEipBpTest();
        updateAutoReleaseTimeTest();
        releaseEipBpTest();
    }
}
