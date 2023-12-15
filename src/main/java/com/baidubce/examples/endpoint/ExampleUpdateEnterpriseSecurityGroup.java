/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.endpoint;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.endpoint.EndpointClient;
import com.baidubce.services.endpoint.EndpointClientConfiguration;
import com.baidubce.services.endpoint.model.UpdateEnterpriseSecurityGroups;

public class ExampleUpdateEnterpriseSecurityGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EndpointClientConfiguration config = new EndpointClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EndpointClient endpointClient = new EndpointClient(config); // 初始化EndpointClient

        UpdateEnterpriseSecurityGroups updateSecurityGroups = new UpdateEnterpriseSecurityGroups();
        updateSecurityGroups.setEndpointId("endpoint-daa07eb2");
        updateSecurityGroups.setEnterpriseSecurityGroupIds(Arrays.asList("esg-6q04xad5xmh6"));

        try {
            endpointClient.updateEnterpriseSecurityGroups(updateSecurityGroups);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
