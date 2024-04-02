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
package com.baidubce.services.blb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.model.AppBackendPolicyRequest;
import com.baidubce.services.blb.model.AppBackendServer;
import com.baidubce.services.blb.model.AppIpGroupMember;
import com.baidubce.services.blb.model.AppPolicy;
import com.baidubce.services.blb.model.AppRule;
import com.baidubce.services.blb.model.AppSgPortRequest;
import com.baidubce.services.blb.model.BlbDetailRequest;
import com.baidubce.services.blb.model.BlbInstance;
import com.baidubce.services.blb.model.BlbListenerRequest;
import com.baidubce.services.blb.model.EsgOperateRequest;
import com.baidubce.services.blb.model.ListBlbEsgResponse;
import com.baidubce.services.blb.model.ListBlbSgRequest;
import com.baidubce.services.blb.model.ListBlbSgResponse;
import com.baidubce.services.blb.model.SgOperateRequest;
import com.baidubce.services.tag.model.Tag;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * appBlbClient test
 */
public class AppBlbClientTest {

    private static final Logger logger = LoggerFactory.getLogger(AppBlbClientTest.class);
    private static final String ak = "3251d60a16f94c839f7aa4b87ed4913b";
    private static final String sk = "21d8c3cdfe1242148e29465fca734e1f";
    private AppBlbClient blbClient;

    @Before
    public void setUp() {
        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("blb.bj.qasandbox.baidu-int.com");
        blbClient = new AppBlbClient(config);
    }


    private static final String BLB_ID = "lb-2986ed03";
    private static final String SERVER_GROUP_ID = "sg-1fd8a838";
    private static final String IP_GROUP_ID = "ip_group-eca5e600";
    private static final String IP_GROUP_NAME = "zzz";
    private static final String IP_GROUP_DESC = "ipGroupDesc";
    private static final String MEMBER_ID = "ip_member-809d66fa";
    private static final String IP = "192.168.0.10";
    private static final int MEMBER_PORT = 90;
    private static final int WEIGHT = 100;
    private static final String IP_GROUP_BACKEND_POLICY_TYPE = "TCP";
    private static final String IP_GROUP_BACKEND_POLICY_ID = "ip_group_policy-ac95cca5";
    private static final int HEALTH_CHECK_TIMEOUT_IN_SECOND = 3;
    // policy
    private static final int PRIORITY = 7;
    private static final String RULE_KEY = "*";
    private static final String RULE_VALUE = "*";
    private static final int LISTENER_PORT = 98;
    private static final int BACKEND_PORT = 81;



    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createBlbTest() {
        Tag tag = new Tag();
        tag.setTagKey("ccqKey");
        tag.setTagValue("ccqValue");
        List<Tag> tagList = new ArrayList<Tag>();
        tagList.add(tag);
        toJsonPrettyString("create blb Results",
                blbClient.createBlb("blb-test", "blb-desc", "vpc-ffi6z1s0g978", "sbn-giusd2f5p2s5", tagList));
    }

    @Test
    public void listBlbsTest() {
        toJsonPrettyString("list blbs result", blbClient.listBlbs("", "", "", ""));
    }

    @Test
    public void blbDetailTest() {
        BlbDetailRequest request = new BlbDetailRequest();
        request.setBlbId("lb-d23ead80");
        BlbInstance response = blbClient.blbDetail(request);
        System.out.println(response);
    }


    @Test
    public void modifyBlbAttributesTest() {
        blbClient.modifyBlbAttributes("lb-99fa2577", "blb-modify-name", "blb-modify-desc");
    }

    @Test
    public void deleteBlbTest() {
        blbClient.deleteBlb("lb-c0db8742");
    }

