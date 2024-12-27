package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubnetVo {
    private String subnetId;
    private String name;
    private String az;
    private String cidr;
    private String ipv6Cidr;
    private String vpcId;
    private String vpcShortId;

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAz() {
        return az;
    }

    public void setAz(String az) {
        this.az = az;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getIpv6Cidr() {
        return ipv6Cidr;
    }

    public void setIpv6Cidr(String ipv6Cidr) {
        this.ipv6Cidr = ipv6Cidr;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getVpcShortId() {
        return vpcShortId;
    }

    public void setVpcShortId(String vpcShortId) {
        this.vpcShortId = vpcShortId;
    }
}
