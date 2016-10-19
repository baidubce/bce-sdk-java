package com.baidubce.services.ruleengine;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ruleengine.model.CreateRuleRequest;
import com.baidubce.services.ruleengine.model.DeleteRulesRequest;
import com.baidubce.services.ruleengine.model.Destination;
import com.baidubce.services.ruleengine.model.DestinationKind;
import com.baidubce.services.ruleengine.model.ListRuleRequest;
import com.baidubce.services.ruleengine.model.ListRuleResponse;
import com.baidubce.services.ruleengine.model.Rule;
import com.baidubce.services.ruleengine.model.RuleState;
import com.baidubce.services.ruleengine.model.UpdateRuleRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by yuanyoujun on 2016/10/9.
 */
public class RuleEngineClientTest {
    // qa-sandbox
    private static final String TEST_ACCESSKEY = "2228f00dfcf64de494348e8486388da1";
    private static final String TEST_SECRETKEY = "40625acf5dcd4807ada78a24d876d87a";
    private static final String TEST_ENVIROMENT_ENDPOINT = "http://nmg01-hpc-w1134.nmg01.baidu.com:8009";

    private static final String TEST_RULE_NAME = "test-rule-sdk";

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
        final String endpoint = "sdk_test_endpoint";
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
            if (dest.getKind() == DestinationKind.MQTT
                    && dest.getValue().equals(destination)) {
                foundTheNewDestination = true;
                break;
            }
        }

        assertThat(foundTheNewDestination, equalTo(true));
    }

    @Test
    public void testDeleteDestination() {
        deleteRuleByName(TEST_RULE_NAME);
        Rule rule = createRule(TEST_RULE_NAME);

        client.deleteDestination(rule.getDestinations().get(0).getUuid());

        Rule ruleOut = client.getRule(rule.getUuid());
        assertThat(ruleOut.getDestinations().size(), equalTo(0));
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
        int pageNo = 1;
        String ruleId = null;
        ListRuleResponse response = null;
        do {
            ListRuleRequest request = new ListRuleRequest();
            request.setPageNo(pageNo++);
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
        request.setEndpoint("sdk_test_endpoint");
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
