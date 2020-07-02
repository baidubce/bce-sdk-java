/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by wangkai on 2019/8/19
 */
public class RateLimitRuleResponse extends AbstractBceResponse {
    /*
     * the id of rateLimit rule
     */
    private String rateLimitRuleId;

    public String getRateLimitRuleId() {
        return rateLimitRuleId;
    }

    public void setRateLimitRuleId(String rateLimitRuleId) {
        this.rateLimitRuleId = rateLimitRuleId;
    }
}
