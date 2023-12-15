package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.AllListener;
import com.baidubce.services.blb.model.ListListenerResponse;
import com.baidubce.services.blb.model.TcpListener;

/**
 * @author chenchangquan
 * @date 2023/11/28
 */
public class ExampleDescribeAppTCPListeners {

    public static void main(String[] args) {

        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        try {
            ListListenerResponse<TcpListener> response = appBlbClient.listTcpListener("lb-b69cd42f");
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
