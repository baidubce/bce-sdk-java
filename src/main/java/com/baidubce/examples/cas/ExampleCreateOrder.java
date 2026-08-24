/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.cas;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cas.CasClient;
import com.baidubce.services.cas.model.request.NewOrderConfig;
import com.baidubce.services.cas.model.response.ApiOrderResponse;
import com.baidubce.services.cas.model.type.CertBrand;
import com.baidubce.services.cas.model.type.CertType;
import com.baidubce.services.cas.model.type.ProductType;
import com.baidubce.services.cas.model.type.TimeUnit;

public class ExampleCreateOrder {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        NewOrderConfig newOrderConfig = new NewOrderConfig();
        newOrderConfig.setCertBrand(CertBrand.TRUSTASIA); // 证书品牌
        newOrderConfig.setCertType(CertType.DV); // 证书认证级别
        newOrderConfig.setProductType(ProductType.SINGLE); // 证书产品类型（单域名/多域名/通配符）
        newOrderConfig.setStandardDomainNum(1); // 标准域名个数
        newOrderConfig.setWildcardDomainNum(0); // 通配符域名个数
        newOrderConfig.setDuration(1); // 购买时长
        newOrderConfig.setTimeUnit(TimeUnit.YEAR); // 购买时长单位
        newOrderConfig.setAutoApply(false); // 是否在下单成功后立即提交证书申请

        try {
            ApiOrderResponse response = casClient.createOrder(newOrderConfig);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
