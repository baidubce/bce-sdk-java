package com.baidubce.examples.userservice;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.userservice.UserserviceClient;
import com.baidubce.services.userservice.model.CreateUserServiceRequest;
import com.baidubce.services.userservice.model.CreateUserServiceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleCreateUserService {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        UserserviceClient userserviceClient = new UserserviceClient(config); // 初始化UserserviceClient

        CreateUserServiceRequest createUserServiceRequest = new CreateUserServiceRequest();
        createUserServiceRequest.setName("name"); // 服务发布点名称
        createUserServiceRequest.setDescription("desc"); // 描述
        createUserServiceRequest.setServiceName("testService"); // 服务名称
        createUserServiceRequest.setInstanceId("lb-b69cd42f"); // 绑定的实例id
        List<CreateUserServiceRequest.Auth> authList = new ArrayList<CreateUserServiceRequest.Auth>(); // 权限列表
        CreateUserServiceRequest.Auth auth = new CreateUserServiceRequest.Auth();
        auth.setAuth("allow"); // 权限类型，取值为allow和deny
        auth.setUid("*"); // 用户id
        authList.add(auth);
        createUserServiceRequest.setAuthList(authList);

        try {
            CreateUserServiceResponse response = userserviceClient.createUserService(createUserServiceRequest, "");
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
