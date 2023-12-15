package com.baidubce.examples.probe;

import java.util.Arrays;
import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.probe.ProbeClient;
import com.baidubce.services.probe.model.CreateProbeRequest;
import com.baidubce.services.probe.model.CreateProbeResponse;

public class ExampleCreateProbe {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        ProbeClient probeClient = new ProbeClient(config); // 初始化ProbeClient

        CreateProbeRequest createProbeRequest = new CreateProbeRequest();
        createProbeRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token，是一个长度不超过64位的ASCII字符串
        createProbeRequest.setName("test"); // 网络探测名称，长度不超过65个字符，可由数字、字符、下划线组成
        createProbeRequest.setDescription("desc"); // 网络探测描述，不超过200字符
        createProbeRequest.setVpcId("vpc-sf52s4f7s1d"); // 网络探测所属VPC ID
        createProbeRequest.setSubnetId("sbn-dtzcawikg1g2"); // 网络探测所属子网ID
        createProbeRequest.setProtocol("UDP"); // 探测类型。目前支持ICMP、TCP、UDP、DNS
        createProbeRequest.setFrequency(10); // 探测频率取值为10、20、30
        createProbeRequest.setSourceIps(Arrays.asList("192.168.0.1")); // 探测源IP列表
        createProbeRequest.setSourceIpNum(2); // 探测源IP个数，当个数大于IP列表长度时，多出的IP会自动分配
        createProbeRequest.setDestIp("10.0.5.8"); // 探测目的IP
        createProbeRequest.setDestPort(80); // 探测目的端口，TCP、UDP和DNS类型的需要
        createProbeRequest.setPayload("udp_probe"); // UDP类型的探测字符串和DNS类型的探测域名

        try {
            CreateProbeResponse probe = probeClient.createProbe(createProbeRequest);
            System.out.println(probe);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
