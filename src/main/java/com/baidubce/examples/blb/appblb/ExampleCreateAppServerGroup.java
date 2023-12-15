package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.AppBackendServer;
import com.baidubce.services.blb.model.AppSgResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenchangquan
 * @date 2023/11/28
 */
public class ExampleCreateAppServerGroup {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        List<AppBackendServer> backendServerList = new ArrayList<AppBackendServer>();
        AppBackendServer appBackendServer = new AppBackendServer();
        appBackendServer.setInstanceId("i-mWyp1Bjd"); // 后端服务器实例ID
        appBackendServer.setWeight(100); // 后端服务器权重
        backendServerList.add(appBackendServer);
        try {
            AppSgResponse response =
                    appBlbClient.createAppServerGroup("lb-99fa2577", "appSg", "", backendServerList);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
