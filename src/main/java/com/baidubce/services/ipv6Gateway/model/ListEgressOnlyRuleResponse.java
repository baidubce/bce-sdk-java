/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import java.util.List;

import com.baidubce.model.ListResponse;

public class ListEgressOnlyRuleResponse extends ListResponse {
    /**
     * List of ipv6Gateway' EgressOnlyRule info
     */
    private List<EgressOnlyRule> egressOnlyRules;

    public List<EgressOnlyRule> getEgressOnlyRules() {
        return egressOnlyRules;
    }

    public void setEgressOnlyRules(List<EgressOnlyRule> egressOnlyRules) {
        this.egressOnlyRules = egressOnlyRules;
    }

    @Override
    public String toString() {
        return "ListEgressOnlyRuleResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "egressOnlyRules=" + egressOnlyRules +
                '}';
    }
}
