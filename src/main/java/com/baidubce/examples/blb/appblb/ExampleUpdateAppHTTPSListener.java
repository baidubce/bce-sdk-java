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
import com.baidubce.services.blb.model.BlbListenerRequest;

import java.util.Arrays;

/**
 * @author chenchangquan
 * @date 2023/11/28
 */
public class ExampleUpdateAppHTTPSListener {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTPS"); // 监听器协议类型
        request.setBlbId("lb-99fa2577"); // 负载均衡器ID
        request.setListenerPort(22); // 监听器端口
        request.setScheduler("LeastConnection"); // 调度算法
        request.setCertIds(Arrays.asList("cert-gs8bktrm7drp")); // 证书ID列表
        try {
            appBlbClient.modifyListenerAttributes(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
