package com.baidubce.examples.endpoint;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.endpoint.EndpointClient;
import com.baidubce.services.endpoint.EndpointClientConfiguration;
import com.baidubce.services.endpoint.model.CreateEndpointRequest;
import com.baidubce.services.endpoint.model.CreateEndpointResponse;

public class ExampleCreateEndpoint {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EndpointClientConfiguration config = new EndpointClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EndpointClient endpointClient = new EndpointClient(config); // 初始化EndpointClient

        CreateEndpointRequest request = new CreateEndpointRequest();
        request.setName("sdk-1"); // 服务网卡的名称，大小写字母、数字以及-_/.特殊字符、中文，必须以字母开头，长度1-65
        request.setVpcId("vpc-va6hhmx62vyp"); // 所属vpc的id
        request.setSubnetId("sbn-1rmebtrbxxn6"); // 所在子网的id
        request.setService("www.test.com"); // 挂载的服务域名
        request.setDescription("sdk create"); // 服务网卡描述
        request.setIpAddress("192.168.0.1"); // 指定服务网卡ip地址,不传自动分配ip地址
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid"); // 计费信息
        request.setBilling(billing);
        try {
            CreateEndpointResponse response = endpointClient.createEndpoint(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }

    }
}
