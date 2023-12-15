package com.baidubce.examples.userservice;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.userservice.UserserviceClient;
import com.baidubce.services.userservice.model.EditAuthRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleEditAuth {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        UserserviceClient userserviceClient = new UserserviceClient(config); // 初始化UserserviceClient

        EditAuthRequest editAuthRequest = new EditAuthRequest();
        List<EditAuthRequest.Auth>  authList = new ArrayList<EditAuthRequest.Auth>(); // 添加的权限列表
        EditAuthRequest.Auth auth = new EditAuthRequest.Auth();
        auth.setAuth("deny"); // 权限类型，取值为allow和deny
        auth.setUid("7cc5aff841ff4b648028d80b84e1917e"); // 用户uid
        authList.add(auth);
        editAuthRequest.setAuthList(authList);

        try {
            userserviceClient.editAuth("testService.uservice-9fbf1146.beijing.baidubce.com",
                    editAuthRequest, null);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
