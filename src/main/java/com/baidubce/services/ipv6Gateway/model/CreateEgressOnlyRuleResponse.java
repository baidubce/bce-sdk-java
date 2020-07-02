/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.model.AbstractBceResponse;

public class CreateEgressOnlyRuleResponse extends AbstractBceResponse {
    private String egressOnlyRuleId;

    public String getEgressOnlyRuleId() {
        return egressOnlyRuleId;
    }

    public void setEgressOnlyRuleId(String egressOnlyRuleId) {
        this.egressOnlyRuleId = egressOnlyRuleId;
    }
}
