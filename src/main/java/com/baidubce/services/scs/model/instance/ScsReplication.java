package com.baidubce.services.scs.model.instance;

/**
 * Scs replication zone and subnet info.
 */
public class ScsReplication {

    private String availabilityZone;
    private String subnetId;
    private Integer isMaster;

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public Integer getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Integer isMaster) {
        this.isMaster = isMaster;
    }
}
