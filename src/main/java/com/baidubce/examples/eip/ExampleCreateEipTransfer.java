package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.CreateEipTransferRequest;
import com.baidubce.services.eip.model.CreateEipTransferResponse;

import java.util.Collections;

public class ExampleCreateEipTransfer {
    public static void main(String[] args) {
        // 设置Client的Access Key ID和Secret Access Key，获取AKSK详见:https://cloud.baidu.com/doc/Reference/s/9jwvz2egb
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "Endpoint";
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient client = new EipClient(config);
        CreateEipTransferRequest createEipTransferRequest = new CreateEipTransferRequest();
        createEipTransferRequest.setClientToken("");
        createEipTransferRequest.setTransferType("eip");
        createEipTransferRequest.setTransferResourceList(Collections.singletonList("ip-2q5dhosc"));
        createEipTransferRequest.setToUserId("xxxxxx");
        try {
            CreateEipTransferResponse response = client.createEipTransfer(createEipTransferRequest);
            System.out.println(response.toString());
        } catch (BceClientException e) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            System.out.println(e.getMessage());
        }
    }
}
