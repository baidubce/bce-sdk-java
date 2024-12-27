package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Rds zone
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsZone {

    private List<String> zoneNames = new ArrayList<String>();

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }
}
