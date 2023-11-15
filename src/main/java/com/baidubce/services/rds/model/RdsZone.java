package com.baidubce.services.rds.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rds zone
 */
public class RdsZone {

    private List<String> zoneNames = new ArrayList<String>();

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }
}
