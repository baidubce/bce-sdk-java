package com.baidubce.examples.et;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.DisassociateEtChannelRequest;

public class ExampleDisassociateEtChannel {

    /**
     * 解关联专线通道测试函数
     * @param args
     */
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        DisassociateEtChannelRequest request = new DisassociateEtChannelRequest();
        request.setEtId("dcphy-zzdark9nuk1g");
        request.setEtChannelId("dedicatedconn-hzf4aigzqttd");
        request.setExtraChannelId("dedicatedconn-i05sdfw25kqz");

        try {
            etClient.disassociateEtChannel(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
