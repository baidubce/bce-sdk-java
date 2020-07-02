/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import java.util.List;

import com.baidubce.model.ListResponse;

public class ListRateLimitRuleResponse extends ListResponse {
    /**
     * List of ipv6Gateway' RateLimitRule info
     */
    private List<RateLimitRule> rateLimitRules;

    public List<RateLimitRule> getRateLimitRules() {
        return rateLimitRules;
    }

    public void setRateLimitRules(List<RateLimitRule> rateLimitRules) {
        this.rateLimitRules = rateLimitRules;
    }
}
