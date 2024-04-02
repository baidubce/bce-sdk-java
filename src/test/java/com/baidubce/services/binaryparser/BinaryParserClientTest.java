/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.binaryparser;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.binaryparser.model.BinaryParser;
import com.baidubce.services.binaryparser.model.Format;
import com.baidubce.services.binaryparser.model.CreateBinaryParserRequest;
import com.baidubce.services.binaryparser.model.ListBinaryParserRequest;
import com.baidubce.services.binaryparser.model.ListBinaryParserResponse;
import com.baidubce.services.binaryparser.model.UpdateBinaryParserRequest;
import com.baidubce.services.iotalarm.model.UuidResponse;
import com.baidubce.services.ruleengine.RuleEngineClient;
import com.baidubce.services.ruleengine.model.Rule;
import com.baidubce.services.ruleengine.model.CreateRuleRequest;
import com.baidubce.services.ruleengine.model.DeleteRulesRequest;
import com.baidubce.services.ruleengine.model.ListRuleRequest;
import com.baidubce.services.ruleengine.model.ListRuleResponse;
import com.baidubce.services.ruleengine.model.Destination;
import com.baidubce.services.ruleengine.model.DestinationKind;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for binary parser client
 * Created by yuanyoujun on 2017/9/3.
 */
public class BinaryParserClientTest {
    private static final String AK = "YourAK";
    private static final String SK = "YourSK";
    private static final String ENDPOINT = "YourEndpoint";
    private static final String SCRIPT = "int intval; short shortval;";
    private static final String SCRIPT2 = "int intval; short shortval; byte byteval;";
    private static final String ENDPOINT_NAME = "yyj";

    private static final String RULE_ENVIROMENT_ENDPOINT = "YourRuleEndpoint";

    public BinaryParserClient client;

    public RuleEngineClient ruleEngineClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        client = new BinaryParserClient(config);

