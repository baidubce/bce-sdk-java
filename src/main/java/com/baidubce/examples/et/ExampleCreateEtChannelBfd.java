package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.CreateEtChannelBfdRequest;

public class ExampleCreateEtChannelBfd {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com";

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config);

        CreateEtChannelBfdRequest request = new CreateEtChannelBfdRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-gq65bz9ip712");
        request.setEtChannelId("dedicatedconn-zy9t7n91k0iq");
        request.setSendInterval(300);
        request.setReceivInterval(300);
        request.setDetectMultiplier(4);

        try {
            etClient.createEtChannelBfd(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
