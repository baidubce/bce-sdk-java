package com.baidubce.services.et.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.ToString;

/**
 * The response for creating a newly ET channel route.
 */
@ToString
public class CreateEtChannelRouteResponse extends AbstractBceResponse {
    private String routeRuleId;

    public String getRouteRuleId() {
        return routeRuleId;
    }

    public void setRouteRuleId(String routeRuleId) {
        this.routeRuleId = routeRuleId;
    }
}
