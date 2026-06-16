package com.baidubce.services.et.model;

import lombok.ToString;

/**
 * The request for updating an ET channel.
 */
@ToString
public class UpdateEtChannelRequest extends EtChannelIdRequest {
    /**
     * Channel Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    private Integer bgpRouteLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBgpRouteLimit() {
        return bgpRouteLimit;
    }

    public void setBgpRouteLimit(Integer bgpRouteLimit) {
        this.bgpRouteLimit = bgpRouteLimit;
    }
}
