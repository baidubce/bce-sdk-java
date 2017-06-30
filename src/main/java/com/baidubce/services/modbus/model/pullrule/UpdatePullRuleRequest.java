package com.baidubce.services.modbus.model.pullrule;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePullRuleRequest extends GenericAccountRequest {
    public Integer getPullInterval() {
        return pullInterval;
    }

    public void setPullInterval(Integer pullInterval) {
        this.pullInterval = pullInterval;
    }

    private Integer pullInterval; // 请求时间间隔
}
