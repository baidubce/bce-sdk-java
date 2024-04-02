package com.baidubce.services.as.model.rule;

/**
 * Created by dongjiawei on 2023/12/13.
 */
public enum RuleType {
    /**
     * 定时伸缩
     * */
    CRONTAB,
    /**
     * 告警伸缩
     * */
    ALARM,
    /**
     * 周期伸缩
     * */
    PERIOD
}
