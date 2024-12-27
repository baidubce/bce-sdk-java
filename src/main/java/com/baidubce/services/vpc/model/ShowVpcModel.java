package com.baidubce.services.vpc.model;

import com.baidubce.services.subnet.model.Subnet;

import java.util.List;

/**
 * vpc detail info model
 */
public class ShowVpcModel {

    private String vpcId;

    /**
     * The id of this vpc.
     */
    private String name;

    /**
     * The cidr of this vpc.
     */
    private String cidr;

    /***
     * The IPv6 cidr of this vpc
     */
    private String ipv6Cidr;

    /**
     * The description of this vpc.
     */
    private String description;

    /**
     * This shows whether this vpc is a default vpc.
     * Is default vpc if the value is true
     * Is user-defined vpc if the value is false
     */
    private Boolean isDefault;

    /**
     * The relay switch status of this vpc.
     */
    private Boolean relay;

    /**
     * The subnet info which belong to this vpc.
     */
    private List<Subnet> subnets;

    /**
     * The secondary cidrs of this vpc.
     */
    private List<String> secondaryCidr;

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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Boolean getRelay() {
        return relay;
    }

    public void setRelay(Boolean relay) {
        this.relay = relay;
    }

    public List<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<Subnet> subnets) {
        this.subnets = subnets;
    }

    public List<String> getSecondaryCidr() {
        return secondaryCidr;
    }

    public void setSecondaryCidr(List<String> secondaryCidr) {
        this.secondaryCidr = secondaryCidr;
    }

    public String getIpv6Cidr() {
        return ipv6Cidr;
    }

    public void setIpv6Cidr(String ipv6Cidr) {
        this.ipv6Cidr = ipv6Cidr;
    }

    @Override
    public String toString() {
        return "ShowVpcModel{" +
                "vpcId='" + vpcId + '\'' +
                ", name='" + name + '\'' +
                ", cidr='" + cidr + '\'' +
                ", ipv6Cidr='" + ipv6Cidr + '\'' +
                ", description='" + description + '\'' +
                ", isDefault=" + isDefault +
                ", relay=" + relay +
                ", subnets=" + subnets +
                ", secondaryCidr=" + secondaryCidr +
                '}';
    }
}
