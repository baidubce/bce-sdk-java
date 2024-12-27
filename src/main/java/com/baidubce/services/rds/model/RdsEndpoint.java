package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Rds endpoint info
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsEndpoint {

    private String address;
    private Integer port;
    private String vnetIp;
    private String inetIp;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
}
