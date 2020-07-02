/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateRateLimitRuleRequest extends AbstractBceRequest {

    private String gatewayId;
    private String ipv6Address;
    private int ingressBandwidthInMbps;
    private int egressBandwidthInMbps;
    private String clientToken;

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
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
    public CreateRateLimitRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
