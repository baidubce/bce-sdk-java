/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eiptp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eiptp.EipTpClient;
import com.baidubce.services.eiptp.model.ListEipTpsRequest;
import com.baidubce.services.eiptp.model.ListEipTpsResponse;

public class ExampleListEipTps {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipTpClient eipTpClient = new EipTpClient(config); // 初始化EipTpClient

        ListEipTpsRequest listEipTpsRequest = new ListEipTpsRequest();
        listEipTpsRequest.setMarker(""); // 批量获取列表的查询的起始位置，是一个由系统生成的字符串
        listEipTpsRequest.setMaxKeys(10); // 每页包含的最大数量，最大数量不超过1000，缺省值为1000

        try {
            ListEipTpsResponse response = eipTpClient.listEipTps(listEipTpsRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
