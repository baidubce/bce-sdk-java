/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.subnet;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.subnet.SubnetClient;
import com.baidubce.services.subnet.SubnetClientConfiguration;
import com.baidubce.services.subnet.model.CreateSubnetRequest;
import com.baidubce.services.subnet.model.CreateSubnetResponse;
import com.baidubce.services.tag.model.Tag;
import com.google.common.collect.Lists;

public class ExampleCreateSubnet {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        SubnetClientConfiguration config = new SubnetClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        SubnetClient subnetClient = new SubnetClient(config); // 初始化SubnetClient

        CreateSubnetRequest createSubnetRequest = new CreateSubnetRequest();
        createSubnetRequest.setVpcId("vpc-6fykvyb927mk");
        createSubnetRequest.setCidr("10.0.0.0/28");
        createSubnetRequest.setName("testSubnet");
        createSubnetRequest.setZoneName("cn-bj-a");
        createSubnetRequest.setVpcSecondaryCidr("10.0.0.0/24");

        Tag tag = new Tag();
        tag.setTagKey("tagKey");
        tag.setTagValue("tagValue");
        createSubnetRequest.setTags(Lists.newArrayList(tag));

        try {
            CreateSubnetResponse createSubnetResponse = subnetClient.createSubnet(createSubnetRequest);
            System.out.println(createSubnetResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
