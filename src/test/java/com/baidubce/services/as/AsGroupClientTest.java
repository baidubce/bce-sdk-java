/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.as;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.as.model.rule.ActionType;
import com.baidubce.services.as.model.rule.CreateRuleResult;
import com.baidubce.services.as.model.rule.RuleDelRequest;
import com.baidubce.services.as.model.rule.RuleListQuery;
import com.baidubce.services.as.model.rule.RuleQuery;
import com.baidubce.services.as.model.rule.RuleRequest;
import com.baidubce.services.as.model.rule.RuleType;
import com.baidubce.services.as.model.rule.RuleState;
import com.baidubce.services.as.model.rule.RuleVOListResponse;
import com.baidubce.services.as.model.rule.RuleVOResponse;
import com.baidubce.services.as.model.task.DetachNodeRequest;
import org.junit.Assert;
import com.baidubce.services.as.model.asgroup.AsGroupBatchRequest;
import com.baidubce.services.as.model.asgroup.AsGroupDeleteResponse;
import com.baidubce.services.as.model.asgroup.AssignTagInfo;
import com.baidubce.services.as.model.asgroup.BccNameConfig;
import com.baidubce.services.as.model.asgroup.BillingInfo;
import com.baidubce.services.as.model.asgroup.CmdConfig;
import com.baidubce.services.as.model.asgroup.EipInfo;
import com.baidubce.services.as.model.asgroup.ExecCmdStrategyType;
import com.baidubce.services.as.model.asgroup.GetAsGroupRequest;
import com.baidubce.services.as.model.asgroup.GetAsGroupResponse;
import com.baidubce.services.as.model.asgroup.GroupConfig;
import com.baidubce.services.as.model.asgroup.GroupCreateRequest;
import com.baidubce.services.as.model.asgroup.GroupCreateResponse;
import com.baidubce.services.as.model.asgroup.HealthCheckConfig;
import com.baidubce.services.as.model.asgroup.ListAsGroupRequest;
import com.baidubce.services.as.model.asgroup.ListAsGroupResponse;
import com.baidubce.services.as.model.asgroup.ListAsNodeRequest;
import com.baidubce.services.as.model.asgroup.ListAsNodeResponse;
import com.baidubce.services.as.model.asgroup.NodeInfo;
import com.baidubce.services.as.model.asgroup.TagInfo;
import com.baidubce.services.as.model.zone.ZoneInfo;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Collections;


import java.util.ArrayList;
import java.util.List;


/**
 * test class for testing asGroup service
 */
public class AsGroupClientTest {

    protected static final Logger logger = LoggerFactory.getLogger(AsGroupClientTest.class);
    protected static String endpoint = "http://gzbh-sandbox15-6271.gzbh:8376";
    protected final String ak = "7807d1ccd46042378c9f6dfb5684496d";
    protected final String sk = "b51dc6642339468289f946f0a6303115";
    private final String ruleId = "asrule-Iv0qihED";

    private AsGroupClient client;

    @Before
    public void setUp() {
        AsGroupClientConfiguration config = new AsGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        client = new AsGroupClient(config);
    }


    @Test
    public void detachNode() {
        DetachNodeRequest request = new DetachNodeRequest();
        request.setGroupId("asg-mPWFLu1E");
        request.setNodes(Collections.singletonList("i-mPkY5ZG5"));
        client.detachNode(request);
    }

    @Test
    public void createRule() {
        RuleRequest request = new RuleRequest();
        request.setRuleName("testRule");
        request.setGroupId("asg-mPWFLu1E");
        request.setType(RuleType.PERIOD);
        request.setActionType(ActionType.INCREASE);
        request.setActionNum(1);
        request.setCooldownInSec(300);
        request.setState(RuleState.ENABLE);
        request.setPeriodType("WEEK");
        request.setPeriodStartTime("2023-12-11T11:00:00Z");
        request.setPeriodEndTime("2023-12-21T11:00:00Z");
        request.setCronTime("12:30");
        request.setPeriodValue(2);

        CreateRuleResult rule = client.createRule(request);
        Assert.assertNotNull(rule);
        Assert.assertNotNull(rule.getRuleId());
    }

