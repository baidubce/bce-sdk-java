package com.baidubce.examples.havip;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.havip.HaVipClient;
import com.baidubce.services.havip.HaVipClientConfiguration;
import com.baidubce.services.havip.model.HaVipResponse;

public class ExampleGetHaVip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        HaVipClientConfiguration config = new HaVipClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        HaVipClient haVipClient = new HaVipClient(config); // 初始化Client

        String haVipId = "havip-w2d4kgc3x0y1"; // 高可用虚拟IP的ID

        try {
            HaVipResponse haVip = haVipClient.getHaVip(haVipId);
            System.out.println(haVip);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
