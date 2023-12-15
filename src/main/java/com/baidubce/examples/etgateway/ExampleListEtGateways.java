package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;
import com.baidubce.services.etgateway.model.ListEtGatewayRequest;
import com.baidubce.services.etgateway.model.ListEtGatewayResponse;

public class ExampleListEtGateways {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        ListEtGatewayRequest listEtGatewayRequest = new ListEtGatewayRequest();
        listEtGatewayRequest.setVpcId("vpc-63b9cr90g5zn"); // vpc的id
        listEtGatewayRequest.setEtGatewayId("");    // 专线网关的id
        listEtGatewayRequest.setName("java_sdk");   // 专线网关的名称，支持模糊查询
        listEtGatewayRequest.setStatus("running");   // 专线网关的状态
        listEtGatewayRequest.setMarker(""); // 批量获取列表的查询的起始位置，是一个由系统生成的字符串
        listEtGatewayRequest.setMaxKeys(10);    // 每页包含的最大数量，最大数量不超过1000。缺省值为1000


        try {
            ListEtGatewayResponse listEtGatewayResponse = etGatewayClient.listEtGateways(listEtGatewayRequest);
            System.out.println("listEtGatewayResponse = " + listEtGatewayResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
