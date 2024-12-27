package com.baidubce.examples.ipcollection.ipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipgroup.DeleteIpGroupRequest;

public class ExampleDeleteIpGroup {
    /**
     * ExampleDeleteIpGroup main method
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

        DeleteIpGroupRequest request = new DeleteIpGroupRequest();
        request.setIpGroupId("ipg-8mnay8wgvd1i");
        try {
            ipCollectionClient.deleteIpGroup(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
