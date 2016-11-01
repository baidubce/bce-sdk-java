/*
 * Copyright 2016 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothub;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;

import com.baidubce.services.iothub.model.BaseResponse;
import com.baidubce.services.iothub.model.CreatePrincipalResponse;
import com.baidubce.services.iothub.model.ListPermissionResponse;
import com.baidubce.services.iothub.model.ListResponse;
import com.baidubce.services.iothub.model.Operation;
import com.baidubce.services.iothub.model.QueryEndpointResponse;
import com.baidubce.services.iothub.model.QueryPermissionResponse;
import com.baidubce.services.iothub.model.QueryThingResponse;
import com.baidubce.services.iothub.model.QueryPolicyResponse;
import com.baidubce.services.iothub.model.QueryPrincipalResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Unit test for iot hub client.
 */

public class IotHubClientTest {

    // qa-sandbox
    private static final String AK = "a6efb01da029490e9dd9e9605698f36c";
    private static final String SK = "6db5c95fd8504352b2da1e2afe52b59e";
    private static final String ENDPOINT = "http://nmg01-hpc-w1134.nmg01.baidu.com:8004";

    private static final String TEST_ENDPOINT_NAME = "sdk_test_endpoint_01";
    private static final String TEST_THING_NAME = "sdk_test_thing_01";
    private static final String TEST_PRINCIPAL_NAME = "sdk_test_principal_01";
    private static final String TEST_POLICY_NAME = "sdk_test_policy_01";

    private static final String TEST_TOPIC = "abc";

