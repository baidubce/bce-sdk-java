package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;
import com.baidubce.services.etgateway.model.CreateEtGatewayRequest;
import com.baidubce.services.etgateway.model.CreateEtGatewayResponse;
import com.google.common.collect.Lists;

import java.util.UUID;

public class ExampleCreateEtGateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        CreateEtGatewayRequest createEtGatewayRequest = new CreateEtGatewayRequest();

        // 专线网关的名称，由大小写字母、数字以及-_ /.特殊字符组成，必须以字母开头，长度1-65
        createEtGatewayRequest.setName("java_sdk_test_et_gw1");

        createEtGatewayRequest.setVpcId("vpc-63b9cr90g5zn");   // 专线网关所属vpc的id
        createEtGatewayRequest.setSpeed(100);    // 专线网关带宽的限速值，单位为Mbps。限制为为2~10000之间的整数
        createEtGatewayRequest.setDescription("java sdk test et gateway.");   // 专线网关的描述，不超过200字符
        createEtGatewayRequest.setEtId("dcphy-ejtn7ffk9rnd"); // 绑定的物理专线的id，etId和channelId必须同时存在
        createEtGatewayRequest.setChannelId("dedicatedconn-xzxm902fvz9d");  // 绑定的专线通道的id，etId和channelId必须同时存在

        // 专线网关的云端网络，用户可以选本vpc网段或自定义一个或多个网段，仅当参数etId和channelId存在时可以设置
        createEtGatewayRequest.setLocalCidrs(Lists.newArrayList("192.168.0.0/20"));

        createEtGatewayRequest.setClientToken(UUID.randomUUID().toString());    // 幂等性Token

        try {
            CreateEtGatewayResponse createEtGatewayResponse = etGatewayClient.createEtGateway(createEtGatewayRequest);
            System.out.println("createEtGatewayResponse = " + createEtGatewayResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
