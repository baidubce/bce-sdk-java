/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.ListBlbSgRequest;
import com.baidubce.services.blb.model.ListBlbSgResponse;

public class ExampleListAppBlbSg {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        ListBlbSgRequest listBlbSgRequest = new ListBlbSgRequest();
        listBlbSgRequest.setBlbId("lb-166d3dbe"); // 要查询的LoadBalancer的标识符

        try {
            ListBlbSgResponse listBlbSgResponse = appBlbClient.listBlbSg(listBlbSgRequest);
            System.out.println(listBlbSgResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
