package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.ConvertToPrepayEipRequest;

public class ExampleConvertToPrepayEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient eipClient = new EipClient(config); // 初始化EipClient

        ConvertToPrepayEipRequest request = new ConvertToPrepayEipRequest();
        request.setEip("100.88.14.238");
        request.setBandWidth(30);
        request.setPurchaseLength(1);
        try {
            eipClient.convertToPrepayEip(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
