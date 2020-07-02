package com.baidubce.services.bmr.model;

public class VpcInfo {
    private String vpcId = "";
    private String name = "";
    private String cidr = "";
    private String description = "";
    private boolean defaultVpc = false;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefaultVpc() {
        return defaultVpc;
    }

    public void setDefaultVpc(boolean defaultVpc) {
        this.defaultVpc = defaultVpc;
    }
}
