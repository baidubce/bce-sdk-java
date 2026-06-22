package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class GaiadbInterfaceListResponse extends GenericGaiadbResponse {
    private List<Map<String, Object>> interfaces;

    public List<Map<String, Object>> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Map<String, Object>> interfaces) {
        this.interfaces = interfaces;
    }
}
