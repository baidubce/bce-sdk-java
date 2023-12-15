package com.baidubce.examples.havip;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.havip.HaVipClient;
import com.baidubce.services.havip.HaVipClientConfiguration;
import com.baidubce.services.havip.model.ListHaVipResponse;

public class ExampleListHaVip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        HaVipClientConfiguration config = new HaVipClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        HaVipClient haVipClient = new HaVipClient(config); // 初始化Client

        String vpcId = "vpc-r625rqw3wuer"; // 高可用虚拟IP所属的VPC ID

        try {
            ListHaVipResponse listHaVipResponse = haVipClient.listHaVip(vpcId);
            System.out.println(listHaVipResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
