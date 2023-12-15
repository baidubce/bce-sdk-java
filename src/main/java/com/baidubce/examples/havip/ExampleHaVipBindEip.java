package com.baidubce.examples.havip;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.havip.HaVipClient;
import com.baidubce.services.havip.HaVipClientConfiguration;
import com.baidubce.services.havip.model.BindEipRequest;

public class ExampleHaVipBindEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        HaVipClientConfiguration config = new HaVipClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        HaVipClient haVipClient = new HaVipClient(config); // 初始化Client

        BindEipRequest bindEipRequest = new BindEipRequest();
        bindEipRequest.setHaVipId("havip-w2d4kgc3x0y1"); // 高可用虚拟IP的ID
        bindEipRequest.setPublicIpAddress("180.76.245.166"); // 公网IP


        try {
            haVipClient.bindEip(bindEipRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
