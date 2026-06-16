package com.baidubce.examples.et;

import java.util.Arrays;
import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.RemoveEtChannelRoutesRequest;

public class ExampleRemoveEtChannelRoutes {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com";

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config);

        RemoveEtChannelRoutesRequest request = new RemoveEtChannelRoutesRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-gq65bz9ip712");
        request.setEtChannelId("dedicatedconn-zy9t7n91k0iq");
        request.setRouteType("static-route");
        request.setNetworks(Arrays.asList("192.168.100.0/24"));

        try {
            etClient.removeEtChannelRoutes(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
