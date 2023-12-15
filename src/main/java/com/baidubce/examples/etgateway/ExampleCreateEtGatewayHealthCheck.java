package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;
import com.baidubce.services.etgateway.model.CreateEtGatewayHealthCheckRequest;

import java.util.UUID;

public class ExampleCreateEtGatewayHealthCheck {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        CreateEtGatewayHealthCheckRequest createEtGatewayHealthCheckRequest = new CreateEtGatewayHealthCheckRequest();
        createEtGatewayHealthCheckRequest.setEtGatewayId("dcgw-9pwwq206xj58");   // 专线网关的id
        createEtGatewayHealthCheckRequest.setHealthCheckSourceIp("");   // 若不传该参数，系统会自动分配一个IP
        createEtGatewayHealthCheckRequest.setHealthCheckType("ICMP");   // 参数可取值为"ICMP",默认为"ICMP"
        createEtGatewayHealthCheckRequest.setHealthCheckPort(8000);     // 健康检查的端口
        createEtGatewayHealthCheckRequest.setHealthCheckInterval(40);   // 健康检查的间隔，1-60之间的整数，单位s
        createEtGatewayHealthCheckRequest.setHealthThreshold(2);    // 健康检查阈值，2-5之间的整数
        createEtGatewayHealthCheckRequest.setUnhealthThreshold(4);  // 不健康检查阈值，2-5之间的整数

        // 是否自动生成探测路由，默认开启。如需关闭，选择false。
        createEtGatewayHealthCheckRequest.setAutoGenerateRouteRule(false);

        createEtGatewayHealthCheckRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            etGatewayClient.createEtGatewayHealthCheck(createEtGatewayHealthCheckRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
