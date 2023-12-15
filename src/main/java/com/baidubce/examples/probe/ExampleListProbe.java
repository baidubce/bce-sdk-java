package com.baidubce.examples.probe;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.probe.ProbeClient;
import com.baidubce.services.probe.model.ListProbeRequest;
import com.baidubce.services.probe.model.ListProbeResponse;

public class ExampleListProbe {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        ProbeClient probeClient = new ProbeClient(config); // 初始化ProbeClient

        ListProbeRequest listProbeRequest = new ListProbeRequest();
        listProbeRequest.setMaxKeys(10); // 每页包含的最大数量。最大数量通常不超过1000，缺省值为1000
        listProbeRequest.setMarker("marker"); // 批量获取列表的查询的起始位置，是一个由系统生成的字符串

        try {
            ListProbeResponse listProbeResponse = probeClient.listProbes(listProbeRequest);
            System.out.println(listProbeResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
