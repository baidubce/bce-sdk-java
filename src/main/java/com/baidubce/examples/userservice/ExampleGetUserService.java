package com.baidubce.examples.userservice;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.userservice.UserserviceClient;
import com.baidubce.services.userservice.model.GetUserServiceResponse;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleGetUserService {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        UserserviceClient userserviceClient = new UserserviceClient(config); // 初始化UserserviceClient

        try {
            GetUserServiceResponse response =
                    userserviceClient.getUserService("testService.uservice-9fbf1146.beijing.baidubce.com");
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
