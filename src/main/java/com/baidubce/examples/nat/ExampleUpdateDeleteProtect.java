package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.UpdateDeleteProtectRequest;

public class ExampleUpdateDeleteProtect {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        UpdateDeleteProtectRequest request = new UpdateDeleteProtectRequest();
        request.setNatId("nat-b58rnkn1g98h"); // 需要更新释放保护的natId
        request.setDeleteProtect(true); // 是否开启删除保护

        try {
            natClient.updateDeleteProtect(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
