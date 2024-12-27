package com.baidubce.examples.vpc;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpc.VpcClient;
import com.baidubce.services.vpc.VpcClientConfiguration;
import com.baidubce.services.vpc.model.GetVpcResourceIpRequest;
import com.baidubce.services.vpc.model.GetVpcResourceIpResponse;

public class ExampleGetVpcResourceIpInfo {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpcClientConfiguration config = new VpcClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpcClient vpcClient = new VpcClient(config); // 初始化VpcClient

        GetVpcResourceIpRequest request = new GetVpcResourceIpRequest();
        request.setVpcId("vpc-xysha3j7gce1"); // vpc id
        request.setSubnetId("sbn-iz20ee97p4u8"); // subnet id

        try {
            GetVpcResourceIpResponse response = vpcClient.getVpcResourceIpInfo(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
