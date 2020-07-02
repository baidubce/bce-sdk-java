/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EgressOnlyRule {
    private String egressOnlyRuleId;
    private String cidr;

    public String getEgressOnlyRuleId() {
        return egressOnlyRuleId;
    }

    public void setEgressOnlyRuleId(String egressOnlyRuleId) {
        this.egressOnlyRuleId = egressOnlyRuleId;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }
}
