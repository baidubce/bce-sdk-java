package com.baidubce.services.rds.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoGroup {
    private String roGroupId;
    private String roGroupName;
    private InstanceDict roGroupEndpoint;
    private String vpcId;
    private String subnetId;
    private String eipStatus;
    private List<AppList> roGroupAppList;
    private SimpleVpcVo vpcVo;
    private SubnetVo subnetVo;

    public String getRoGroupId() {
        return roGroupId;
    }

    public void setRoGroupId(String roGroupId) {
        this.roGroupId = roGroupId;
    }

    public String getRoGroupName() {
        return roGroupName;
    }

    public void setRoGroupName(String roGroupName) {
        this.roGroupName = roGroupName;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public InstanceDict getRoGroupEndpoint() {
        return roGroupEndpoint;
    }

    public void setRoGroupEndpoint(InstanceDict roGroupEndpoint) {
        this.roGroupEndpoint = roGroupEndpoint;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getEipStatus() {
        return eipStatus;
    }

    public void setEipStatus(String eipStatus) {
        this.eipStatus = eipStatus;
    }

    public List<AppList> getRoGroupAppList() {
        return roGroupAppList;
    }

    public void setRoGroupAppList(List<AppList> roGroupAppList) {
        this.roGroupAppList = roGroupAppList;
    }

    public SimpleVpcVo getVpcVo() {
        return vpcVo;
    }

    public void setVpcVo(SimpleVpcVo vpcVo) {
        this.vpcVo = vpcVo;
    }

    public SubnetVo getSubnetVo() {
        return subnetVo;
    }

    public void setSubnetVo(SubnetVo subnetVo) {
        this.subnetVo = subnetVo;
    }
}
