package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.AppPolicy;
import com.baidubce.services.blb.model.AppRule;
import com.baidubce.services.blb.model.CreateAppPolicyResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenchangquan
 * @date 2023/11/28
 */
public class ExampleCreatePolicys {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        List<AppPolicy> appPolicyVos = new ArrayList<AppPolicy>(); // 创建策略列表
        AppPolicy appPolicy = new AppPolicy();
        appPolicyVos.add(appPolicy);
        appPolicy.setPriority(7); // 策略优先级
        List<AppRule> ruleList = new ArrayList<AppRule>(); // 策略规则列表
        AppRule appRule = new AppRule();
        appRule.setKey("*"); // 策略规则类型
        appRule.setValue("*"); // 策略规则值
        ruleList.add(appRule);
        appPolicy.setRuleList(ruleList);
        appPolicy.setAppServerGroupId("sg-1fd8a838"); // 服务器组ID
        appPolicy.setBackendPort(90); // 后端端口
        try {
            CreateAppPolicyResponse response =
                    appBlbClient.createPolicys("lb-2986ed03", 80, null, appPolicyVos);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
