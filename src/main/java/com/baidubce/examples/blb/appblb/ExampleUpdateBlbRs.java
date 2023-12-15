package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.AppBackendServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenchangquan
 * @date 2023/11/28
 */
public class ExampleUpdateBlbRs {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        List<AppBackendServer> backendServerList = new ArrayList<AppBackendServer>(); // 后端服务器列表
        AppBackendServer appBackendServer = new AppBackendServer();
        appBackendServer.setInstanceId("i-mWyp1Bjd"); // 后端服务器实例ID
        appBackendServer.setWeight(50); // 权重
        backendServerList.add(appBackendServer);
        try {
            appBlbClient.modifyBlbRs("lb-99fa2577", "sg-0bf84edc", backendServerList);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
