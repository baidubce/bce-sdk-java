/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.cas;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cas.CasClient;
import com.baidubce.services.cas.model.request.ApplyCertRequest;
import com.baidubce.services.cas.model.type.AuthType;
import com.baidubce.services.cas.model.type.EncryptionStrength;
import com.baidubce.services.cas.model.type.EncryptionType;
import com.baidubce.services.cas.model.type.HashAlgo;

public class ExampleApplyCert {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        String certId = "Your Cert Id"; // 证书id

        ApplyCertRequest applyCertRequest = new ApplyCertRequest();
        applyCertRequest.setPrimaryDomain("www.example.com"); // 主域名
        List<String> bindDomains = new ArrayList<String>(); // 附加域名列表
        bindDomains.add("api.example.com"); // 附加域名
        applyCertRequest.setBindDomains(bindDomains);
        applyCertRequest.setAuthType(AuthType.DNS.name()); // 域名验证方式
        applyCertRequest.setEncryptionType(EncryptionType.RSA.name()); // 加密算法
        applyCertRequest.setEncryptionStrength(EncryptionStrength.RSA_2048.getValue()); // 密钥长度
        applyCertRequest.setHashAlgo(HashAlgo.SHA256.name()); // 摘要算法
        applyCertRequest.setCompany("Your Company"); // 公司名称
        applyCertRequest.setAdminEmail("admin@example.com"); // 订单管理员邮箱

        try {
            casClient.applyCert(certId, applyCertRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
