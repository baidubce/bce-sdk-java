package com.baidubce.examples.havip;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.havip.HaVipClient;
import com.baidubce.services.havip.HaVipClientConfiguration;
import com.baidubce.services.havip.model.CreateHaVipRequest;
import com.baidubce.services.havip.model.CreateHaVipResponse;

public class ExampleCreateHaVip {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        HaVipClientConfiguration config = new HaVipClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        HaVipClient haVipClient = new HaVipClient(config); // 初始化Client
        CreateHaVipRequest createHaVipRequest = new CreateHaVipRequest();
        createHaVipRequest.setSubnetId(""); // 子网ID
        createHaVipRequest.setDescription("desc"); // 描述
        createHaVipRequest.setName("havip"); // havip名字
        createHaVipRequest.setPrivateIpAddress(""); // 指定havip IP地址

        try {
            CreateHaVipResponse createHaVipResponse = haVipClient.createHaVip(createHaVipRequest);
            System.out.println(createHaVipResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
