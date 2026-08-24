/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.cas;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cas.CasClient;
import com.baidubce.services.cas.model.request.RenewOrderConfig;
import com.baidubce.services.cas.model.response.ApiOrderResponse;
import com.baidubce.services.cas.model.type.CertBrand;
import com.baidubce.services.cas.model.type.CertType;
import com.baidubce.services.cas.model.type.ProductType;
import com.baidubce.services.cas.model.type.TimeUnit;

public class ExampleCreateRenewOrder {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        RenewOrderConfig renewOrderConfig = new RenewOrderConfig();
        renewOrderConfig.setSourceResourceId("Your Cert Id"); // 被续费证书的资源id
        renewOrderConfig.setCertBrand(CertBrand.TRUSTASIA); // 证书品牌
        renewOrderConfig.setCertType(CertType.DV); // 证书认证级别
        renewOrderConfig.setProductType(ProductType.SINGLE); // 证书产品类型
        renewOrderConfig.setStandardDomainNum(1); // 标准域名个数
        renewOrderConfig.setDuration(1); // 续费时长
        renewOrderConfig.setTimeUnit(TimeUnit.YEAR); // 续费时长单位

        try {
            ApiOrderResponse response = casClient.createRenewOrder(renewOrderConfig);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
