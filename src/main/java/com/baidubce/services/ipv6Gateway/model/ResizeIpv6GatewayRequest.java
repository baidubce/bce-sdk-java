/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ResizeIpv6GatewayRequest extends AbstractBceRequest {
    private int bandwidthInMbps;

    private String ipv6GatewayId;

    private String clientToken;

    @Override
    public ResizeIpv6GatewayRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public ResizeIpv6GatewayRequest withIpv6Gateway(String ipv6GatewayId) {
        this.ipv6GatewayId = ipv6GatewayId;
        return this;
    }

    public ResizeIpv6GatewayRequest withBandwidth(int bandwidth) {
        this.bandwidthInMbps = bandwidth;
        return this;
    }

    public String getIpv6GatewayId() {
        return ipv6GatewayId;
    }

    public void setIpv6GatewayId(String ipv6GatewayId) {
        this.ipv6GatewayId = ipv6GatewayId;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}
