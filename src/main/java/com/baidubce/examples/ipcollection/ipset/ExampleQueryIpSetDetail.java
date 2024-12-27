package com.baidubce.examples.ipcollection.ipset;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetDetailRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetDetailResponse;

public class ExampleQueryIpSetDetail {
    /**
     * ExampleQueryIpSetDetail main method
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

        QueryIpSetDetailRequest request = new QueryIpSetDetailRequest();
        request.setIpSetId("ips-xwnu2mdjcxfy");
        try {
            QueryIpSetDetailResponse queryIpSetDetailResponse = ipCollectionClient.queryIpSetDetail(request);
            System.out.println(queryIpSetDetailResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
