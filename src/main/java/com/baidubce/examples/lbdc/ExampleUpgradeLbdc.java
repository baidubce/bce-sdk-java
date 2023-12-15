package com.baidubce.examples.lbdc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lbdc.LbdcClient;
import com.baidubce.services.lbdc.model.UpgradeLbdcRequest;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleUpgradeLbdc {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LbdcClient lbdcClient = new LbdcClient(config); // 初始化LbdcClient

        UpgradeLbdcRequest upgradeLbdcRequest = new UpgradeLbdcRequest();
        upgradeLbdcRequest.setCcuCount(8); // 升级后CCU数量

        try {
            lbdcClient.upgradeLbdc("bgw_group-1f1b6e17", upgradeLbdcRequest, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
