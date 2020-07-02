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
    private String egressOnlyRuleId;
    private int ingressBandwidthInMbps = -1;
    private int egressBandwidthInMbps = -1;
    private String clientToken;

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getEgressOnlyRuleId() {
        return egressOnlyRuleId;
    }

    public void setEgressOnlyRuleId(String egressOnlyRuleId) {
        this.egressOnlyRuleId = egressOnlyRuleId;
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
