package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of scs zone list
 */
public class ScsZoneResponse extends AbstractBceResponse {

    private List<ScsZone> zones = new ArrayList<ScsZone>();

    public List<ScsZone> getZones() {
        return zones;
    }

    public void setZones(List<ScsZone> zones) {
        this.zones = zones;
    }
}
