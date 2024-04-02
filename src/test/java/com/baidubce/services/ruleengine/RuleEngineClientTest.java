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
import com.baidubce.services.iotalarm.model.UuidResponse;
import com.baidubce.services.iotsmsreceiver.IotSmsReceiverClient;
import com.baidubce.services.iotsmsreceiver.model.CreateIotSmsReceiverRequest;
import com.baidubce.services.ruleengine.model.CreateRuleRequest;
import com.baidubce.services.ruleengine.model.DeleteRulesRequest;
import com.baidubce.services.ruleengine.model.Destination;
import com.baidubce.services.ruleengine.model.DestinationKind;
import com.baidubce.services.ruleengine.model.ListRuleRequest;
import com.baidubce.services.ruleengine.model.ListRuleResponse;
import com.baidubce.services.ruleengine.model.Rule;
import com.baidubce.services.ruleengine.model.RuleState;
import com.baidubce.services.ruleengine.model.UpdateRuleRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for rule engine client
 *
 * Created by yuanyoujun on 2016/10/9.
 */
public class RuleEngineClientTest {
    // qa-sandbox
    private static final String TEST_ACCESSKEY = "YourAK";
    private static final String TEST_SECRETKEY = "YourSK";
    private static final String TEST_ENVIROMENT_ENDPOINT = "YourEnvironmentEndpoint";

    private static final String TEST_RULE_NAME = "test-rule-sdk";
    private static final String ENDPOINT = "YourEndpoint";

    private static final String BTS_INSTANCE = "YourBTSInstance";
    private static final String BTS_TABLE = "YourBTSTable";

    private static final String RDS_CONNECTION_ID = "YourRDSConnectionId";
    private static final String RDS_TABLE = "YourRDSTable";

    public static final String TEMPLATE = "smsTpl:a37d4613-26d3-4628-a3cd-9855c8e68e76";
    public static final String SIGNATURE = "WbtYhzJC-4IIT-HknG";

