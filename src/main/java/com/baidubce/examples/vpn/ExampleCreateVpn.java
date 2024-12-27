package com.baidubce.examples.vpn;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.tag.model.Tag;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.CreateVpnRequest;
import com.baidubce.services.vpn.model.CreateVpnResponse;

public class ExampleCreateVpn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        CreateVpnRequest createVpnRequest = new CreateVpnRequest();
        createVpnRequest.setVpcId("vpc-8gc432kidqqb"); // 所属VPC的ID
        createVpnRequest.setVpnName("VpnJavaSdkTest"); // VPN的名称，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
        createVpnRequest.setSubnetId("sbn-ptb45cw0icpk"); // 所属子网的ID
        createVpnRequest.setType("SSL"); // VPN的类型
        createVpnRequest.setMaxConnection(20); // SSL-VPN最大客户端连接数
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid"); // 计费信息
        createVpnRequest.setBilling(billing);
        createVpnRequest.setDescription("this is a desc"); // 描述信息

        Tag tag = new Tag();
        tag.setTagKey("tagKey");
        tag.setTagValue("tagValue");
        createVpnRequest.setTags(Arrays.asList(tag)); // 标签信息
        createVpnRequest.setResourceGroupId("RESG-xyfmAVnHGzK"); // 资源组ID
        createVpnRequest.setDeleteProtect(true); // 是否开启释放保护

        try {
            CreateVpnResponse vpn = vpnClient.createVpn(createVpnRequest);
            System.out.println(vpn);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
