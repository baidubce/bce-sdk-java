package com.baidubce.examples.ipv6gateway;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;
import com.baidubce.services.ipv6Gateway.model.CreateIpv6GatewayRequest;
import com.baidubce.services.ipv6Gateway.model.CreateIpv6GatewayResponse;

public class ExampleCreateIpv6Gateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        // 方式1: 使用简单方法创建IPv6网关
        String vpcId = "vpc-g7cufa91auif"; // vpc的ID
        String name = "testIpv6"; // IPv6网关的名称
        Integer bandwidthInMbps = 20; // 带宽

        // 计费信息，目前只支持后付费，默认封装
        try {
            CreateIpv6GatewayResponse response = ipv6GatewayClient.createIpv6Gateway(vpcId, name, bandwidthInMbps);
            System.out.println("Successfully created IPv6 Gateway with ID: " + response.getGatewayId());
        } catch (BceClientException e) {
            System.out.println("Failed to create IPv6 Gateway: " + e.getMessage());
        }

        // 方式2: 使用完整参数创建IPv6网关（包含tags、resourceGroupId、deleteProtect）
        CreateIpv6GatewayRequest request = new CreateIpv6GatewayRequest();
        request.setVpcId("vpc-g7cufa91auif"); // vpc的ID
        request.setName("testIpv6WithTags"); // IPv6网关的名称
        request.setBandwidthInMbps(20); // 带宽

        // 添加标签
        List<TagModel> tags = new ArrayList<TagModel>();
        TagModel tag1 = new TagModel();
        tag1.setTagKey("project");
        tag1.setTagValue("test");
        tags.add(tag1);
        
        TagModel tag2 = new TagModel();
        tag2.setTagKey("env");
        tag2.setTagValue("dev");
        tags.add(tag2);
        
        request.setTags(tags);

        // 设置资源组ID
        request.setResourceGroupId("RESG-test123");

        // 开启释放保护
        request.setDeleteProtect(true);

        try {
            CreateIpv6GatewayResponse response = ipv6GatewayClient.createIpv6Gateway(request);
            System.out.println("Successfully created IPv6 Gateway with full parameters, ID: " + response.getGatewayId());
        } catch (BceClientException e) {
            System.out.println("Failed to create IPv6 Gateway: " + e.getMessage());
        }
    }
}
