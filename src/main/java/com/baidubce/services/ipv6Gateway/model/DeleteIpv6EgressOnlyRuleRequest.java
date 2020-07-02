/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class DeleteIpv6EgressOnlyRuleRequest extends AbstractBceRequest {
    private String gatewayId;

    private String egressOnlyRuleId;

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

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public DeleteIpv6EgressOnlyRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public DeleteIpv6EgressOnlyRuleRequest withRequestGatewayId(String gatewayId) {
        this.setGatewayId(gatewayId);
        return this;
    }

    public DeleteIpv6EgressOnlyRuleRequest wirhRequestEgressOnlyRuleId(String egressOnlyRuleId) {
        this.egressOnlyRuleId = egressOnlyRuleId;
        return this;
    }

}
