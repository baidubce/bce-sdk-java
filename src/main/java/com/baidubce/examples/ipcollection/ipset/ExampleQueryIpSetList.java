package com.baidubce.examples.ipcollection.ipset;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetListRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetListResponse;

public class ExampleQueryIpSetList {

    /**
     * ExampleQueryIpSetList main method
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

        QueryIpSetListRequest request = new QueryIpSetListRequest();
        request.setIpVersion("IPv4");
        request.setMaxKeys(10);
        request.setMarker("ips-xwnu2mdjcxfy");
        try {
            QueryIpSetListResponse response = ipCollectionClient.queryIpSetList(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
