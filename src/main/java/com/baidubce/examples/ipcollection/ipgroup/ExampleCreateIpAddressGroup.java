package com.baidubce.examples.ipcollection.ipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipgroup.CreateIpAddressGroupRequest;

import java.util.ArrayList;
import java.util.List;

public class ExampleCreateIpAddressGroup {
    /**
     * ExampleCreateIpAddressGroup main method
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

        CreateIpAddressGroupRequest request = new CreateIpAddressGroupRequest();
        request.setName("ipGroupByCode");
        request.setIpVersion("IPv4");
        request.setDescription("test description");
        List<String> ipSetList = new ArrayList<>();
        ipSetList.add("ips-xwnu2mdjcxfy");
        request.setIpSetIds(ipSetList);
        try {
            ipCollectionClient.createIpAddressGroup(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
