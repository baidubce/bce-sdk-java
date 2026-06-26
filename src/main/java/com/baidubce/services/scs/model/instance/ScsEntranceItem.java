package com.baidubce.services.scs.model.instance;

/**
 * The entrance item of scs instance.
 */
public class ScsEntranceItem {

    private String ip;
    private Integer port;
    private String zone;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
