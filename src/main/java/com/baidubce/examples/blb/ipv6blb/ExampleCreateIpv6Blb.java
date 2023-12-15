package com.baidubce.examples.blb.ipv6blb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.BlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.CreateBlbResponse;

/**
 * @author chenchangquan
 * @date 2023/11/28
 */
public class ExampleCreateIpv6Blb {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        try {
            CreateBlbResponse response = blbClient.createIpv6Blb("ipv6blb-test",
                    "ipv6blb-desc", "vpc-gg92ixpgmpw7", "sbn-ej7r34w74p69");
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
