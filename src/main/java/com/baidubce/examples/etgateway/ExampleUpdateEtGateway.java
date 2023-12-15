package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;
import com.baidubce.services.etgateway.model.UpdateEtGatewayRequest;
import com.google.common.collect.Lists;

import java.util.UUID;

public class ExampleUpdateEtGateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        UpdateEtGatewayRequest updateEtGatewayRequest = new UpdateEtGatewayRequest();
        updateEtGatewayRequest.setEtGatewayId("dcgw-9pwwq206xj58");  // 专线网关的id

        // 专线网关的名称，由大小写字母、数字以及-_ /.特殊字符组成，必须以字母开头，长度1-65
        updateEtGatewayRequest.setName("java_sdk_et_gw_update");

        updateEtGatewayRequest.setSpeed(200);    // 专线网关带宽的限速值，单位为Mbps。限制为2~100000之间的整数
        updateEtGatewayRequest.setDescription("java sdk test update et gateway.");    // 专线网关的描述，不超过200字符

        // 专线网关的IPv4云端网络，用户可以选本vpc网段或自定义一个或多个网段
        updateEtGatewayRequest.setLocalCidrs(Lists.newArrayList("192.168.0.0/20"));

        updateEtGatewayRequest.setEnableIpv6(1);    // IPv6功能是否开启，1是0否，IPv6为白名单功能

        // 专线网关的IPv6云端网络，用户可以选本vpc网段或自定义一个或多个IPv6网段
        updateEtGatewayRequest.setIpv6LocalCidrs(Lists.newArrayList("240c:4085:0:6a02::/80"));

        updateEtGatewayRequest.setClientToken(UUID.randomUUID().toString());    // 幂等性Token

        try {
            etGatewayClient.updateEtGateway(updateEtGatewayRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
