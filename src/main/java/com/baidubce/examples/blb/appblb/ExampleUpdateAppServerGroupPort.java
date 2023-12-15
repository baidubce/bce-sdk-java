package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.AppSgPortRequest;

/**
 * @author chenchangquan
 * @date 2023/11/28
 */
public class ExampleUpdateAppServerGroupPort {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        AppSgPortRequest request = new AppSgPortRequest();
        request.setBlbId("lb-99fa2577"); // 负载均衡器ID
        request.setSgId("sg-9153c2d9"); // 应用服务器组ID
        request.setPortId("port-2b68dae3"); // 应用服务器组端口ID
        request.setHealthCheck("HTTP"); // 健康检查协议
        request.setHealthCheckTimeoutInSecond(50); // 健康检查超时时间
        try {
            appBlbClient.modifyAppServerGroupPortAttributes(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
