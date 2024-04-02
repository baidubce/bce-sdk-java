/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.havip.model.BindEipRequest;
import com.baidubce.services.havip.model.BindInstanceRequest;
import com.baidubce.services.havip.model.CreateHaVipRequest;
import com.baidubce.services.havip.model.CreateHaVipResponse;
import com.baidubce.services.havip.model.UnBindEipRequest;
import com.baidubce.services.havip.model.UnBindInstanceRequest;
import com.baidubce.services.havip.model.UpdateHaVipRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by zhangjing60 on 17/8/10.
 */
public class HaVipClientTest {
    private static final Logger logger = LoggerFactory.getLogger(com.baidubce.services.subnet.SubnetClientTest.class);
    private static final String ak = "359117df989c4a3db52289bbb8d286fb";
    private static final String sk = "9dd6aaedd58f43b396f99204c7025cd6";

    protected HaVipClient haVipClient;

    @Before
    public void setUp() {
        HaVipClientConfiguration config = new HaVipClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        haVipClient = new HaVipClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListHaVip() {
        toJsonPrettyString("list haVip Results", haVipClient.listHaVip("vpc-b9ycwxxisrb7"));
    }

    @Test
    public void testGetHaVip() {
        toJsonPrettyString("list haVip Results", haVipClient.getHaVip("havip-mqr0g8q7dd74"));
    }

    @Test
    public void testCreateHaVip() {
        CreateHaVipRequest createHaVipRequest = new CreateHaVipRequest();
        createHaVipRequest.setSubnetId("sbn-6ha6gp1vczuv");
        createHaVipRequest.setDescription("desc");
        createHaVipRequest.setName("havip");
        createHaVipRequest.setPrivateIpAddress("");

        CreateHaVipResponse createHaVipResponse = haVipClient.createHaVip(createHaVipRequest);
        toJsonPrettyString("createHaVipResponse is ",createHaVipResponse);
    }

    @Test
    public void testUpdateHaVip() {
        UpdateHaVipRequest updateHaVipRequest = new UpdateHaVipRequest();
        updateHaVipRequest.setHaVipId("havip-mqr0g8q7dd74");
        updateHaVipRequest.setDescription("desc");
        updateHaVipRequest.setName("name");
        haVipClient.updateHaVip(updateHaVipRequest);
    }

    @Test
    public void testBindInstance() {
        BindInstanceRequest bindInstanceRequest = new BindInstanceRequest();
        bindInstanceRequest.setHaVipId("havip-mqr0g8q7dd74");
        bindInstanceRequest.setInstanceType("ENI");
        bindInstanceRequest.setInstanceIds(Arrays.asList("eni-wjz6683jaswn"));
        haVipClient.bindInstance(bindInstanceRequest);
    }

    @Test
    public void testUnBindInstance() {
        UnBindInstanceRequest unBindInstanceRequest = new UnBindInstanceRequest();
        unBindInstanceRequest.setHaVipId("havip-mqr0g8q7dd74");
        unBindInstanceRequest.setInstanceType("ENI");
        unBindInstanceRequest.setInstanceIds(Arrays.asList("eni-wjz6683jaswn"));
        haVipClient.unBindInstance(unBindInstanceRequest);
    }

    @Test
    public void bindEip() {
        BindEipRequest bindEipRequest = new BindEipRequest();
        bindEipRequest.setHaVipId("havip-mqr0g8q7dd74");
        bindEipRequest.setPublicIpAddress("100.88.4.54");
        haVipClient.bindEip(bindEipRequest);
    }

    @Test
    public void unBindEip() {
        UnBindEipRequest unBindEipRequest = new UnBindEipRequest();
        unBindEipRequest.setHaVipId("havip-mqr0g8q7dd74");
        haVipClient.unBindEip(unBindEipRequest);
    }

    @Test
    public void deleteHaVip(){
        haVipClient.deleteHaVip("havip-iwyu1bsab4vi");
    }
}