    private IotHubClient iotHubClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        iotHubClient = new IotHubClient(config);
    }

    @Test
    public void endpointTest() throws Exception {
        QueryEndpointResponse responseQuery = iotHubClient.createEndpoint(TEST_ENDPOINT_NAME);
        assertThat(responseQuery.getEndpointName(), is(TEST_ENDPOINT_NAME));
        assertThat(responseQuery, hasProperty("websocketHostname"));
        assertThat(responseQuery, hasProperty("mqttHostname"));
        assertThat(responseQuery, hasProperty("mqttTlsHostname"));
        assertThat(responseQuery, hasProperty("accountUuid"));

        ListResponse responseList = iotHubClient.listEndpoints();
        assertThat(responseList.getResult(), is(notNullValue()));
        assertThat(responseList.getTotalCount(), is(1));

        responseQuery = iotHubClient.queryEndpoint(TEST_ENDPOINT_NAME);
        assertThat(responseQuery.getEndpointName(), is(TEST_ENDPOINT_NAME));
        assertThat(responseQuery, hasProperty("websocketHostname"));
        assertThat(responseQuery, hasProperty("mqttHostname"));
        assertThat(responseQuery, hasProperty("mqttTlsHostname"));
        assertThat(responseQuery, hasProperty("accountUuid"));

        BaseResponse responseBase = iotHubClient.deleteEndpoint(TEST_ENDPOINT_NAME);
        assertThat(responseBase.getMetadata().getContentLength(), is(-1L));

        responseList = iotHubClient.listEndpoints();
        assertThat(responseList.getTotalCount(), is(0));
    }

    @Test
    public void thingTest() throws Exception {
        iotHubClient.createEndpoint(TEST_ENDPOINT_NAME);
        QueryThingResponse responseQuery = iotHubClient.createThing(TEST_ENDPOINT_NAME, TEST_THING_NAME);
        assertThat(responseQuery.getThingName(), is(TEST_THING_NAME));

        ListResponse responseList = iotHubClient.listThings(TEST_ENDPOINT_NAME);
        assertThat(responseList.getResult(), is(notNullValue()));
        assertThat(responseList.getTotalCount(), is(1));

        responseQuery = iotHubClient.queryThing(TEST_ENDPOINT_NAME, TEST_THING_NAME);
        assertThat(responseQuery.getThingName(), is(TEST_THING_NAME));

        BaseResponse responseBase = iotHubClient.deleteThing(TEST_ENDPOINT_NAME, TEST_THING_NAME);
        assertThat(responseBase.getMetadata().getContentLength(), is(-1L));

        responseList = iotHubClient.listThings(TEST_ENDPOINT_NAME);
        assertThat(responseList.getTotalCount(), is(0));
        iotHubClient.deleteEndpoint(TEST_ENDPOINT_NAME);
    }


    @Test
    public void principalTest() throws  Exception {
        iotHubClient.createEndpoint(TEST_ENDPOINT_NAME);
        iotHubClient.createThing(TEST_ENDPOINT_NAME, TEST_THING_NAME);

        CreatePrincipalResponse responseCreate = iotHubClient.createPrincipal(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);
        assertThat(responseCreate.getPrincipalName(), is(TEST_PRINCIPAL_NAME));

        ListResponse responseList = iotHubClient.listPrincipals(TEST_ENDPOINT_NAME);
        assertThat(responseList.getResult(), is(notNullValue()));
        assertThat(responseList.getTotalCount(), is(1));

        responseList = iotHubClient.listPrincipals(TEST_ENDPOINT_NAME, TEST_THING_NAME);
        assertThat(responseList.getTotalCount(), is(0));

        iotHubClient.attachThingToPrincipal(TEST_ENDPOINT_NAME, TEST_THING_NAME, TEST_PRINCIPAL_NAME);
        responseList = iotHubClient.listPrincipals(TEST_ENDPOINT_NAME, TEST_THING_NAME);
        assertThat(responseList.getTotalCount(), is(1));

        responseCreate = iotHubClient.regenerateCert(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME, "cert");
        assertThat(responseCreate.getCert(), is(notNullValue()));

        QueryPrincipalResponse responseQuery = iotHubClient.queryPrincipal(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);
        assertThat(responseQuery.getPrincipalName(), is(TEST_PRINCIPAL_NAME));

        BaseResponse responseBase = iotHubClient.deletePrincipal(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);
        assertThat(responseBase.getMetadata().getContentLength(), is(-1L));

        responseList = iotHubClient.listPrincipals(TEST_ENDPOINT_NAME);
        assertThat(responseList.getTotalCount(), is(0));
        iotHubClient.deleteEndpoint(TEST_ENDPOINT_NAME);
    }

    @Test
    public void policyTest() throws Exception {
        iotHubClient.createEndpoint(TEST_ENDPOINT_NAME);
        iotHubClient.createThing(TEST_ENDPOINT_NAME, TEST_THING_NAME);
        iotHubClient.createPrincipal(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);

        QueryPolicyResponse responseQuery = iotHubClient.createPolicy(TEST_ENDPOINT_NAME, TEST_POLICY_NAME);
        assertThat(responseQuery.getPolicyName(), is(TEST_POLICY_NAME));

        ListResponse responseList = iotHubClient.listPolicy(TEST_ENDPOINT_NAME);
        assertThat(responseList.getResult(), is(notNullValue()));
        assertThat(responseList.getTotalCount(), is(1));

        responseList = iotHubClient.listPolicy(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);
        assertThat(responseList.getTotalCount(), is(0));

        iotHubClient.attachPrincipalToPolicy(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME, TEST_POLICY_NAME);
        responseList = iotHubClient.listPolicy(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);
        assertThat(responseList.getTotalCount(), is(1));

        responseQuery = iotHubClient.queryPolicy(TEST_ENDPOINT_NAME, TEST_POLICY_NAME);
        assertThat(responseQuery.getPolicyName(), is(TEST_POLICY_NAME));

        BaseResponse responseBase = iotHubClient.deletePolicy(TEST_ENDPOINT_NAME, TEST_POLICY_NAME);
        assertThat(responseBase.getMetadata().getContentLength(), is(-1L));

        responseList = iotHubClient.listPolicy(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);
        assertThat(responseList.getTotalCount(), is(0));

        iotHubClient.deleteEndpoint(TEST_ENDPOINT_NAME);
    }

    @Test
    public void permissionTest() throws Exception {
        preCreate();
        List<Operation> operations = new ArrayList<Operation>();
        operations.add(Operation.PUBLISH);
        operations.add(Operation.SUBSCRIBE);
        QueryPermissionResponse response = iotHubClient.createPermission(TEST_ENDPOINT_NAME,
                TEST_POLICY_NAME,
                operations,
                "abc");
        assertThat(response.getPermissionUuid(), is(notNullValue()));
        String permissionUuid = response.getPermissionUuid();
        response = iotHubClient.updatePermission(TEST_ENDPOINT_NAME, permissionUuid, null, TEST_TOPIC);
        assertThat(response.getTopic(), is(TEST_TOPIC));
        ListPermissionResponse listPermissionResponse = iotHubClient.listPermission(TEST_ENDPOINT_NAME,
                TEST_POLICY_NAME);
        assertThat(listPermissionResponse.getTotalCount(), is(1));
        assertThat(listPermissionResponse.getResult().get(0), hasProperty("operations"));
        assertThat(listPermissionResponse.getResult().get(0), hasProperty("topic"));
        assertThat(listPermissionResponse.getResult().get(0), hasProperty("policyUuid"));
        assertThat(listPermissionResponse.getResult().get(0), hasProperty("permissionUuid"));
        QueryPermissionResponse responseQuery = iotHubClient.queryPermission(TEST_ENDPOINT_NAME, permissionUuid);
        assertThat(responseQuery.getPermissionUuid(), is(permissionUuid));
        iotHubClient.deleteEndpoint(TEST_ENDPOINT_NAME);
    }

    @Test
    public void actionTest() throws Exception {
        preCreate();
        BaseResponse response;
        response = iotHubClient.attachThingToPrincipal(TEST_ENDPOINT_NAME, TEST_THING_NAME, TEST_PRINCIPAL_NAME);
        assertThat(response.getMessage(), is("ok"));
        response = iotHubClient.attachPrincipalToPolicy(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME, TEST_POLICY_NAME);
        assertThat(response.getMessage(), is("ok"));
        response = iotHubClient.removePrincipalToPolicy(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME, TEST_POLICY_NAME);
        assertThat(response.getMessage(), is("ok"));
        response = iotHubClient.removeThingToPrincipal(TEST_ENDPOINT_NAME, TEST_THING_NAME, TEST_PRINCIPAL_NAME);
        assertThat(response.getMessage(), is("ok"));
        iotHubClient.deleteEndpoint(TEST_ENDPOINT_NAME);
    }

    private void preCreate() {
        iotHubClient.createEndpoint(TEST_ENDPOINT_NAME);
        iotHubClient.createThing(TEST_ENDPOINT_NAME, TEST_THING_NAME);
        iotHubClient.createPrincipal(TEST_ENDPOINT_NAME, TEST_PRINCIPAL_NAME);
        iotHubClient.createPolicy(TEST_ENDPOINT_NAME, TEST_POLICY_NAME);
    }
}
