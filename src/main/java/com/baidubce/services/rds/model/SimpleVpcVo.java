package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleVpcVo {
    private String vpcId;
    private String shortId;
    private String name;
    private String cidr;
    private Integer status;
    private Date createTime;
    private String description;
    private Boolean defaultVpc;
    private String ipv6Cidr;
    private List<String> auxiliaryCidr;
    private Boolean relay;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDefaultVpc() {
        return defaultVpc;
    }

    public void setDefaultVpc(Boolean defaultVpc) {
        this.defaultVpc = defaultVpc;
    }

    public String getIpv6Cidr() {
        return ipv6Cidr;
    }

    public void setIpv6Cidr(String ipv6Cidr) {
        this.ipv6Cidr = ipv6Cidr;
    }

    public List<String> getAuxiliaryCidr() {
        return auxiliaryCidr;
    }

    public void setAuxiliaryCidr(List<String> auxiliaryCidr) {
        this.auxiliaryCidr = auxiliaryCidr;
    }

    public Boolean getRelay() {
        return relay;
    }

    public void setRelay(Boolean relay) {
        this.relay = relay;
    }
}
