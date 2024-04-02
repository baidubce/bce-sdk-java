package com.baidubce.services.as.model.rule;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleVOResponse extends AbstractBceResponse {
    private String ruleId;
    private String ruleName;
    private String groupId;
    private String accountId;
    private String state;
    private String type;
    private String targetType;
    private String targetId;
    private String indicator;
    private String threshold;
    private String unit;
    private String comparisonOperator;
    private String cronTime;
    private String actionType;
    private int actionNum;
    private int cooldownInSec;
    private Timestamp createTime;
    private Timestamp lastExecutionTime;
    private Timestamp periodStartTime; // 周期生效开始时间
    private Timestamp periodEndTime; // 周期生效结束时间
    // 周期类型 DAY/WEEK/MONTH
    private String periodType;
    // 周几 几号
    private int periodValue;
}
