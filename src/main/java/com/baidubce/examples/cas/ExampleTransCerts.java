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
import com.baidubce.services.cas.model.request.BatchRequest;

public class ExampleTransCerts {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        String newUserId = "Your New User Id"; // 接收证书的用户id

        List<String> certIds = new ArrayList<String>(); // 待过户的证书id列表
        certIds.add("Your Cert Id"); // 待过户的证书id
        BatchRequest<String> batchRequest = new BatchRequest<String>(certIds);

        try {
            casClient.transCerts(newUserId, batchRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
