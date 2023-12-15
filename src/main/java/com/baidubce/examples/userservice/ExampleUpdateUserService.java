package com.baidubce.examples.userservice;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.userservice.UserserviceClient;
import com.baidubce.services.userservice.model.UpdateUserServiceRequest;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleUpdateUserService {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        UserserviceClient userserviceClient = new UserserviceClient(config); // 初始化UserserviceClient

        UpdateUserServiceRequest updateUserServiceRequest = new UpdateUserServiceRequest();
        updateUserServiceRequest.setName("updateName1"); // 修改后的服务名称
        updateUserServiceRequest.setDescription("updateDesc"); // 修改后的描述

        try {
            userserviceClient.updateUserService("testService.uservice-9fbf1146.beijing.baidubce.com",
                    updateUserServiceRequest, null);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
