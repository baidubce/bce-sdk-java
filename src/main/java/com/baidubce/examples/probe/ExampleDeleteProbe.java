package com.baidubce.examples.probe;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.probe.ProbeClient;

public class ExampleDeleteProbe {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        ProbeClient probeClient = new ProbeClient(config); // 初始化ProbeClient

        try {
            probeClient.deleteProbe("probe-25sd634a4xasd"); // 网络探测的ID
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
