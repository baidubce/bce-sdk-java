package com.baidubce.services.bmr.model;

public class SubnetInfo {
    private String name = "";
    private String subnetId = "";
    private String az = "";
    private String cidr = "";
    private String vpcId = "";
    private String accountId = "";
    private String subnetUuid = "";
    private String description = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
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

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSubnetUuid() {
        return subnetUuid;
    }

    public void setSubnetUuid(String subnetUuid) {
        this.subnetUuid = subnetUuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
