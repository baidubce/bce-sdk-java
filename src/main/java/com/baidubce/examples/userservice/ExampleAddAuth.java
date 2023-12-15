package com.baidubce.examples.userservice;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.userservice.UserserviceClient;
import com.baidubce.services.userservice.model.AddAuthRequest;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleAddAuth {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        UserserviceClient userserviceClient = new UserserviceClient(config); // 初始化UserserviceClient

        AddAuthRequest addAuthRequest = new AddAuthRequest();
        List<AddAuthRequest.Auth> authList = new ArrayList<AddAuthRequest.Auth>(); // 添加的权限列表
        AddAuthRequest.Auth auth = new AddAuthRequest.Auth();
        auth.setAuth("allow"); // 权限类型，取值为allow和deny
        auth.setUid("7cc5aff841ff4b648028d80b84e1917e"); // 用户ID
        authList.add(auth);
        addAuthRequest.setAuthList(authList);

        try {
            userserviceClient.addAuth("testService.uservice-9fbf1146.beijing.baidubce.com",
                    addAuthRequest, null);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
