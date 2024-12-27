package com.baidubce.examples.ipcollection.ipset;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipset.UpdateIpSetRequest;

import java.util.UUID;

public class ExampleUpdateIpSet {

    /**
     * ExampleUpdateIpSet main method
     * @param args
     */
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名
        IpCollectionClientConfiguration config = new IpCollectionClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        IpCollectionClient ipCollectionClient = new IpCollectionClient(config);

        UpdateIpSetRequest updateIpSetRequest = new UpdateIpSetRequest();
        updateIpSetRequest.setClientToken(UUID.randomUUID().toString());
        updateIpSetRequest.setDescription("description update");
        updateIpSetRequest.setIpSetId("ips-zmbp03b6wz8a");
        updateIpSetRequest.setName("test update ipSet");
        try {
            ipCollectionClient.updateIpSet(updateIpSetRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
