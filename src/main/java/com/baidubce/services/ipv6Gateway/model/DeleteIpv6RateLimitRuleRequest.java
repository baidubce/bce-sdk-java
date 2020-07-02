/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class DeleteIpv6RateLimitRuleRequest extends AbstractBceRequest {
    private String gatewayId;

    private String rateLimitRuleId;

    private String clientToken;

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getRateLimitRuleId() {
        return rateLimitRuleId;
    }

    public void setRateLimitRuleId(String rateLimitRuleId) {
        this.rateLimitRuleId = rateLimitRuleId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public DeleteIpv6RateLimitRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public DeleteIpv6RateLimitRuleRequest withRequestGatewayId(String gatewayId) {
        this.setGatewayId(gatewayId);
        return this;
    }

    public DeleteIpv6RateLimitRuleRequest withRequestRateLimitRuleId(String rateLimitRuleId) {
        this.setRateLimitRuleId(rateLimitRuleId);
        return this;
    }

    public DeleteIpv6RateLimitRuleRequest() {
    }
}