    @Test
    public void updateRule() {
        RuleRequest request = new RuleRequest();
        request.setRuleId(ruleId);
        request.setRuleName("testRule_new");
        request.setGroupId("asg-mPWFLu1E");
        request.setType(RuleType.PERIOD);
        request.setActionType(ActionType.INCREASE);
        request.setActionNum(1);
        request.setCooldownInSec(300);
        request.setState(RuleState.ENABLE);
        request.setPeriodType("WEEK");
        request.setPeriodStartTime("2023-12-11T11:00:00Z");
        request.setPeriodEndTime("2023-12-21T11:00:00Z");
        request.setCronTime("12:40");
        request.setPeriodValue(2);

        client.updateRule(request);
        RuleQuery ruleQuery = new RuleQuery();
        ruleQuery.setRuleId(ruleId);
        RuleVOResponse rule = client.getRule(ruleQuery);
        Assert.assertNotNull(rule);
        Assert.assertEquals(request.getRuleName(), rule.getRuleName());
        Assert.assertEquals(request.getCronTime(), rule.getCronTime());
    }

    @Test
    public void queryRuleList() {
        RuleListQuery ruleListQuery = new RuleListQuery();
        ruleListQuery.setPageNo(1);
        ruleListQuery.setPageSize(10);
        ruleListQuery.setGroupId("asg-mPWFLu1E");
        ruleListQuery.setOrderBy("createTime");
        RuleVOListResponse response = client.queryRuleList(ruleListQuery);

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.getPageNo());
        Assert.assertTrue(response.getResult().size() > 0);
    }

    @Test
    public void getRule() {
        RuleQuery ruleQuery = new RuleQuery();
        ruleQuery.setRuleId(ruleId);
        RuleVOResponse rule = client.getRule(ruleQuery);
        Assert.assertNotNull(rule);
        Assert.assertEquals("testRule_new", rule.getRuleName());
        Assert.assertEquals("12:40", rule.getCronTime());
    }

    @Test(expected = com.baidubce.BceServiceException.class)
    public void deleteRule() {
        RuleDelRequest ruleDelRequest = new RuleDelRequest();
        ruleDelRequest.setRuleIds(Collections.singletonList(ruleId));
        client.deleteRule(ruleDelRequest);

        RuleQuery ruleQuery = new RuleQuery();
        ruleQuery.setRuleId(ruleId);
        client.getRule(ruleQuery);
    }

        @Test
        public void testCreateAsGroup() {
            GroupCreateRequest req = new GroupCreateRequest();
            req.setGroupName("yyy-test-gosdk3");

            GroupConfig config = new GroupConfig();
            config.setMinNodeNum(0);
            config.setExpectNum(-1);
            config.setMaxNodeNum(2000);
            config.setCooldownInSec(300);
            req.setConfig(config);

            HealthCheckConfig healthCheck = new HealthCheckConfig();
            healthCheck.setHealthCheckInterval(15);
            healthCheck.setGraceTime(300);
            req.setHealthCheck(healthCheck);

            req.setShrinkageStrategy("Earlier");

            List<ZoneInfo> zoneInfoList = new ArrayList<ZoneInfo>();
            ZoneInfo zoneInfo = new ZoneInfo();
            zoneInfo.setZone("zoneA");
            zoneInfo.setSubnetId("sbn-8mghgkzs3ch9");
            zoneInfoList.add(zoneInfo);
            req.setZoneInfo(zoneInfoList);

            AssignTagInfo assignTagInfo = new AssignTagInfo();
            assignTagInfo.setRelationTag(false);
            List<TagInfo> tagList = new ArrayList<TagInfo>();
            TagInfo tag = new TagInfo();
            tag.setTagKey("默认项目");
            tag.setTagValue("");
            tagList.add(tag);
            assignTagInfo.setTags(tagList);
            req.setAssignTagInfo(assignTagInfo);

            List<NodeInfo> nodeList = new ArrayList<NodeInfo>();
            NodeInfo nodeInfo = new NodeInfo();
            nodeInfo.setCpuCount(8);
            nodeInfo.setMemoryCapacityInGB(32);
            nodeInfo.setSysDiskType("enhanced_ssd_pl1");
            nodeInfo.setSysDiskInGB(20);
            nodeInfo.setInstanceType(13);
            nodeInfo.setProductType("bidding");
            nodeInfo.setImageId("24e80264-8a6d-49c1-b415-116d9cf38a75");
            nodeInfo.setImageType("custom");
            nodeInfo.setOsType("linux");
            nodeInfo.setSecurityGroupId("g-yhryv5vyapb4");
            nodeInfo.setSpec("bcc.g4.c8m32");
            nodeInfo.setPriorities(1);
            nodeInfo.setZoneSubnet("[{\"zone\":\"zoneA\",\"subnetId\":\"sbn-8mghgkzs3ch9\",\"subnetName\":\"lyz2（192.168.0.0/24）\",\"subnetUuid\":\"5911e194-528f-4153-99a3-3c63b7bc7d7c\"}]");
            nodeInfo.setTotalCount(1);
            nodeInfo.setBidModel("custom");
            nodeInfo.setBidPrice(0.0264944);
            nodeList.add(nodeInfo);
            req.setNodes(nodeList);

            EipInfo eip = new EipInfo();
            eip.setIfBindEip(false);
            req.setEip(eip);

            BillingInfo billing = new BillingInfo();
            billing.setPaymentTiming("bidding");
            req.setBilling(billing);

            CmdConfig cmdConfig = new CmdConfig();
            cmdConfig.setHasDecreaseCmd(false);
            cmdConfig.setDecCmdStrategy(ExecCmdStrategyType.Proceed);
            cmdConfig.setDecCmdTimeout(3600);
            cmdConfig.setDecCmdManual(true);
            cmdConfig.setHasIncreaseCmd(false);
            cmdConfig.setIncCmdStrategy(ExecCmdStrategyType.Proceed);
            cmdConfig.setIncCmdTimeout(3600);
            cmdConfig.setIncCmdManual(true);
            req.setCmdConfig(cmdConfig);

            BccNameConfig bccNameConfig = new BccNameConfig();
            bccNameConfig.setBccName("");
            bccNameConfig.setBccHostname("");
            req.setBccNameConfig(bccNameConfig);

            GroupCreateResponse response = client.createAsGroup(req);

            Assert.assertNotNull(response.getGroupId());

        }

        @Test
        public void testListAsGroup() {
            ListAsGroupRequest request = new ListAsGroupRequest();
            request.setKeywordType("groupName");
            request.setKeyword("djw-test");
            request.setPageNo(1);
            ListAsGroupResponse response = client.listAsGroup(request);
            Assert.assertEquals("asg-FKsD6xmT", response.getResult().get(0).getGroupId());
        }


        @Test
        public void deleteAsGroup() {
            AsGroupBatchRequest request = new AsGroupBatchRequest();
            request.setGroupIds(Collections.singletonList("asg-a7dGeHcF"));
            AsGroupDeleteResponse response = client.deleteAsGroup(request);
            Assert.assertEquals("asg-a7dGeHcF", response.getGroupIds().get(0));
        }

        @Test
        public void getAsGroupDetail() {
            GetAsGroupRequest request = new GetAsGroupRequest();
            request.setGroupId("asg-sTufLpId");
            GetAsGroupResponse asGroup = client.getAsGroupDetail(request);
            Assert.assertEquals("asg-sTufLpId", asGroup.getGroupId());
        }

        @Test
        public void listAsNodeV2() {
            ListAsNodeRequest request = new ListAsNodeRequest();
            request.setGroupId("asg-6GH0OMN0");
            request.setPageNo(1);
            ListAsNodeResponse response = client.listAsNode(request);
            Assert.assertEquals(4, response.getResult().size());
        }
    }


