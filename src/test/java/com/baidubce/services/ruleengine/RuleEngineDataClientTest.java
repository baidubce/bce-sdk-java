/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ruleengine;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ruleengine.model.FeedMessageRequest;
import com.baidubce.services.ruleengine.model.FeedMessageResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for rule data client
 *
 * Created by huangjiatian on 2019/03/07.
 */
public class RuleEngineDataClientTest {

    // qa-sandbox
    private static final String ak = "YourAK";
    private static final String sk = "YourSK";
    private static final String endpoint = "YourEnvironmentEndpoint";
    private static final String uuid = "YourRuleUuid";

    private RuleEngineDataClient client;

    @Before
    public void setUp() {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        client = new RuleEngineDataClient(configuration);
    }

    // test method feed(String, List<String>)
    @Test
    public void testFeedV1() {
        FeedMessageResponse response = client.feed(uuid, Arrays.asList("{\"name\":\"simple\"}"));
        String exp = "written 1 message(s), dropped 0 message(s).";
        Assert.assertEquals(exp, response.getResult());
    }

    // test method feed(String, FeedMessageRequest)
    @Test
    public void testFeedV2() {
        List<String> payloads = new ArrayList<String>();
        payloads.add("{\"name\":\"simple\"}");
        payloads.add("{\"name\":\"simple\"}");
        FeedMessageRequest request = new FeedMessageRequest().withSimplePayloads(payloads);

        FeedMessageResponse response = client.feed(uuid, request);
        String exp = "written 2 message(s), dropped 0 message(s).";
        Assert.assertEquals(exp, response.getResult());
    }

    // test method feed(String, FeedMessageRequest, boolean)
    @Test
    public void testFeedV3() {
        List<String> payloads = new ArrayList<String>();
        payloads.add("{\"name\":\"simple\"}");
        payloads.add("{\"name\":\"simple\"}");
        FeedMessageRequest request = new FeedMessageRequest().withSimplePayloads(payloads);

        FeedMessageResponse response = client.feed(uuid, request, true);
        String exp = "written 2 message(s), dropped 0 message(s).";
        Assert.assertEquals(exp, response.getResult());

        response = client.feed(uuid, request, false);
        Assert.assertEquals(exp, response.getResult());
    }
}
