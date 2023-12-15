package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
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
        createVpnRequest.setVpcId("vpc-g7cufa91auif"); // 所属VPC的ID
        createVpnRequest.setVpnName(""); // VPN的名称，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid"); // 计费信息
        createVpnRequest.setBilling(billing);

        try {
            CreateVpnResponse vpn = vpnClient.createVpn(createVpnRequest);
            System.out.println(vpn);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