    private RuleEngineClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_ACCESSKEY, TEST_SECRETKEY))
                .withEndpoint(TEST_ENVIROMENT_ENDPOINT);
        client = new RuleEngineClient(config);

        // for production environment, just create the client like:
        // client = new RuleengineClient(accessKey, secretKey);
    }


    @Test
    public void testCreate() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        assertThat(rule.getName(), equalTo(TEST_RULE_NAME));
    }

    @Test
    public void testDelete() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);
        deleteRuleById(rule.getUuid());

        String ruleId = findRuleIdByName(TEST_RULE_NAME);
        assertThat(ruleId, equalTo(null));
    }

    @Test
    public void testUpdate() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        UpdateRuleRequest updateReq = new UpdateRuleRequest();
        updateReq.setUuid(rule.getUuid());

        final String newWhere = "a>b AND c < 100";
        updateReq.setWhere(newWhere);

        final String newSelect = "a,b,c,*";
        updateReq.setSelect(newSelect);

        final String newFrom = "topic/after/updating";
        updateReq.setFrom(newFrom);

        final String newDesc = "this is rule description after updating";
        updateReq.setDescription(newDesc);

        client.updateRule(updateReq);

        Rule newRule = client.getRule(rule.getUuid());

        assertThat(newRule.getWhere(), equalTo(newWhere));
        assertThat(newRule.getFrom(), equalTo(newFrom));
        assertThat(newRule.getSelect(), equalTo(newSelect));
        assertThat(newRule.getDescription(), equalTo(newDesc));
    }

    @Test
    public void testEnableDisable() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        assertThat(rule.getState(), equalTo(RuleState.ENABLED));

        client.disableRule(rule.getUuid());
        rule = client.getRule(rule.getUuid());
        assertThat(rule.getState(), equalTo(RuleState.DISABLED));

        client.enableRule(rule.getUuid());
        rule = client.getRule(rule.getUuid());
        assertThat(rule.getState(), equalTo(RuleState.ENABLED));
    }

    @Test
    public void testGetRule() {
        deleteRuleByName(TEST_RULE_NAME);
        CreateRuleRequest request = new CreateRuleRequest();
        request.setName(TEST_RULE_NAME);
        final String desc = "test rule is create from sdk, for testing";
        request.setDescription(desc);
        final String endpoint = ENDPOINT;
        request.setEndpoint(endpoint);
        final String topic = "topic/sensor01";
        request.setFrom(topic);
        final String select = "temperature, name";
        request.setSelect(select);
        final String where = "temperature > 90 AND name like '%sensor%'";
        request.setWhere(where);
        List<Destination> destinations = new ArrayList<Destination>();
        Destination dest = new Destination();
        dest.setKind(DestinationKind.MQTT);
        final String topicDest = "topic/sensor01_high_temp";
        dest.setValue(topicDest);
        destinations.add(dest);
        request.setDestinations(destinations);
        Rule rule = client.createRule(request);

        Rule ruleOut = client.getRule(rule.getUuid());

        assertThat(ruleOut.getState(), equalTo(RuleState.ENABLED));
        assertThat(ruleOut.getDescription(), equalTo(desc));
        assertThat(ruleOut.getName(), equalTo(TEST_RULE_NAME));
        assertThat(ruleOut.getSelect(), equalTo(select));
        assertThat(ruleOut.getFrom(), equalTo(topic));
        assertThat(ruleOut.getWhere(), equalTo(where));
        assertThat(ruleOut.getEndpoint(), equalTo(endpoint));

        assertThat(ruleOut.getDestinations().size(), equalTo(1));
        assertThat(ruleOut.getDestinations().get(0).getKind(), equalTo(DestinationKind.MQTT));
        assertThat(ruleOut.getDestinations().get(0).getValue(), equalTo(topicDest));
    }

    @Test
    public void testAddDestination() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        Destination request = new Destination();
        final String destination = "SOME_OTHER_TOPIC";
        request.setValue(destination);
        request.setKind(DestinationKind.MQTT);
        request.setRuleUuid(rule.getUuid());
        client.createDestination(request);

        Rule ruleOut = client.getRule(rule.getUuid());
        assertThat(ruleOut.getDestinations().size(), equalTo(2));
        boolean foundTheNewDestination = false;
        for (Destination dest : ruleOut.getDestinations()) {
            if (dest.getKind().equals(DestinationKind.MQTT)
                    && dest.getValue().equals(destination)) {
                foundTheNewDestination = true;
                break;
            }
        }
        assertThat(foundTheNewDestination, equalTo(true));
    }

    @Test
    public void testRuleDestination() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        final String destRuleName = "destRule";
        deleteRuleByName(destRuleName);
        CreateRuleRequest ruleRequest = new CreateRuleRequest();
        ruleRequest.setName(destRuleName);
        ruleRequest.setEndpoint(ENDPOINT);
        ruleRequest.setFrom("topic/from");
        ruleRequest.setSelect("*");
        List<Destination> destinationList = new ArrayList<Destination>();
        Destination dest1 = new Destination();
        dest1.setKind(DestinationKind.MQTT);
        dest1.setValue("topic/to");
        destinationList.add(dest1);
        ruleRequest.setDestinations(destinationList);
        Rule anotherRule = client.createRule(ruleRequest);

        Destination destination = new Destination();
        destination.setValue(anotherRule.getUuid());
        destination.setKind(DestinationKind.RULE);
        destination.setRuleUuid(rule.getUuid());
        client.createDestination(destination);

        Rule ruleOut = client.getRule(rule.getUuid());
        String destinationRuleId = null;
        for (Destination dest : ruleOut.getDestinations()) {
            if (DestinationKind.RULE.equals(dest.getKind())) {
                destinationRuleId = dest.getValue();
                break;
            }
        }
        assertThat(destinationRuleId, equalTo(anotherRule.getUuid()));
    }

    @Test
    public void testBTSDestination() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        Destination btsDestinationRequest = new Destination();
        btsDestinationRequest.setValue(BTS_INSTANCE + ":" + BTS_TABLE);
        btsDestinationRequest.setRuleUuid(rule.getUuid());
        btsDestinationRequest.setKind(DestinationKind.BTS);
        client.createDestination(btsDestinationRequest);

        Rule ruleOut = client.getRule(rule.getUuid());
        boolean foundBTSDestination = false;
        for (Destination dest : ruleOut.getDestinations()) {
            if (DestinationKind.BTS.equals(dest.getKind())) {
                foundBTSDestination = true;
                break;
            }
        }
        assertThat(foundBTSDestination, equalTo(true));
    }

    @Test
    public void testRDSDestination() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        Destination destination = new Destination();
        destination.setValue(RDS_CONNECTION_ID + ":" + RDS_TABLE);
        destination.setKind(DestinationKind.RDS);
        destination.setRuleUuid(rule.getUuid());
        client.createDestination(destination);

        Rule ruleOut = client.getRule(rule.getUuid());
        boolean foundRDSDestination = false;
        for (Destination dest : ruleOut.getDestinations()) {
            if (DestinationKind.RDS.equals(dest.getKind())) {
                foundRDSDestination = true;
                break;
            }
        }
        assertThat(foundRDSDestination, equalTo(true));
    }

    @Test
    public void testBIN2JSONDestination() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        Destination request = new Destination();
        final String destination = "test_topic";
        request.setValue(destination);
        request.setKind(DestinationKind.BIN2JSON);
        request.setRuleUuid(rule.getUuid());
        client.createDestination(request);

        Rule ruleOut = client.getRule(rule.getUuid());
        boolean foundBIN2JSONDestination = false;
        for (Destination dest : ruleOut.getDestinations()) {
            if (dest.getKind().equals(DestinationKind.BIN2JSON)
                    && dest.getValue().equals(destination)) {
                foundBIN2JSONDestination = true;
                break;
            }
        }
        assertThat(foundBIN2JSONDestination, equalTo(true));
    }

    @Test
    public void testDeleteDestination() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        client.deleteDestination(rule.getDestinations().get(0).getUuid());

        Rule ruleOut = client.getRule(rule.getUuid());
        assertThat(ruleOut.getDestinations().size(), equalTo(0));
    }

    @Test
    public void testSmsDestRule() {
        deleteRuleByName(TEST_RULE_NAME);
        // create a sms receiver first
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_ACCESSKEY, TEST_SECRETKEY))
                .withEndpoint(TEST_ENVIROMENT_ENDPOINT);
        IotSmsReceiverClient smsClient = new IotSmsReceiverClient(config);
        CreateIotSmsReceiverRequest smsRequest = new CreateIotSmsReceiverRequest();
        smsRequest.setName("testSmsDestRule");
        smsRequest.setSignature(SIGNATURE);
        smsRequest.setTemplate(TEMPLATE);
        smsRequest.setReceivers("15821267273");
        UuidResponse smsResponse = smsClient.create(smsRequest);

        CreateRuleRequest ruleReq = new CreateRuleRequest();
        ruleReq.setSelect("*");
        ruleReq.setEndpoint(ENDPOINT);
        ruleReq.setFrom("testSmsDestRule");
        ruleReq.setName(TEST_RULE_NAME);
        Destination dest = new Destination();
        dest.setKind(DestinationKind.SMS);
        dest.setValue(smsResponse.getUuid());
        List<Destination> dests = new ArrayList<Destination>();
        dests.add(dest);
        ruleReq.setDestinations(dests);

        Rule rule = client.createRule(ruleReq);

        UpdateRuleRequest updateReq = new UpdateRuleRequest();
        updateReq.setUuid(rule.getUuid());
        String newDesc = "newDesc";
        updateReq.setDescription(newDesc);
        updateReq.setWhere(null);
        client.updateRule(updateReq);
        Rule rule2 = client.getRule(rule.getUuid());
        Assert.assertEquals(newDesc, rule2.getDescription());
        Assert.assertEquals(rule.getSelect(), rule2.getSelect());
        Assert.assertEquals(rule.getFrom(), rule2.getFrom());
        Assert.assertEquals(rule.getWhere(), rule2.getWhere());

        // clean up
        DeleteRulesRequest delRuleReq = new DeleteRulesRequest();
        ArrayList<String> ids = new ArrayList<String>();
        ids.add(rule.getUuid());
        delRuleReq.setRules(ids);
        client.deleteRule(delRuleReq);

        smsClient.delete(smsResponse.getUuid());
    }

    private void deleteRuleByName(String name) {
        String ruleId = findRuleIdByName(name);

        if (ruleId != null) {
            DeleteRulesRequest delRequest = new DeleteRulesRequest();
            List<String> ruleIds = new ArrayList<String>();
            ruleIds.add(ruleId);
            delRequest.setRules(ruleIds);
            client.deleteRule(delRequest);
        }
    }

    private void deleteRuleById(String ruleId) {
        if (ruleId != null) {
            DeleteRulesRequest delRequest = new DeleteRulesRequest();
            List<String> ruleIds = new ArrayList<String>();
            ruleIds.add(ruleId);
            delRequest.setRules(ruleIds);
            client.deleteRule(delRequest);
        }
    }

    private String findRuleIdByName(String name) {
        int pageNo = 0;
        String ruleId = null;
        ListRuleResponse response = null;
        do {
            ListRuleRequest request = new ListRuleRequest();
            request.setPageNo(++pageNo);
            response = client.listRules(request);
            for (Rule rule : response.getResult()) {
                if (rule.getName().equals(name)) {
                    ruleId = rule.getUuid();
                    break;
                }
            }
        } while (ruleId == null && response.getTotalCount() > response.getPageNo() * response.getPageSize());

        return ruleId;
    }

    private Rule createRule(String name) {
        CreateRuleRequest request = new CreateRuleRequest();
        request.setName(name);
        request.setDescription("test rule is create from sdk, for testing");
        request.setEndpoint(ENDPOINT);
        request.setFrom("topic/sensor01");
        request.setSelect("temperature, name");
        request.setWhere("temperature > 90 AND name like '%sensor%'");
        List<Destination> destinations = new ArrayList<Destination>();
        Destination dest = new Destination();
        dest.setKind(DestinationKind.MQTT);
        dest.setValue("topic/sensor01_high_temp");
        destinations.add(dest);
        request.setDestinations(destinations);
        Rule rule = client.createRule(request);
        return rule;
    }
}
