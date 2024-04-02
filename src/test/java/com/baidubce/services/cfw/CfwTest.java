/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw;


import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bec.BecClientConfiguration;
import com.baidubce.services.cfw.model.BindCfwRequest;
import com.baidubce.services.cfw.model.CreateCfwRequest;
import com.baidubce.services.cfw.model.CreateCfwResponse;
import com.baidubce.services.cfw.model.CreateCfwRuleRequest;
import com.baidubce.services.cfw.model.DeleteCfwRuleRequest;
import com.baidubce.services.cfw.model.DisableCfwRequest;
import com.baidubce.services.cfw.model.EnableCfwRequest;
import com.baidubce.services.cfw.model.GetCfwResponse;
import com.baidubce.services.cfw.model.ListCfwRequest;
import com.baidubce.services.cfw.model.ListCfwResponse;
import com.baidubce.services.cfw.model.ListInstanceRequest;
import com.baidubce.services.cfw.model.ListInstanceResponse;
import com.baidubce.services.cfw.model.UnbindCfwRequest;
import com.baidubce.services.cfw.model.UpdateCfwRequest;
import com.baidubce.services.cfw.model.UpdateCfwRuleRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * unit case
 */
public class CfwTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CfwTest.class);
    private static final String AK = "ak";
    private static final String SK = "sk";
    private CfwClient client;

    @Before
    public void setUp() {
        BecClientConfiguration config = new BecClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("cfw.baidubce.com");
        // config.setProtocol(Protocol.HTTPS);
        client = new CfwClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            LOGGER.info("[{}]==>\n{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createCfwTest() {
        CreateCfwRequest createCfwRequest = new CreateCfwRequest();
        createCfwRequest.setName("cfw_3");
        CreateCfwRequest.CreateRule createRule = new CreateCfwRequest.CreateRule();
        createRule.setIpVersion(4);
        createRule.setPriority(1);
        createRule.setProtocol("TCP");
        createRule.setDirection("in");
        createRule.setSourceAddress("0.0.0.0/0");
        createRule.setSourcePort("0-65535");
        createRule.setDestAddress("0.0.0.0/0");
        createRule.setDestPort("0-65535");
        createRule.setAction("allow");
        createCfwRequest.setCfwRules(Arrays.asList(createRule));
        CreateCfwResponse createCfwResponse = client.createCfw(createCfwRequest);
        toJsonPrettyString("create cfw", createCfwResponse.getCfwId());
    }

    @Test
    public void listCfwTest() {
        ListCfwRequest listCfwRequest = new ListCfwRequest();
        listCfwRequest.setMarker("cfw-xxxxxxxxxxxx");
        listCfwRequest.setMaxKeys(1);
        ListCfwResponse listCfwResponse = client.listCfw(listCfwRequest);
        toJsonPrettyString("list cfw", listCfwResponse);
    }

    @Test
    public void getCfwTest() {
        GetCfwResponse cfwResponse = client.getCfw("cfw-xxxxxxxxxxxx");
        toJsonPrettyString("get cfw", cfwResponse);
    }

    @Test
    public void updateCfwTest() {
        UpdateCfwRequest updateCfwRequest = new UpdateCfwRequest();
        updateCfwRequest.setName("cfw_5");
        updateCfwRequest.setDescription("desc");
        client.updateCfw("cfw-xxxxxxxxxxxx", updateCfwRequest);
    }

    @Test
    public void deleteCfwTest() {
        client.deleteCfw("cfw-xxxxxxxxxxxx");
    }

    @Test
    public void createCfwRuleTest() {
        CreateCfwRuleRequest createCfwRuleRequest = new CreateCfwRuleRequest();
        CreateCfwRuleRequest.CreateRule createRule = new CreateCfwRuleRequest.CreateRule();
        createRule.setIpVersion(4);
        createRule.setPriority(2);
        createRule.setProtocol("TCP");
        createRule.setDirection("in");
        createRule.setSourceAddress("192.168.1.1");
        createRule.setSourcePort("0-65535");
        createRule.setDestAddress("192.168.1.2");
        createRule.setDestPort("0-65535");
        createRule.setAction("allow");
        createRule.setDescription("extra");
        createCfwRuleRequest.setCfwRules(Arrays.asList(createRule));
        client.createCfwRule("cfw-xxxxxxxxxxxx", createCfwRuleRequest);
    }

    @Test
    public void updateCfwRuleTest() {
        UpdateCfwRuleRequest updateCfwRuleRequest = new UpdateCfwRuleRequest();
        updateCfwRuleRequest.setSourceAddress("192.168.1.4");
        updateCfwRuleRequest.setSourcePort("0-65535");
        client.updateCfwRule("cfw-xxxxxxxxxxxx", "cfwr-xxxxxxxxxxxx", updateCfwRuleRequest);
    }

    @Test
    public void deleteCfwRuleTest() {
        DeleteCfwRuleRequest deleteCfwRuleRequest = new DeleteCfwRuleRequest();
        deleteCfwRuleRequest.setCfwRuleIds(Arrays.asList("cfwr-xxxxxxxxxxxx", "cfwr-xxxxxxxxxxxx"));
        client.deleteCfwRule("cfw-xxxxxxxxxxxx", deleteCfwRuleRequest);
    }

    @Test
    public void listInstanceTest() {
        ListInstanceRequest listInstanceRequest = new ListInstanceRequest();
        listInstanceRequest.setInstanceType("csn");
        listInstanceRequest.setMaxKeys(2);
        ListInstanceResponse listInstance = client.listInstance(listInstanceRequest);
        toJsonPrettyString("list instance", listInstance);
    }

    @Test
    public void bindCfwTest() {
        BindCfwRequest bindCfwRequest = new BindCfwRequest();
        bindCfwRequest.setInstanceType("csn");
        BindCfwRequest.CfwBind cfwBind = new BindCfwRequest.CfwBind();
        cfwBind.setInstanceId("csn-xxxxxxxxxxxx");
        cfwBind.setMemberId("vpc-xxxxxxxxxxxx");
        cfwBind.setRegion("bj");
        bindCfwRequest.setInstances(Arrays.asList(cfwBind));
        client.bindCfw("cfw-xxxxxxxxxxxx", bindCfwRequest);
    }

    @Test
    public void unbindCfwTest() {
        UnbindCfwRequest unbindCfwRequest = new UnbindCfwRequest();
        unbindCfwRequest.setInstanceType("csn");
        UnbindCfwRequest.CfwBind cfwBind = new UnbindCfwRequest.CfwBind();
        cfwBind.setInstanceId("csn-xxxxxxxxxxxx");
        cfwBind.setMemberId("vpc-xxxxxxxxxxxx");
        cfwBind.setRegion("bj");
        unbindCfwRequest.setInstances(Arrays.asList(cfwBind));
        client.unbindCfw("cfw-xxxxxxxxxxxx", unbindCfwRequest);
    }

    @Test
    public void enableCfwTest() {
        EnableCfwRequest enableCfwRequest = new EnableCfwRequest();
        enableCfwRequest.setInstanceId("csn-xxxxxxxxxxxx");
        enableCfwRequest.setMemberId("vpc-xxxxxxxxxxxx");
        client.enableCfw("cfw-xxxxxxxxxxxx", enableCfwRequest);
    }

    @Test
    public void disableCfwTest() {
        DisableCfwRequest disableCfwRequest = new DisableCfwRequest();
        disableCfwRequest.setInstanceId("csn-xxxxxxxxxxxx");
        disableCfwRequest.setMemberId("vpc-xxxxxxxxxxxx");
        client.disableCfw("cfw-xxxxxxxxxxxx", disableCfwRequest);
    }
}
