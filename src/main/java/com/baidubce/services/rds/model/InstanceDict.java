package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceDict {
    private Integer port;
    private String vnetIp;
    private String inetIp;
    private String address;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getVnetIp() {
        return vnetIp;
    }

    public void setVnetIp(String vnetIp) {
        this.vnetIp = vnetIp;
    }

    public String getInetIp() {
        return inetIp;
    }

    public void setInetIp(String inetIp) {
        this.inetIp = inetIp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
