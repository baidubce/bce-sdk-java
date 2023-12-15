package com.baidubce.services.et.model;

import lombok.ToString;

/**
 * The request for containing ET channel route ID.
 */
@ToString
public class EtChannelRouteRuleIdRequest extends EtChannelIdRequest {
    private String routeRuleId;

    public String getRouteRuleId() {
        return routeRuleId;
    }

    public void setRouteRuleId(String routeRuleId) {
        this.routeRuleId = routeRuleId;
    }
}
