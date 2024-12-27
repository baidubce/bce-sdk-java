package com.baidubce.examples.ipcollection.ipset;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipset.RemoveIpAddressFromIpSetRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExampleRemoveIpAddressFromIpSet {

    /**
     * ExampleRemoveIpAddressFromIpSet main method
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

        RemoveIpAddressFromIpSetRequest request = new RemoveIpAddressFromIpSetRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setIpSetId("ips-zmbp03b6wz8a");
        List<String> ipAddressInfo = new ArrayList<>();
        ipAddressInfo.add("192.168.34.0");
        ipAddressInfo.add("192.168.35.0");
        request.setIpAddressInfo(ipAddressInfo);
        try {
            ipCollectionClient.removeIpAddressFromIpSet(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