    @Test
    public void createTCPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(81);
        request.setScheduler("Hash");
        request.setType("TCP");
        blbClient.createListener(request);
    }

    @Test
    public void createUDPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("UDP");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(82);
        request.setScheduler("Hash");
        blbClient.createListener(request);
    }

    @Test
    public void createHTTPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTP");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(90);
        request.setScheduler("RoundRobin");
        blbClient.createListener(request);
    }

    @Test
    public void createHTTPSListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTPS");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(22);
        request.setScheduler("RoundRobin");
        request.setCertIds(Arrays.asList("cert-gs8bktrm7drp"));
        blbClient.createListener(request);
    }

    @Test
    public void createSSLListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("SSL");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(23);
        request.setScheduler("RoundRobin");
        request.setCertIds(Arrays.asList("cert-gs8bktrm7drp"));
        blbClient.createListener(request);
    }

    @Test
    public void listTCPListenersTest() {
        toJsonPrettyString("list TCP listener results:", blbClient.listTcpListener("lb-b69cd42f"));
    }

    @Test
    public void listUDPListenersTest() {
        toJsonPrettyString("list UDP listener results:", blbClient.listUdpListener("lb-99fa2577"));
    }

    @Test
    public void listHTTPListenersTest() {
        toJsonPrettyString("list HTTP listener results:", blbClient.listHttpListener("lb-99fa2577"));
    }

    @Test
    public void listHTTPSListenersTest() {
        toJsonPrettyString("list HTTPS listener results:", blbClient.listHttpsListener("lb-99fa2577"));
    }

    @Test
    public void listSSLListenersTest() {
        toJsonPrettyString("list SSL listener results:", blbClient.listSslListener("lb-99fa2577"));
    }

    @Test
    public void listAllListenersTest() {
        toJsonPrettyString("list all listener results:", blbClient.listAllListener("lb-b69cd42f"));
    }

    @Test
    public void modifyTCPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("TCP");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(81);
        request.setScheduler("RoundRobin");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyUDPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("UDP");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(82);
        request.setScheduler("LeastConnection");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyHTTPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTP");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(90);
        request.setScheduler("LeastConnection");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyHTTPSListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTPS");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(22);
        request.setScheduler("LeastConnection");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifySSLListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("SSL");
        request.setBlbId("lb-99fa2577");
        request.setListenerPort(23);
        request.setScheduler("LeastConnection");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void deleteListenerTest() {
        blbClient.deleteListener("lb-99fa2577", Arrays.asList(81, 82));
    }

    @Test
    public void createAppServerGroupTest() {
        List<AppBackendServer> backendServerList = new ArrayList<AppBackendServer>();
        AppBackendServer appBackendServer = new AppBackendServer();
        appBackendServer.setInstanceId("i-q9dlHUJm");
        appBackendServer.setWeight(100);
        backendServerList.add(appBackendServer);
        toJsonPrettyString("create appServerGroup Results"
                , blbClient.createAppServerGroup("lb-b69cd42f", "", "", backendServerList));
    }

    @Test
    public void createAppIpGroupTest() {
        List<AppIpGroupMember> memberList = new ArrayList<AppIpGroupMember>();
        AppIpGroupMember member = new AppIpGroupMember();
        member.setIp(IP);
        member.setPort(MEMBER_PORT);
        memberList.add(member);
        toJsonPrettyString("create appIpGroup Results"
                , blbClient.createAppIpGroup(BLB_ID, IP_GROUP_NAME, IP_GROUP_DESC, memberList));
    }

    @Test
    public void modifyAppIpGroupAttributesTest() {
        blbClient.modifyAppIpGroupAttributes(BLB_ID, IP_GROUP_ID, IP_GROUP_NAME, IP_GROUP_DESC);
    }

    @Test
    public void listAppIpGroupTest() {
        toJsonPrettyString("list AppIpGroup results:",
                blbClient.listAppIpGroup(BLB_ID, IP_GROUP_NAME));
    }

    @Test
    public void deleteAppIpGroupTest() {
        blbClient.deleteAppIpGroup(BLB_ID, IP_GROUP_ID);
    }

    @Test
    public void modifyAppServerGroupAttributesTest() {
        blbClient.modifyAppServerGroupAttributes("lb-99fa2577", "sg-9153c2d9", "ccqSg", "ccqSgDesc");
    }

    @Test
    public void listAppServerGroupTest() {
        toJsonPrettyString("list AppServerGroup results:",
                blbClient.listAppServerGroup("lb-b69cd42f", ""));
    }

    @Test
    public void createAppIpGroupBackendPolicyTest() {
        AppBackendPolicyRequest request = new AppBackendPolicyRequest();
        request.setBlbId(BLB_ID);
        request.setIpGroupId(IP_GROUP_ID);
        request.setType(IP_GROUP_BACKEND_POLICY_TYPE);
        blbClient.createAppIpGroupBackendPolicy(request);
    }

    @Test
    public void modifyAppIpGroupBackendPolicyTest() {
        AppBackendPolicyRequest request = new AppBackendPolicyRequest();
        request.setBlbId(BLB_ID);
        request.setIpGroupId(IP_GROUP_ID);
        request.setId(IP_GROUP_BACKEND_POLICY_ID);
        request.setHealthCheckTimeoutInSecond(HEALTH_CHECK_TIMEOUT_IN_SECOND);
        blbClient.modifyAppIpGroupBackendPolicyAttributes(request);
    }

    @Test
    public void deleteAppIpGroupBackendPolicyTest() {
        blbClient.deleteAppIpGroupBackendPolicy(BLB_ID, IP_GROUP_ID, Arrays.asList(IP_GROUP_BACKEND_POLICY_ID));
    }

    @Test
    public void createIpGroupMemberTest() {
        List<AppIpGroupMember> memberList = new ArrayList<AppIpGroupMember>();
        AppIpGroupMember member = new AppIpGroupMember();
        member.setIp(IP);
        member.setPort(MEMBER_PORT);
        memberList.add(member);
        blbClient.createIpGroupMember(BLB_ID, IP_GROUP_ID, memberList);
    }

    @Test
    public void modifyIpGroupMemberTest() {
        List<AppIpGroupMember> memberList = new ArrayList<AppIpGroupMember>();
        AppIpGroupMember member = new AppIpGroupMember();
        member.setMemberId(MEMBER_ID);
        member.setPort(MEMBER_PORT);
        memberList.add(member);
        blbClient.modifyIpGroupMember(BLB_ID, IP_GROUP_ID, memberList);
    }

    @Test
    public void listIpGroupMemberTest() {
        toJsonPrettyString("list ipGroupMember results:",
                blbClient.listIpGroupMember(BLB_ID, IP_GROUP_ID));
    }

    @Test
    public void deleteIpGroupMemberTest() {
        blbClient.deleteIpGroupMember(BLB_ID, IP_GROUP_ID, Arrays.asList(MEMBER_ID));
    }

    @Test
    public void deleteAppServerGroupTest() {
        blbClient.deleteAppServerGroup("lb-99fa2577", "sg-c2eac39c");
    }

    @Test
    public void createAppServerGroupPortTest() {
        AppSgPortRequest request = new AppSgPortRequest();
        request.setBlbId("lb-b69cd42f");
        request.setSgId("sg-0aae4f4c");
        request.setPort(90);
        request.setType("HTTP");
        request.setHealthCheck("HTTP");
        toJsonPrettyString("create appServerGroupPort Results"
                , blbClient.createAppServerGroupPort(request));
    }

    @Test
    public void modifyAppServerGroupPortTest() {
        AppSgPortRequest request = new AppSgPortRequest();
        request.setBlbId("lb-99fa2577");
        request.setSgId("sg-9153c2d9");
        request.setPortId("port-2b68dae3");
        request.setHealthCheck("HTTP");
        request.setHealthCheckTimeoutInSecond(50);
        blbClient.modifyAppServerGroupPortAttributes(request);
    }

    @Test
    public void deleteAppServerGroupPortTest() {
        blbClient.deleteAppServerGroupPort("lb-99fa2577", "sg-9153c2d9", Arrays.asList("port-2b68dae3"));
    }

    @Test
    public void createBlbRsTest() {
        List<AppBackendServer> backendServerList = new ArrayList<AppBackendServer>();
        AppBackendServer appBackendServer = new AppBackendServer();
        appBackendServer.setInstanceId("i-mWyp1Bjd");
        appBackendServer.setWeight(100);
        backendServerList.add(appBackendServer);
        blbClient.createBlbRs("lb-99fa2577", "sg-0bf84edc", backendServerList);
    }

    @Test
    public void modifyBlbRsTest() {
        List<AppBackendServer> backendServerList = new ArrayList<AppBackendServer>();
        AppBackendServer appBackendServer = new AppBackendServer();
        appBackendServer.setInstanceId("i-mWyp1Bjd");
        appBackendServer.setWeight(50);
        backendServerList.add(appBackendServer);
        blbClient.modifyBlbRs("lb-99fa2577", "sg-0bf84edc", backendServerList);
    }

    @Test
    public void listBlbRsTest() {
        toJsonPrettyString("list blbRs results:",
                blbClient.listBlbRs("lb-b69cd42f", "sg-b8221879"));
    }

    @Test
    public void deleteBlbRsTest() {
        blbClient.deleteBlbRs("lb-99fa2577", "sg-9153c2d9", Arrays.asList("i-mWyp1Bjd"));
    }

    @Test
    public void listBlbRsMountTest() {
        toJsonPrettyString("list blbRsMount results:",
                blbClient.listBlbRsMount("lb-b69cd42f", "sg-b8221879"));
    }

    @Test
    public void listBlbRsUnMountTest() {
        toJsonPrettyString("list blbRsUnMount results:",
                blbClient.listBlbRsUnMount("lb-b69cd42f", "sg-b8221879"));
    }

    @Test
    public void createPolicysTest() {
        List<AppPolicy> appPolicyVos = new ArrayList<AppPolicy>();
        AppPolicy appPolicy = new AppPolicy();
        appPolicyVos.add(appPolicy);
        appPolicy.setPriority(PRIORITY);
        List<AppRule> ruleList = new ArrayList<AppRule>();
        AppRule appRule = new AppRule();
        appRule.setKey(RULE_KEY);
        appRule.setValue(RULE_VALUE);
        ruleList.add(appRule);
        appPolicy.setRuleList(ruleList);
        appPolicy.setAppServerGroupId(SERVER_GROUP_ID);
        appPolicy.setBackendPort(BACKEND_PORT);
        toJsonPrettyString("create policy results:",
                blbClient.createPolicys(BLB_ID, LISTENER_PORT, null, appPolicyVos));
    }

    @Test
    public void createIpGroupPolicysTest() {
        List<AppPolicy> appPolicyVos = new ArrayList<AppPolicy>();
        AppPolicy appPolicy = new AppPolicy();
        appPolicyVos.add(appPolicy);
        appPolicy.setPriority(PRIORITY);
        List<AppRule> ruleList = new ArrayList<AppRule>();
        AppRule appRule = new AppRule();
        appRule.setKey(RULE_KEY);
        appRule.setValue(RULE_VALUE);
        ruleList.add(appRule);
        appPolicy.setRuleList(ruleList);
        appPolicy.setAppIpGroupId(IP_GROUP_ID);
        toJsonPrettyString("create ip group policy results:",
                blbClient.createPolicys(BLB_ID, LISTENER_PORT, null, appPolicyVos));
    }

    @Test
    public void listPolicysTest() {
        toJsonPrettyString("list policy results:",
                blbClient.listPolicys(BLB_ID, LISTENER_PORT));
    }

    @Test
    public void deletePolicysTest() {
        blbClient.deletePolicys("lb-99fa2577", 90, Arrays.asList("policy-01940c30"));
    }

    @Test
    public void bindSgTest() {
        SgOperateRequest sgOperateRequest = new SgOperateRequest();
        sgOperateRequest.setBlbId("lb-166d3dbe"); // 所属LoadBalancer的标识符
        List<String> securityGroupIds = new ArrayList<String>(); // 绑定的普通安全组ID列表
        securityGroupIds.add("g-msrvz0w5kvuz"); // 绑定的普通安全组ID
        sgOperateRequest.setSecurityGroupIds(securityGroupIds);

        blbClient.bindSg(sgOperateRequest);
    }

    @Test
    public void listBlbSgTest() {
        ListBlbSgRequest listBlbSgRequest = new ListBlbSgRequest();
        listBlbSgRequest.setBlbId("lb-166d3dbe"); // 要查询的LoadBalancer的标识符

        ListBlbSgResponse listBlbSgResponse = blbClient.listBlbSg(listBlbSgRequest);
        toJsonPrettyString("listBlbSg", listBlbSgResponse);
    }

    @Test
    public void unBindSgTest() {
        SgOperateRequest sgOperateRequest = new SgOperateRequest();
        sgOperateRequest.setBlbId("lb-166d3dbe"); // 所属LoadBalancer的标识符
        List<String> securityGroupIds = new ArrayList<String>(); // 解绑的普通安全组ID列表
        securityGroupIds.add("g-msrvz0w5kvuz"); // 解绑的普通安全组ID
        sgOperateRequest.setSecurityGroupIds(securityGroupIds);

        blbClient.unBindSg(sgOperateRequest);
    }

    @Test
    public void bindEsgTest() {
        EsgOperateRequest esgOperateRequest = new EsgOperateRequest();
        esgOperateRequest.setBlbId("lb-166d3dbe"); // 所属LoadBalancer的标识符
        List<String> enterpriseSecurityGroupIds = new ArrayList<String>(); // 绑定的企业安全组ID列表
        enterpriseSecurityGroupIds.add("esg-tv9jcdnyz5ap"); // 绑定的企业安全组ID
        esgOperateRequest.setEnterpriseSecurityGroupIds(enterpriseSecurityGroupIds);

        blbClient.bindEsg(esgOperateRequest);
    }

    @Test
    public void listBlbEsgTest() {
        ListBlbSgRequest listBlbSgRequest = new ListBlbSgRequest();
        listBlbSgRequest.setBlbId("lb-166d3dbe"); // 要查询的LoadBalancer的标识符

        ListBlbEsgResponse listBlbEsgResponse = blbClient.listBlbEsg(listBlbSgRequest);
        toJsonPrettyString("listBlbEsg", listBlbEsgResponse);
    }

    @Test
    public void unBindEsgTest() {
        EsgOperateRequest esgOperateRequest = new EsgOperateRequest();
        esgOperateRequest.setBlbId("lb-166d3dbe"); // 所属LoadBalancer的标识符
        List<String> enterpriseSecurityGroupIds = new ArrayList<String>(); // 解绑的企业安全组ID列表
        enterpriseSecurityGroupIds.add("esg-tv9jcdnyz5ap"); // 解绑的企业安全组ID
        esgOperateRequest.setEnterpriseSecurityGroupIds(enterpriseSecurityGroupIds);

        blbClient.unBindEsg(esgOperateRequest);
    }
}
