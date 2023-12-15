package com.baidubce.examples.endpoint;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.endpoint.EndpointClient;
import com.baidubce.services.endpoint.EndpointClientConfiguration;
import com.baidubce.services.endpoint.model.ReleaseEndpointRequest;

public class ExampleDeleteEndpoint {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EndpointClientConfiguration config = new EndpointClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EndpointClient endpointClient = new EndpointClient(config); // 初始化EndpointClient

        ReleaseEndpointRequest request = new ReleaseEndpointRequest();
        request.setEndpointId("endpoint-11b09ce6"); // 服务网卡的id
        try {
            endpointClient.releaseEndpoint(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }
    }
}
