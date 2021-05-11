package com.baidubce.services.subnet.model;


/**
 * subnet detail info model
 */
public class SubnetDetail extends Subnet {

    /**
     * Number of IPs available in the subnet
     */
    private Integer availableIp;

    public Integer getAvailableIp() {
        return availableIp;
    }

    public void setAvailableIp(Integer availableIp) {
        this.availableIp = availableIp;
    }
}
