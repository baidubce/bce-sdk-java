package com.baidubce.services.vpc.model;

/**
 * vpc detail info model
 */
public class Vpc {

    /**
     * The id of this vpc.
     */
    private String vpcId;

    /**
     * The name of this vpc.
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

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getIpv6Cidr() {
        return ipv6Cidr;
    }

    public void setIpv6Cidr(String ipv6Cidr) {
        this.ipv6Cidr = ipv6Cidr;
    }

    @Override
    public String toString() {
        return "Vpc{"
                + "vpcId='" + vpcId + '\''
                + ", name='" + name + '\''
                + ", cidr='" + cidr + '\''
                + ", description='" + description + '\''
                + ", isDefault='" + isDefault + '\''
                + '}';
    }
}
