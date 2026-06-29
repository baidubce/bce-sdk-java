package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Subnet mapping for MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbSubnetMap {
    private String zoneName;

    private String subnetId;

    private String physicalZone;

    public MongodbSubnetMap() {
    }

    public MongodbSubnetMap(String zoneName) {
        this.zoneName = zoneName;
    }

    public MongodbSubnetMap(String zoneName, String subnetId) {
        this.zoneName = zoneName;
        this.subnetId = subnetId;
    }

    public MongodbSubnetMap(String zoneName, String subnetId, String physicalZone) {
        this.zoneName = zoneName;
        this.subnetId = subnetId;
        this.physicalZone = physicalZone;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getPhysicalZone() {
        return physicalZone;
    }

    public void setPhysicalZone(String physicalZone) {
        this.physicalZone = physicalZone;
    }
}
