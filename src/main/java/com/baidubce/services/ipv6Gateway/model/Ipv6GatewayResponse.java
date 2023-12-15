/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import java.util.List;

import com.baidubce.model.ListResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Ipv6GatewayResponse extends ListResponse {
    private String name;
    private String gatewayId;
    private int bandwidthInMbps;
    private String vpcId;

    private List<EgressOnlyRule> egressOnlyRules;
    private List<RateLimitRule> rateLimitRules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public List<EgressOnlyRule> getEgressOnlyRules() {
        return egressOnlyRules;
    }

    public void setEgressOnlyRules(
            List<EgressOnlyRule> egressOnlyRules) {
        this.egressOnlyRules = egressOnlyRules;
    }

    public List<RateLimitRule> getRateLimitRules() {
        return rateLimitRules;
    }

    public void setRateLimitRules(
            List<RateLimitRule> rateLimitRules) {
        this.rateLimitRules = rateLimitRules;
    }

}
