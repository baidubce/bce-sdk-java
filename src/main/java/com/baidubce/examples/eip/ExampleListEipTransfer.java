package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.ListEipTransferRequest;
import com.baidubce.services.eip.model.ListEipTransferResponse;

public class ExampleListEipTransfer {
    public static void main(String[] args) {
        // 设置Client的Access Key ID和Secret Access Key，获取AKSK详见:https://cloud.baidu.com/doc/Reference/s/9jwvz2egb
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "Endpoint";
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient client = new EipClient(config);
        ListEipTransferRequest listEipTransferRequest = new ListEipTransferRequest();
        listEipTransferRequest.setMaxKeys(10);
        listEipTransferRequest.setMarker("tf-1l4m5etb");
        listEipTransferRequest.setDirection("sent");
        listEipTransferRequest.setTransferId("tf-1l4m5etb");
        listEipTransferRequest.setStatus("success");
        listEipTransferRequest.setFuzzyTransferId("tf-1l4m5etb");
        listEipTransferRequest.setFuzzyInstanceId("ip-3pblsyay");
        listEipTransferRequest.setFuzzyInstanceName("EIP1768387961009");
        listEipTransferRequest.setFuzzyInstanceIp("100.88.8.139");
        try {
            ListEipTransferResponse response = client.listEipTransfer(listEipTransferRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
