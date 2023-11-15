package com.baidubce.services.subnet.model;

import com.baidubce.services.bcc.model.TagModel;

import java.util.List;

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
     * The IPv6 network segment of the subnet
     */
    private String ipv6Cidr;

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

    /**
     * The creation time of the subnet
     */
    private String createdTime;

    /**
     * List of labels for subnet bindings
     */
    private List<TagModel> tags;

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

    public String getIpv6Cidr() {
        return ipv6Cidr;
    }

    public void setIpv6Cidr(String ipv6Cidr) {
        this.ipv6Cidr = ipv6Cidr;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Subnet{" +
                "name='" + name + '\'' +
                ", subnetId='" + subnetId + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", cidr='" + cidr + '\'' +
                ", ipv6Cidr='" + ipv6Cidr + '\'' +
                ", vpcId='" + vpcId + '\'' +
                ", subnetType='" + subnetType + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", tags=" + tags +
                '}';
    }
}
