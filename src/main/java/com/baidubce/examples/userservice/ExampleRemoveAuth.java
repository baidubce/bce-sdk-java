package com.baidubce.examples.userservice;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.userservice.UserserviceClient;
import com.baidubce.services.userservice.model.RemoveAuthRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleRemoveAuth {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        UserserviceClient userserviceClient = new UserserviceClient(config); // 初始化UserserviceClient

        RemoveAuthRequest removeAuthRequest = new RemoveAuthRequest();
        List<String> uidList = new ArrayList<String>(); // 要移除的用户uid列表
        uidList.add("7cc5aff841ff4b648028d80b84e1917e"); // 用户uid
        removeAuthRequest.setUidList(uidList);

        try {
            userserviceClient.removeAuth("testService.uservice-9fbf1146.beijing.baidubce.com",
                    removeAuthRequest, null);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
