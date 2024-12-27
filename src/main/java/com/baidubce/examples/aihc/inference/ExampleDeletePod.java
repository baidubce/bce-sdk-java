package com.baidubce.examples.aihc.inference;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcInferenceClient;
import com.baidubce.services.aihc.model.inference.DeletePodRequest;
import com.baidubce.services.aihc.model.inference.DeletePodResponse;

public class ExampleDeletePod {
    public static void main(String[] args) {
        String ak = "Your AK";
        String sk = "Your SK";
        String endpoint = "aihc.baidubce.com";
        String region = "bj";

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        AihcInferenceClient client = new AihcInferenceClient(config);

        DeletePodRequest request = new DeletePodRequest();
        request.setAppId("test");
        request.setInsID("test");

        try {
            DeletePodResponse response = client.deletePod(request, region);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
