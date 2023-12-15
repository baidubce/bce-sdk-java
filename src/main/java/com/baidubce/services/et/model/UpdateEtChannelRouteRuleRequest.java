package com.baidubce.services.et.model;

import lombok.ToString;

/**
 * The request for updating an ET channel route rule.
 */
@ToString
public class UpdateEtChannelRouteRuleRequest extends EtChannelRouteRuleIdRequest {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
