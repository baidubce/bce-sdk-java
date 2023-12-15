package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.ListPeerConnRequest;
import com.baidubce.services.peerconn.model.ListPeerConnResponse;

public class ExampleListPeerConn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        ListPeerConnRequest listPeerConnRequest  = new ListPeerConnRequest();
        listPeerConnRequest.setVpcId("vpc-0pyknh7t2cgn"); // vpc的ID
        listPeerConnRequest.setMarker("");  // 批量获取列表的查询的起始位置，是一个由系统生成的字符串
        listPeerConnRequest.setMaxKeys(10); // 每页包含的最大数量，最大数量不超过1000。缺省值为1000

        try {
            ListPeerConnResponse listPeerConnResponse = peerConnClient.listPeerConn(listPeerConnRequest);
            System.out.println("listPeerConnResponse = " + listPeerConnResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
