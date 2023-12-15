/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.DetachInstanceRequest;

import java.util.UUID;

public class ExampleDetachInstance {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnId = "csn-b65m2hez6h60iwa1";  // 云智能网ID
        String instanceAccountId = "Your Instance Account ID";  // 跨账号卸载网络实例场景下，网络实例所属账号的ID
        DetachInstanceRequest detachInstanceRequest = DetachInstanceRequest.builder()
                .instanceType("vpc")    // 卸载的实例类型，取值 [vpc|channel|bec_vpc]，分别表示私有网络、专线通道、边缘网络
                .instanceId("vpc-33ajnsvj41r6") // 卸载的实例ID
                .instanceRegion("bj")   // 卸载的实例所属的region
                .instanceAccountId(instanceAccountId)
                .build();
        String clientToken = UUID.randomUUID().toString();

        try {
            csnClient.detachInstance(csnId, detachInstanceRequest, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
