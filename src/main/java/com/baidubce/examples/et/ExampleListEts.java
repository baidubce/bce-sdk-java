package com.baidubce.examples.et;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.ListEtRequest;
import com.baidubce.services.et.model.ListEtResponse;

public class ExampleListEts {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        ListEtRequest request = new ListEtRequest();
        request.setMarker("dcphy-t6ewxjaekkt2"); // 批量获取列表的查询的起始位置，ET ID标记
        request.setMaxKeys(5); // 每页包含的最大数量，最大数量通常不超过1000，缺省值为1000
        request.setStatus("ack-wait");
        try {
            ListEtResponse response = etClient.listEts(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
