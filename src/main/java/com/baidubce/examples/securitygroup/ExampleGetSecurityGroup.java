package com.baidubce.examples.securitygroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.SecurityGroupModel;

public class ExampleGetSecurityGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BccClientConfiguration config = new BccClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BccClient bccClient = new BccClient(config); // 初始化SecurityGroupClient

        try {
            SecurityGroupModel response = bccClient.getSecurityGroup("g-zhc4yv5aw2i9");
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
