package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsDccHostId {
    private String hostId;
    private String azone;

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getAzone() {
        return azone;
    }

    public void setAzone(String azone) {
        this.azone = azone;
    }
}
