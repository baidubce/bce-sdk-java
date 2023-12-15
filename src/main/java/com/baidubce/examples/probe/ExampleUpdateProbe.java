package com.baidubce.examples.probe;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.probe.ProbeClient;
import com.baidubce.services.probe.model.GetProbeResponse;
import com.baidubce.services.probe.model.UpdateProbeRequest;

public class ExampleUpdateProbe {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        ProbeClient probeClient = new ProbeClient(config); // 初始化ProbeClient

        UpdateProbeRequest updateProbeRequest = new UpdateProbeRequest();
        updateProbeRequest.setProbeId("probe-25sd634a4xasd"); // 需要更新的网络探测的ID
        updateProbeRequest.setName("name-update"); // 网络探测的name
        probeClient.updateProbe(updateProbeRequest);

        try {
            GetProbeResponse probe = probeClient.getProbe(updateProbeRequest.getProbeId());
            System.out.println(probe);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
