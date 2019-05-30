package com.baidubce.services.vpc.model;

import java.util.List;

import com.baidubce.services.subnet.model.Subnet;

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

    /**
     * The description of this vpc.
     */
    private String description;

    /**
     * This shows whether this vpc is a default vpc.
     * Is default vpc if the value is true
     * Is user-defined vpc if the value is false
     */
    private boolean isDefault;

    /**
     * The subnet info which belong to this vpc.
     */
    private List<Subnet> subnets;

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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public List<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<Subnet> subnets) {
        this.subnets = subnets;
    }

    @Override
    public String toString() {
        return "Vpc{"
                + "vpcId='" + vpcId + '\''
                + ", name='" + name + '\''
                + ", cidr='" + cidr + '\''
                + ", description='" + description + '\''
                + ", isDefault='" + isDefault + '\''
                + ", subnets='" + subnets + '\''
                + '}';
    }
}
