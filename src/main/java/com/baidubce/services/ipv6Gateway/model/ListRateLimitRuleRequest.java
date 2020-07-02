/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

public class ListRateLimitRuleRequest extends ListRequest {

    private String gatewayId;

    @Override
    public ListRateLimitRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public ListRateLimitRuleRequest withRequestGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
        return this;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public ListRateLimitRuleRequest() {
    }
}
