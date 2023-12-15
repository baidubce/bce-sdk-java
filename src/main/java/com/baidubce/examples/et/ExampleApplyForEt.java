package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.ApplyForEtRequest;
import com.baidubce.services.et.model.ApplyForEtResponse;

public class ExampleApplyForEt {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        ApplyForEtRequest request = new ApplyForEtRequest();
        request.setName("NewJavaSdkTestET"); // ET名称
        request.setDescription("New Java sdk test ET"); // ET描述
        request.setIsp("ISP_CMCC"); // 运营商
        request.setIntfType("1G"); // 物理端口规格
        request.setApType("BAIDU"); // 线路类型,百度内部用户：BAIDU，外部用户：SINGLE
        request.setApAddr("BB"); // 接入点
        request.setUserIdc("北京|市辖区|东城区|东单"); // 对端地址
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            ApplyForEtResponse response = etClient.applyForEt(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
