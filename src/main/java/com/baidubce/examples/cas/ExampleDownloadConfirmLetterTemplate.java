/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.cas;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cas.CasClient;

public class ExampleDownloadConfirmLetterTemplate {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        String certId = "Your Cert Id"; // 证书id

        try {
            byte[] content = casClient.downloadConfirmLetterTemplate(certId); // 确认函模板，pdf格式
            System.out.println("confirm letter template size: " + content.length);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
