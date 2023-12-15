/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UpdateRateLimitRuleRequest extends AbstractBceRequest {

    private String gatewayId;
    private String rateLimitRuleId;
    private int ingressBandwidthInMbps = -1;
    private int egressBandwidthInMbps = -1;
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

    public int getIngressBandwidthInMbps() {
        return ingressBandwidthInMbps;
    }

    public void setIngressBandwidthInMbps(int ingressBandwidthInMbps) {
        this.ingressBandwidthInMbps = ingressBandwidthInMbps;
    }

    public int getEgressBandwidthInMbps() {
        return egressBandwidthInMbps;
    }

    public void setEgressBandwidthInMbps(int egressBandwidthInMbps) {
        this.egressBandwidthInMbps = egressBandwidthInMbps;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public UpdateRateLimitRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
