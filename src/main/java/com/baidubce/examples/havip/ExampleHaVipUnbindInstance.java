package com.baidubce.examples.havip;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.havip.HaVipClient;
import com.baidubce.services.havip.HaVipClientConfiguration;
import com.baidubce.services.havip.model.UnBindInstanceRequest;

import java.util.Arrays;

public class ExampleHaVipUnbindInstance {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        HaVipClientConfiguration config = new HaVipClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        HaVipClient haVipClient = new HaVipClient(config); // 初始化Client

        UnBindInstanceRequest unBindInstanceRequest = new UnBindInstanceRequest();
        unBindInstanceRequest.setHaVipId("havip-w2d4kgc3x0y1"); // 高可用虚拟IP的ID
        unBindInstanceRequest.setInstanceType("ENI"); // 解绑的实例类型，"SERVER"表示云服务器（BCC/BBC/DCC），"ENI"表示弹性网卡
        unBindInstanceRequest.setInstanceIds(Arrays.asList("eni-w2d4kgc3x0y1")); // 解绑的实例ID列表，列表长度不大于5


        try {
            haVipClient.unBindInstance(unBindInstanceRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
