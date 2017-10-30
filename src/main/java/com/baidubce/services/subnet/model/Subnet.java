package com.baidubce.services.subnet.model;

/**
 * subnet detail info model
 */
public class Subnet {

    /**
     * The name of subnet.
     */
    private String name;

    /**
     * The id of subnet.
     */
    private String subnetId;

    /**
     * the name of available zone which the subnet belong
     * through listZones, we can get all available zone info at current region
     * ee.g. "cn-gz-a"  "cn-gz-b"
     */
    private String zoneName;

    /**
     * The CIDR of this subnet.
     */
    private String cidr;

    /**
     * The id of vpc which this subnet belong.
     */
    private String vpcId;

    /**
     * The type of subnet
     * See more detail on
     * <a href = "https://cloud.baidu.com/doc/VPC/API.html#.E5.88.9B.E5.BB.BA.E5.AD.90.E7.BD.91">
     * Subnet API doc</a>
     */
    private String subnetType;

    /**
     * The description of subnet.
     */
    private String description;

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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
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

    public String getSubnetType() {
        return subnetType;
    }

    public void setSubnetType(String subnetType) {
        this.subnetType = subnetType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subnet{"
                + "subnetId='" + subnetId + '\''
                + ", name='" + name + '\''
                + ", zoneName='" + zoneName + '\''
                + ", cidr='" + cidr + '\''
                + ", vpcId='" + vpcId + '\''
                + ", subnetType='" + subnetType + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
