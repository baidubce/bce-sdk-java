package com.baidubce.services.as.model.rule;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleRequest extends AbstractBceRequest {
    private String ruleId;
    private String ruleName;
    private String groupId;
    private RuleState state;
    private RuleType type;
    private String targetType = "";
    private String targetId = "";
    private String indicator = "";
    private String threshold = "0";
    private String unit = "";
    private String comparisonOperator = "";
    private ActionType actionType;
    private Integer actionNum;
    private String cronTime = ""; // CRON表达式类型，传标准cron格式
    private Integer cooldownInSec;
    private String periodType; // 周期单位 可选 DAY/WEEK/MONTH/CronExpression
    private Integer periodValue;   // 周期触发日期 1-7标识周1-周日 1-31表示1号到31号 和periodType有关 如果是DAY不用填
    private String periodStartTime; // 周期有效期开始时间
    private String periodEndTime; // 周期有效期结束时间

    @Override
    public RuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
