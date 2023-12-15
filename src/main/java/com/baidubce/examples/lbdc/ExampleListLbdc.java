package com.baidubce.examples.lbdc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lbdc.LbdcClient;
import com.baidubce.services.lbdc.model.ListLbdcResponse;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleListLbdc {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LbdcClient lbdcClient = new LbdcClient(config); // 初始化LbdcClient

        try {
            ListLbdcResponse response = lbdcClient.listLbdc(null, null);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
