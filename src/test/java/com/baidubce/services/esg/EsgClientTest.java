/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.esg;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.esg.model.CreateEsgRequest;
import com.baidubce.services.esg.model.CreateEsgResponse;
import com.baidubce.services.esg.model.EnterpriseSecurityGroup;
import com.baidubce.services.esg.model.EnterpriseSecurityGroupRule;
import com.baidubce.services.esg.model.EsgRuleOperateRequest;
import com.baidubce.services.esg.model.ListEsgRequest;
import com.baidubce.services.esg.model.ListEsgResponse;
import com.baidubce.services.esg.model.UpdateEsgRuleRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Enterprise security group client test
 */
public class EsgClientTest {

    private static final Logger logger = LoggerFactory.getLogger(EsgClientTest.class);
    private static final String AK = "ak_will_be_written";
    private static final String SK = "sk_will_be_written";
    private EsgClient esgClient;

    private List<String> esgIds = new ArrayList<String>();

    @Before
    public void setup() {
        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("bcc.bj.baidubce.com");
        esgClient = new EsgClient(config);
        esgIds.add(esgClient.createEsg(createEsgRequest()).getEnterpriseSecurityGroupId());
    }

    @After
    public void tearDown() {
        for (String esgId : esgIds) {
            esgClient.deleteEsg(esgId);
        }
        logger.info("esg test tearDown");
    }

    @Test
    public void testCreateEsg() {
        CreateEsgRequest request = createEsgRequest();
        CreateEsgResponse response = esgClient.createEsg(request);
        assertThat(response.getEnterpriseSecurityGroupId(), notNullValue());
        toJsonPrettyString("createEsg", response);
        esgIds.add(response.getEnterpriseSecurityGroupId());
    }

    private CreateEsgRequest createEsgRequest() {
        List<EnterpriseSecurityGroupRule> rules = new ArrayList<EnterpriseSecurityGroupRule>();
        rules.add(EnterpriseSecurityGroupRule.builder()
                .action("deny")
                .direction("ingress")
                .ethertype("IPv4")
                .portRange("1-65535")
                .priority(1000)
                .protocol("udp")
                .remark("java sdk esg rule deny test")
                .sourceIp("all")
                .build());

        rules.add(EnterpriseSecurityGroupRule.builder()
                .action("allow")
                .direction("ingress")
                .ethertype("IPv4")
                .portRange("1-65535")
                .priority(1000)
                .protocol("icmp")
                .remark("java sdk esg rule allow test")
                .sourceIp("all")
                .build());

        // Example with new fields: remoteIpSet and remoteIpGroup
        rules.add(EnterpriseSecurityGroupRule.builder()
                .action("allow")
                .direction("ingress")
                .ethertype("IPv4")
                .portRange("80")
                .priority(500)
                .protocol("tcp")
                .remark("java sdk esg rule with remoteIpSet")
                .remoteIpSet("192.168.1.0/24,10.0.0.0/8")
                .build());
        List<TagModel> tags = new ArrayList<TagModel>();
        tags.add(new TagModel()
                .withTagKey("testTagKey")
                .withTagValue("testTagValue"));
        return CreateEsgRequest.builder()
                .name("esg_java_sdk_name" + System.currentTimeMillis())
                .desc("create esg java sdk test")
                .rules(rules)
                .tags(tags)
                .build();
    }

    @Test
    public void testListEsg() {
        ListEsgResponse response = esgClient.listEsg(ListEsgRequest.builder().build());
        assertThat(response.getEnterpriseSecurityGroups(), notNullValue());
        toJsonPrettyString("listEsg", response);
    }

