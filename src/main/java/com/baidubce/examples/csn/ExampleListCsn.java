package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.ListCsnResponse;

public class ExampleListCsn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String marker = "csn-2jssjbhvyd8v1gxn"; // 批量获取列表的查询的起始位置，是一个由系统生成的字符串
        int maxKeys = 10; // 每页包含的最大数量，最大数量不超过1000，缺省值为1000

        try {
            ListCsnResponse response = csnClient.listCsn(marker, maxKeys);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
