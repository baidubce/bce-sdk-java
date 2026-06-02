/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.UpdateAppPolicyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Example for batch updating policies of an application-type BLB listener.
 */
public class ExampleUpdatePolicys {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        // 构建待更新的策略项列表
        UpdateAppPolicyItem item = new UpdateAppPolicyItem();
        item.setPolicyId("policy-xxxx"); // 策略ID（必需）
        item.setPriority(10);            // 新的优先级（可选）
        item.setDescription("updated"); // 新的描述（可选）

        List<UpdateAppPolicyItem> policyList = new ArrayList<UpdateAppPolicyItem>();
        policyList.add(item);

        try {
            appBlbClient.updatePolicys("lb-2986ed03", 80, "TCP", policyList);
            System.out.println("策略更新成功");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
