/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.cas;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cas.CasClient;
import com.baidubce.services.cas.model.request.PriceConfig;
import com.baidubce.services.cas.model.response.PriceResponse;
import com.baidubce.services.cas.model.type.CertBrand;
import com.baidubce.services.cas.model.type.CertType;
import com.baidubce.services.cas.model.type.ProductType;
import com.baidubce.services.cas.model.type.TimeUnit;

public class ExampleCalculatePrice {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        PriceConfig priceConfig = new PriceConfig();
        priceConfig.setCertBrand(CertBrand.TRUSTASIA); // 证书品牌
        priceConfig.setCertType(CertType.DV); // 证书认证级别
        priceConfig.setProductType(ProductType.SINGLE); // 证书产品类型
        priceConfig.setStandardDomainNum(1); // 标准域名个数
        priceConfig.setDuration(1); // 购买时长
        priceConfig.setTimeUnit(TimeUnit.YEAR); // 购买时长单位

        try {
            PriceResponse response = casClient.calculatePrice(priceConfig);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
