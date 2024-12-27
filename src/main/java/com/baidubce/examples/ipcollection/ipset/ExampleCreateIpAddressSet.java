package com.baidubce.examples.ipcollection.ipset;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipset.CreateIpAddressSetRequest;
import com.baidubce.services.ipcollection.model.TemplateIpAddressInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExampleCreateIpAddressSet {
    /**
     * ExampleCreateIpAddressSet main method
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

        CreateIpAddressSetRequest createIpAddressGroupRequest = new CreateIpAddressSetRequest();
        createIpAddressGroupRequest.setClientToken(UUID.randomUUID().toString());
        createIpAddressGroupRequest.setName("ipSet_name");
        createIpAddressGroupRequest.setIpVersion("IPv4");
        List<TemplateIpAddressInfo> ipAddressInfo = new ArrayList<>();
        TemplateIpAddressInfo templateIpAddressInfo = new TemplateIpAddressInfo();
        templateIpAddressInfo.setIpAddress("192.168.33.0");
        templateIpAddressInfo.setDescription("ipAddress description");
        ipAddressInfo.add(templateIpAddressInfo);
        createIpAddressGroupRequest.setIpAddressInfo(ipAddressInfo);
        createIpAddressGroupRequest.setDescription("ipSet description");
        try {
            ipCollectionClient.createIpAddressSet(createIpAddressGroupRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
