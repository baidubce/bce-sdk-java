/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.endpoint.model.CreateEndpointRequest;
import com.baidubce.services.endpoint.model.CreateEndpointResponse;
import com.baidubce.services.endpoint.model.Endpoint;
import com.baidubce.services.endpoint.model.ListEndpointRequest;
import com.baidubce.services.endpoint.model.ListEndpointResponse;
import com.baidubce.services.endpoint.model.ModifyEndpointRequest;
import com.baidubce.services.endpoint.model.ReleaseEndpointRequest;
import com.baidubce.services.endpoint.model.ServiceResponse;
import com.baidubce.services.endpoint.model.UpdateEnterpriseSecurityGroups;
import com.baidubce.services.endpoint.model.UpdateSecurityGroups;
import com.baidubce.util.JsonUtils;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

public class EndpointClientTest {
    private static final Logger LOG = LoggerFactory.getLogger(EndpointClientTest.class);
    private static final String ak = "Your Ak";
    private static final String sk = "Your Sk";
    private EndpointClient endpointClient;

    private String vpcId = "vpc-q1hcnhf7nmve";
    private String subnetId = "sbn-crqu2vxzj049";

    @Before
    public void setUp() {
        EndpointClientConfiguration config = new EndpointClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        endpointClient = new EndpointClient(config);
    }

    @Test
    @Ignore
    public void testListService() {
        ServiceResponse response = endpointClient.listService();
        LOG.info(JsonUtils.toJsonString(response));
    }

    @Test
    @Ignore
    public void testCreateEndpoint() {
        CreateEndpointRequest request = new CreateEndpointRequest();
        request.setName("sdk-1");
        request.setVpcId(vpcId);
        request.setSubnetId(subnetId);
        request.setService("service");
        request.setDescription("sdk create");
        request.setIpAddress("");
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        request.setBilling(billing);
        CreateEndpointResponse response = endpointClient.createEndpoint(request);
        LOG.info(JsonUtils.toJsonString(response));
    }

    @Test
    @Ignore
    public void testreleaseEndpoint() {
        ListEndpointRequest listEndpointRequest = new ListEndpointRequest();
        listEndpointRequest.setVpcId(vpcId);
        ListEndpointResponse response = endpointClient.listEndpoint(listEndpointRequest);
        LOG.info(JsonUtils.toJsonString(response.getEndpoints().size()));

        ReleaseEndpointRequest request = new ReleaseEndpointRequest();
        request.setEndpointId(response.getEndpoints().get(0).getEndpointId());
        endpointClient.releaseEndpoint(request);

        response = endpointClient.listEndpoint(listEndpointRequest);
        LOG.info(JsonUtils.toJsonString(response.getEndpoints().size()));
    }

    @Test
    @Ignore
    public void testListEndpoints() {
        ListEndpointRequest request = new ListEndpointRequest();
        request.setVpcId(vpcId);
        ListEndpointResponse response = endpointClient.listEndpoint(request);
        LOG.info(JsonUtils.toJsonString(response));
    }

    @Test
    @Ignore
    public void testGetEndpoint() {
        String endpointId = "endpoint-44dc136d";
        Endpoint endpoint = endpointClient.getEndpoint(endpointId);
        LOG.info(JsonUtils.toJsonString(endpoint));
    }

    @Test
    @Ignore
    public void testModifyEndpoint() {
        String endpointId = "endpoint-44dc136d";
        ModifyEndpointRequest request = new ModifyEndpointRequest();
        request.setEndpointId(endpointId);
        request.setName("sdk-update-2");
        endpointClient.modifyEndpoint(request);
        Endpoint endpoint = endpointClient.getEndpoint(endpointId);
        LOG.info(JsonUtils.toJsonString(endpoint));
    }

    @Test
    public void testUpdateSecurityGroups() {
        UpdateSecurityGroups updateSecurityGroups = new UpdateSecurityGroups();
        updateSecurityGroups.setEndpointId("endpoint-daa07eb2");
        updateSecurityGroups.setSecurityGroupIds(Arrays.asList("g-6r0ds9xbxwes"));
        endpointClient.updateSecurityGroups(updateSecurityGroups);
    }

    @Test
    public void testUpdateEnterpriseSecurityGroups() {
        UpdateEnterpriseSecurityGroups updateSecurityGroups = new UpdateEnterpriseSecurityGroups();
        updateSecurityGroups.setEndpointId("endpoint-daa07eb2");
        updateSecurityGroups.setEnterpriseSecurityGroupIds(Arrays.asList("esg-6q04xad5xmh6"));
        endpointClient.updateEnterpriseSecurityGroups(updateSecurityGroups);
    }
}
