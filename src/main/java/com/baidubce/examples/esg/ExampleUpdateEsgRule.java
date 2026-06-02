/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.esg;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.esg.EsgClient;
import com.baidubce.services.esg.EsgClientConfiguration;
import com.baidubce.services.esg.model.UpdateEsgRuleRequest;

public class ExampleUpdateEsgRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EsgClient esgClient = new EsgClient(config); // 初始化EsgClient

        UpdateEsgRuleRequest updateEsgRuleRequest = new UpdateEsgRuleRequest();
        updateEsgRuleRequest.setEnterpriseSecurityGroupRuleId("esgr-q2m9h0yyhjm9");
        // updateEsgRuleRequest.setSourceIp("all"); // 规则源IP
        // updateEsgRuleRequest.setDestIp("all"); // 规则目的IP
        updateEsgRuleRequest.setAction("allow"); // 规则允许
        updateEsgRuleRequest.setPriority(300); // 规则优先级
        updateEsgRuleRequest.setProtocol("tcp"); // 规则协议
        updateEsgRuleRequest.setPortRange("1000-3000"); // 规则端口范围
        updateEsgRuleRequest.setRemark("updated rule remark"); // 规则备注

        // 新增字段示例：
        updateEsgRuleRequest.setSourcePortRange("8000-9000"); // 新增：源端口范围
        updateEsgRuleRequest.setLocalIp("10.0.0.1"); // 新增：本地IP地址
        updateEsgRuleRequest.setRemoteIpSet("ips-wzz7s2sjvsaq"); // 新增：远程IP集合（CIDR格式）
        // 或者使用 remoteIpGroup：
        // updateEsgRuleRequest.setRemoteIpGroup("ipg-xxxxxx"); // 新增：远程IP组ID

        try {
            esgClient.updateEsgRule(updateEsgRuleRequest);
            System.out.println("更新企业安全组规则成功");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

