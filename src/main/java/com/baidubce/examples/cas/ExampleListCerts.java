/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.cas;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cas.CasClient;
import com.baidubce.services.cas.model.request.ListCertsRequest;
import com.baidubce.services.cas.model.response.CertDetailsListResponse;
import com.baidubce.services.cas.model.type.CertType;

public class ExampleListCerts {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        ListCertsRequest listCertsRequest = new ListCertsRequest();
        listCertsRequest.setPageNo(1); // 页码，从1开始
        listCertsRequest.setPageSize(10); // 每页包含的证书数量
        listCertsRequest.setCertType(CertType.DV.name()); // 按证书认证级别过滤，可选

        try {
            CertDetailsListResponse response = casClient.listCerts(listCertsRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