        BceClientConfiguration ruleConfig = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(RULE_ENVIROMENT_ENDPOINT);
        ruleEngineClient = new RuleEngineClient(ruleConfig);
    }

    CreateBinaryParserRequest makeCreateRequest(String name, String endpoint, String inputTopic,
                                                String script, String outputTopic, String outputRuleid,
                                                Format format) {
        CreateBinaryParserRequest req = new CreateBinaryParserRequest();
        req.setName(name);
        req.setEndpoint(endpoint);
        req.setInputTopic(inputTopic);
        req.setScript(script);
        if (StringUtils.isNotEmpty(outputTopic)) {
            req.setOutputTopic(outputTopic);
        }
        if (StringUtils.isNotEmpty(outputRuleid)) {
            req.setOutputRuleid(outputRuleid);
        }
        if (format == null) {
            format = Format.BINARY;
        }
        req.setFormat(format);
        return req;
    }

    @Test
    public void testCreate() {
        String name = "testCreate";
        cleanExistingParser(name);
        String inputTopic = name + "/in";
        String outputTopic = name + "/out";
        Format format = Format.HEXSTRING;
        String ruleName = "testRule";
        deleteRuleByName(ruleName);
        Rule rule = createRule(ruleName);
        CreateBinaryParserRequest req = makeCreateRequest(name, ENDPOINT_NAME,
               inputTopic, SCRIPT, outputTopic, rule.getUuid(), format);

        UuidResponse res = client.create(req);

        // get
        BinaryParser parser = client.get(res.getUuid());
        Assert.assertEquals(name, parser.getName());
        Assert.assertEquals(ENDPOINT_NAME, parser.getEndpoint());
        Assert.assertEquals(inputTopic, parser.getInputTopic());
        Assert.assertEquals(SCRIPT, parser.getScript());
        Assert.assertEquals(outputTopic, parser.getOutputTopic());
        Assert.assertEquals(format, parser.getFormat());
        Assert.assertEquals(rule.getUuid(), parser.getOutputRuleid());

        // clean up
        client.delete(res.getUuid());
        deleteRuleById(rule.getUuid());
    }

    @Test
    public void testUpdate() {
        String name = "testUpdate";
        cleanExistingParser(name);
        String inputTopic = name + "/in";
        String outputTopic = name + "/out";
        Format format = Format.BINARY;
        String ruleName1 = "ruleName1";
        deleteRuleByName(ruleName1);
        Rule rule1 = createRule(ruleName1);
        CreateBinaryParserRequest req = makeCreateRequest(name, ENDPOINT_NAME,
                inputTopic, SCRIPT, outputTopic, rule1.getUuid(), format);

        UuidResponse res = client.create(req);

        String name2 = name + "2";
        String inputTopic2 = inputTopic + "2";
        String outputTopic2 = outputTopic + "2";
        format = Format.HEXSTRING;

        deleteRuleById(rule1.getUuid());
        String ruleName2 = "ruleName2";
        deleteRuleByName(ruleName2);
        Rule rule2 = createRule(ruleName2);

        UpdateBinaryParserRequest reqUpdate = new UpdateBinaryParserRequest();
        reqUpdate.setName(name2);
        reqUpdate.setInputTopic(inputTopic2);
        reqUpdate.setOutputTopic(outputTopic2);
        reqUpdate.setScript(SCRIPT2);
        reqUpdate.setOutputRuleid(rule2.getUuid());
        reqUpdate.setFormat(format);

        client.update(reqUpdate, res.getUuid());

        BinaryParser parser = client.get(res.getUuid());
        Assert.assertEquals(name2, parser.getName());
        Assert.assertEquals(ENDPOINT_NAME, parser.getEndpoint());
        Assert.assertEquals(inputTopic2, parser.getInputTopic());
        Assert.assertEquals(SCRIPT2, parser.getScript());
        Assert.assertEquals(outputTopic2, parser.getOutputTopic());
        Assert.assertEquals(format, parser.getFormat());
        Assert.assertEquals(rule2.getUuid(), parser.getOutputRuleid());

        // clean up
        client.delete(res.getUuid());
        deleteRuleById(rule2.getUuid());
    }

    @Test
    public void testList() {
        String name1 = "testList1";
        cleanExistingParser(name1);
        String inputTopic1 = name1 + "/in";
        String outputTopic1 = name1 + "/out";
        Format format1 = Format.BINARY;
        CreateBinaryParserRequest req1 = makeCreateRequest(name1, ENDPOINT_NAME,
                inputTopic1, SCRIPT, outputTopic1, null, format1);
        UuidResponse res1 = client.create(req1);

        String name2 = "testList2";
        cleanExistingParser(name2);
        String inputTopic2 = name2 + "/in";
        String outputTopic2 = name2 + "/out";
        Format format2 = Format.HEXSTRING;
        CreateBinaryParserRequest req2 = makeCreateRequest(name2, ENDPOINT_NAME,
                inputTopic2, SCRIPT, outputTopic2, null, format2);
        UuidResponse res2 = client.create(req2);

        ListBinaryParserRequest listReq = new ListBinaryParserRequest();
        listReq.setPageNo(1);
        listReq.setPageSize(1);
        ListBinaryParserResponse listResponse = client.list(listReq);
        Assert.assertEquals(1, listResponse.getResult().size());
        Assert.assertEquals(listReq.getPageNo(), listResponse.getPageNo());
        Assert.assertEquals(listReq.getPageSize(), listResponse.getPageSize());
        BinaryParser binaryParser = listResponse.getResult().get(0);
        Assert.assertNotNull(binaryParser.getErrorEvent());
        Assert.assertNotNull(binaryParser.getErrorTime());

        client.delete(res1.getUuid());
        client.delete(res2.getUuid());

    }

    @Test
    public void testGetAndClearError() {
        String name = "testGet";
        cleanExistingParser(name);
        String inputTopic = name + "/in";
        String outputTopic = name + "/out";

        CreateBinaryParserRequest req = makeCreateRequest(name, ENDPOINT_NAME,
                inputTopic, SCRIPT, outputTopic, null, null);

        UuidResponse res = client.create(req);

        // get
        BinaryParser parser = client.get(res.getUuid());
        Assert.assertEquals(name, parser.getName());
        Assert.assertEquals(ENDPOINT_NAME, parser.getEndpoint());
        Assert.assertEquals(inputTopic, parser.getInputTopic());
        Assert.assertEquals(SCRIPT, parser.getScript());
        Assert.assertEquals(outputTopic, parser.getOutputTopic());
        Assert.assertNotNull(parser.getErrorEvent());
        Assert.assertNotNull(parser.getErrorTime());

        client.clearError(res.getUuid());

        Assert.assertEquals("1970-01-01T00:00:00Z", parser.getErrorTime());
        client.delete(res.getUuid());
    }

    void cleanExistingParser(String name) {
        ListBinaryParserRequest req = new ListBinaryParserRequest();
        ListBinaryParserResponse res = null;
        int pageNo = 0;
        boolean processed = false;
        do {
            req.setPageNo(++pageNo);
            res = client.list(req);
            for (BinaryParser bp : res.getResult()) {
                if (bp.getName().equals(name)) {
                    client.delete(bp.getUuid());
                    processed = true;
                    break;
                }
            }
        } while (! processed && res.getTotalCount() > req.getPageSize() * pageNo);
    }

    private Rule createRule(String name) {
        CreateRuleRequest request = new CreateRuleRequest();
        request.setName(name);
        request.setDescription("test rule is create from sdk, for testing");
        request.setEndpoint(ENDPOINT_NAME);
        request.setFrom("topic/sensor01");
        request.setSelect("temperature, name");
        request.setWhere("temperature > 90 AND name like '%sensor%'");
        List<Destination> destinations = new ArrayList<Destination>();
        Destination dest = new Destination();
        dest.setKind(DestinationKind.MQTT);
        dest.setValue("topic/sensor01_high_temp");
        destinations.add(dest);
        request.setDestinations(destinations);
        Rule rule = ruleEngineClient.createRule(request);
        return rule;
    }

    private void deleteRuleById(String ruleId) {
        if (ruleId != null) {
            DeleteRulesRequest delRequest = new DeleteRulesRequest();
            List<String> ruleIds = new ArrayList<String>();
            ruleIds.add(ruleId);
            delRequest.setRules(ruleIds);
            ruleEngineClient.deleteRule(delRequest);
        }
    }

    private void deleteRuleByName(String name) {
        String ruleId = findRuleIdByName(name);

        if (ruleId != null) {
            DeleteRulesRequest delRequest = new DeleteRulesRequest();
            List<String> ruleIds = new ArrayList<String>();
            ruleIds.add(ruleId);
            delRequest.setRules(ruleIds);
            ruleEngineClient.deleteRule(delRequest);
        }
    }

    private String findRuleIdByName(String name) {
        int pageNo = 0;
        String ruleId = null;
        ListRuleResponse response = null;
        do {
            ListRuleRequest request = new ListRuleRequest();
            request.setPageNo(++pageNo);
            response = ruleEngineClient.listRules(request);
            for (Rule rule : response.getResult()) {
                if (rule.getName().equals(name)) {
                    ruleId = rule.getUuid();
                    break;
                }
            }
        } while (ruleId == null && response.getTotalCount() > response.getPageNo() * response.getPageSize());

        return ruleId;
    }
}