    @Test
    public void testAuthorizeEsgRule() {
        if(esgIds.isEmpty()){
            esgIds.add(esgClient.createEsg(createEsgRequest()).getEnterpriseSecurityGroupId());
        }
        List<EnterpriseSecurityGroupRule> rules = new ArrayList<EnterpriseSecurityGroupRule>();
        rules.add(EnterpriseSecurityGroupRule.builder()
                .action("deny")
                .direction("ingress")
                .ethertype("IPv4")
                .portRange("1-65535")
                .priority(1000)
                .protocol("tcp")
                .remark("java sdk authorize esg rule test")
                .sourceIp("all")
                .build());

        // Example with remoteIpGroup
        rules.add(EnterpriseSecurityGroupRule.builder()
                .action("allow")
                .direction("egress")
                .ethertype("IPv4")
                .portRange("443")
                .priority(600)
                .protocol("tcp")
                .remark("java sdk authorize esg rule with remoteIpGroup")
                .remoteIpGroup("esg-group-123")
                .build());
        EsgRuleOperateRequest operateRequest = EsgRuleOperateRequest.builder()
                .enterpriseSecurityGroupId(esgIds.get(0))
                .rules(rules)
                .build();
        esgClient.authorizeEsgRule(operateRequest);
        logger.info("test authorize enterprise security group rule finished.");
    }

    @Test
    public void testUpdateEsgRule(){
        if(esgIds.isEmpty()){
            esgIds.add(esgClient.createEsg(createEsgRequest()).getEnterpriseSecurityGroupId());
        }
        String esgId = esgIds.get(0);
        String willBeUpdatedEsgRuleId = null;
        ListEsgResponse response = esgClient.listEsg(ListEsgRequest.builder().build());
        List<EnterpriseSecurityGroup> enterpriseSecurityGroups = response.getEnterpriseSecurityGroups();
        for (EnterpriseSecurityGroup enterpriseSecurityGroup : enterpriseSecurityGroups) {
            if (esgId.equals(enterpriseSecurityGroup.getId())){
                List<EnterpriseSecurityGroupRule> rules = enterpriseSecurityGroup.getRules();
                if (rules != null && !rules.isEmpty()){
                    willBeUpdatedEsgRuleId = rules.get(0).getEnterpriseSecurityGroupRuleId();
                    break;
                }
            }
        }
        if (willBeUpdatedEsgRuleId != null){
            // Example using new fields and clientToken for idempotency
            // Note: direction and ethertype are deprecated in UpdateEsgRuleRequest (marked with @JsonIgnore)
            UpdateEsgRuleRequest updateEsgRuleRequest = UpdateEsgRuleRequest.builder()
                    .enterpriseSecurityGroupRuleId(willBeUpdatedEsgRuleId)
                    .clientToken("java-sdk-test-update-" + System.currentTimeMillis())
                    .remark("java sdk test update esg rule with new fields")
                    .protocol("tcp")
                    .portRange("8080-8090")
                    .sourcePortRange("1024-65535")
                    .sourceIp("192.168.0.0/16")
                    .localIp("10.0.0.1")
                    .remoteIpSet("172.16.0.0/12,192.168.1.0/24")
                    .action("allow")
                    .priority(100)
                    .build();
            esgClient.updateEsgRule(updateEsgRuleRequest);
            logger.info("test update enterprise security group rule finished.");
        }
    }

    @Test
    public void testDeleteEsgRule(){
        if(esgIds.isEmpty()){
            esgIds.add(esgClient.createEsg(createEsgRequest()).getEnterpriseSecurityGroupId());
        }
        String esgId = esgIds.get(0);
        String willBeDeletedEsgRuleId = null;
        ListEsgResponse response = esgClient.listEsg(ListEsgRequest.builder().build());
        List<EnterpriseSecurityGroup> enterpriseSecurityGroups = response.getEnterpriseSecurityGroups();
        for (EnterpriseSecurityGroup enterpriseSecurityGroup : enterpriseSecurityGroups) {
            if (esgId.equals(enterpriseSecurityGroup.getId())){
                List<EnterpriseSecurityGroupRule> rules = enterpriseSecurityGroup.getRules();
                if (rules != null && !rules.isEmpty()){
                    willBeDeletedEsgRuleId = rules.get(0).getEnterpriseSecurityGroupRuleId();
                    break;
                }
            }
        }
        if (willBeDeletedEsgRuleId != null){
            // Example using clientToken for idempotency
            esgClient.deleteEsgRule(willBeDeletedEsgRuleId);
            logger.info("test delete enterprise security group rule finished.");
        }
    }

    @Test
    public void testDeleteEsg() {
        if (!esgIds.isEmpty()) {
            String willDeletedEsgId = esgIds.get(0);
            esgClient.deleteEsg(willDeletedEsgId);
            esgIds.remove(willDeletedEsgId);
        }
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
