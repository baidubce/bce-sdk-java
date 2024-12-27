package com.baidubce.examples.ipcollection.ipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipgroup.BindIpSetRequest;

import java.util.ArrayList;
import java.util.List;

public class ExampleBindIpSet {

    /**
     * ExampleBindIpSet main method
     *
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

        BindIpSetRequest request = new BindIpSetRequest();
        request.setIpGroupId("ipg-p40bmsaw3aad");
        List<String> ipSetIds = new ArrayList<>();
        ipSetIds.add("ips-zmbp03b6wz8a");
        ipSetIds.add("ips-vqkhe16ex5vb");
        request.setIpSetIds(ipSetIds);
        try {
            ipCollectionClient.bindIpSet(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }

}
