package com.baidubce.services.subnet.model;

import lombok.Getter;
import lombok.Setter;

/**
 * IpReserve detail info model
 */
@Getter
@Setter
public class IpReserve {

    private String ipReserveId;
    private String subnetId;
    private String ipCidr;
    private String ipVersion;
    private String description;
    private String createdTime;
    private String updatedTime;

    @Override
    public String toString() {
        return "IpReserve{" +
                "ipReserveId='" + ipReserveId + '\'' +
                ", subnetId='" + subnetId + '\'' +
                ", ipCidr='" + ipCidr + '\'' +
                ", ipVersion='" + ipVersion + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                '}';
    }
}

