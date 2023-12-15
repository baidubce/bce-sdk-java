/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import lombok.ToString;

@ToString
public class RateLimitRule {

    private String rateLimitRuleId;
    private String ipv6Address;
    private int ingressBandwidthInMbps;
    private int egressBandwidthInMbps;

    public String getRateLimitRuleId() {
        return rateLimitRuleId;
    }

    public void setRateLimitRuleId(String rateLimitRuleId) {
        this.rateLimitRuleId = rateLimitRuleId;
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

    public int getEgressBandwidthInMbps() {
        return egressBandwidthInMbps;
    }

    public void setIngressBandwidthInMbps(int ingressBandwidthInMbps) {
        this.ingressBandwidthInMbps = ingressBandwidthInMbps;
    }

    public void setEgressBandwidthInMbps(int egressBandwidthInMbps) {
        this.egressBandwidthInMbps = egressBandwidthInMbps;
    }
}
