package com.baidubce.examples.ipcollection.ipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipgroup.UpdateIpGroupRequest;

public class ExampleUpdateIpGroup {
    /**
     * ExampleUpdateIpGroup main method
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

        UpdateIpGroupRequest request = new UpdateIpGroupRequest();
        request.setIpGroupId("ipg-p40bmsaw3aad");
        request.setName("ipGroupByCode_Update");
        request.setDescription("test description update");
        try {
            ipCollectionClient.updateIpGroup(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
