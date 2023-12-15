/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.ListInstanceResponse;

public class ExampleListInstance {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String testCsnId = "csn-b65m2hez6h60iwa1";
        String marker = ""; // 批量获取列表的查询的起始位置，是一个由系统生成的字符串
        Integer maxKeys = 10;   // 每页包含的最大数量，最大数量不超过1000，缺省值为1000

        try {
            ListInstanceResponse listInstanceResponse = csnClient.listInstance(testCsnId, marker, maxKeys);
            System.out.println("listInstanceResponse = " + listInstanceResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
