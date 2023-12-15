package com.baidubce.examples.endpoint;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.endpoint.EndpointClient;
import com.baidubce.services.endpoint.EndpointClientConfiguration;
import com.baidubce.services.endpoint.model.ModifyEndpointRequest;

public class ExampleUpdateEndpoint {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EndpointClientConfiguration config = new EndpointClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EndpointClient endpointClient = new EndpointClient(config); // 初始化EndpointClient

        String endpointId = "endpoint-11b09ce6"; // 服务网卡的id
        ModifyEndpointRequest request = new ModifyEndpointRequest();
        request.setEndpointId(endpointId);
        request.setName("sdk-update-2"); // 服务网卡名称,长度不超过65个字符，可由数字，字符，下划线、中文组成
        try {
            endpointClient.modifyEndpoint(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }
    }
}
