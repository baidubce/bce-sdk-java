/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.endpoint;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.endpoint.EndpointClient;
import com.baidubce.services.endpoint.EndpointClientConfiguration;
import com.baidubce.services.endpoint.model.UpdateSecurityGroups;

public class ExampleUpdateEndpointSecurityGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EndpointClientConfiguration config = new EndpointClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EndpointClient endpointClient = new EndpointClient(config); // 初始化EndpointClient

        UpdateSecurityGroups updateSecurityGroups = new UpdateSecurityGroups();
        updateSecurityGroups.setEndpointId("endpoint-daa07eb2");
        updateSecurityGroups.setSecurityGroupIds(Arrays.asList("g-6r0ds9xbxwes"));

        try {
            endpointClient.updateSecurityGroups(updateSecurityGroups);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
