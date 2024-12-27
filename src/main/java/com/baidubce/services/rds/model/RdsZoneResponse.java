package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of rds zone list
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsZoneResponse extends AbstractBceResponse {

    private List<RdsZone> zones = new ArrayList<RdsZone>();

    public List<RdsZone> getZones() {
        return zones;
    }

    public void setZones(List<RdsZone> zones) {
        this.zones = zones;
    }
}
