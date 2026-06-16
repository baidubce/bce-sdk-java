package com.baidubce.examples.et;

import java.util.Arrays;
import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.RemoveEtChannelUsersRequest;

public class ExampleRemoveEtChannelUsers {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com";

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config);

        RemoveEtChannelUsersRequest request = new RemoveEtChannelUsersRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-gq65bz9ip712");
        request.setEtChannelId("dedicatedconn-zy9t7n91k0iq");
        request.setAuthorizedUsers(Arrays.asList("8770d0e94e2728ca81b0ec99db9f4df8"));

        try {
            etClient.removeEtChannelUsers(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
