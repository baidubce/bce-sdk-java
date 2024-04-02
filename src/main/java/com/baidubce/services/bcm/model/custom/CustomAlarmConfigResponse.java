package com.baidubce.services.bcm.model.custom;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author guanyanyan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomAlarmConfigResponse extends AbstractBceResponse {
    private String comment = ""; // 注释
    private String userId;
    private String alarmName; // userId下唯一
    private String namespace;
    private String level;
    private Boolean actionEnabled;
    private Boolean policyEnabled;
    private Set<String> alarmActions;
    private Set<String> okActions;
    private Set<String> insufficientActions;
    private int insufficientCycle; // 无数据报警判断周期
    private List<CustomAlarmRule> rules;
    private String region = "";
    private String callbackUrl = ""; // 报警回调地址
    private String callbackToken = ""; // 报警回调token
    private String tag = ""; // 用来标识单条规则的老策略
    private int repeatAlarmCycle; // 重复提醒周期
    private int maxRepeatCount; // 重复提醒次数
}
