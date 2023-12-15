package com.baidubce.examples.endpoint;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.endpoint.EndpointClient;
import com.baidubce.services.endpoint.EndpointClientConfiguration;
import com.baidubce.services.endpoint.model.ServiceResponse;

public class ExampleGetPublicServices {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EndpointClientConfiguration config = new EndpointClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EndpointClient endpointClient = new EndpointClient(config); // 初始化EndpointClient

        try {
            ServiceResponse serviceResponse = endpointClient.listService(); // 查询可挂载的公共服务
            System.out.println(serviceResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }
    }
}
