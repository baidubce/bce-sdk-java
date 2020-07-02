/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;


public class CreateEgressOnlyRuleRequest extends AbstractBceRequest {

    private String cidr;

    private String gatewayId;

    private String clientToken;

    @Override
    public CreateEgressOnlyRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public CreateEgressOnlyRuleRequest withRequestCidr(String cidr) {
        this.cidr = cidr;
        return this;
    }

    public CreateEgressOnlyRuleRequest withRequestGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
        return this;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}
