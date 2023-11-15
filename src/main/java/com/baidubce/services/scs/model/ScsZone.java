package com.baidubce.services.scs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Scs zone list
 */
public class ScsZone {

    private List<String> zoneNames = new ArrayList<String>();

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }
}
