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

        // 构建策略规则
        AppRule appRule = new AppRule();
        appRule.setKey("*"); // 策略规则类型
        appRule.setValue("*"); // 策略规则值
        List<AppRule> ruleList = new ArrayList<AppRule>();
        ruleList.add(appRule);

        // 构建策略
        AppPolicy appPolicy = new AppPolicy();
        appPolicy.setPriority(7); // 策略优先级
        appPolicy.setAppServerGroupId("sg-1fd8a838"); // 服务器组ID
        appPolicy.setBackendPort(90); // 后端端口
        appPolicy.setDesc("my policy"); // 策略描述
        appPolicy.setRuleList(ruleList);

        List<AppPolicy> appPolicyVos = new ArrayList<AppPolicy>();
        appPolicyVos.add(appPolicy);

        try {
            CreateAppPolicyResponse response =
                    appBlbClient.createPolicys("lb-2986ed03", 80, "TCP", appPolicyVos);
            System.out.println("创建策略成功，策略ID列表: " + response.getIdList());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
