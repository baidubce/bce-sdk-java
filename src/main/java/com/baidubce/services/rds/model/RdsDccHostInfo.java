package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsDccHostInfo {
    private  RdsDccHostId    master;

    public RdsDccHostId getMaster() {
        return master;
    }

    public void setMaster(RdsDccHostId master) {
        this.master = master;
    }

    public RdsDccHostId getBackup() {
        return backup;
    }

    public void setBackup(RdsDccHostId backup) {
        this.backup = backup;
    }

    private   RdsDccHostId     backup;
}
