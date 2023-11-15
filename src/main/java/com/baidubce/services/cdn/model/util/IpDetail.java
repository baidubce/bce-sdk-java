package com.baidubce.services.cdn.model.util;

public class IpDetail {
    private String ip;
    private String isp;
    private String region;
    private Boolean cdnIP;

    public IpDetail() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getCdnIP() {
        return cdnIP;
    }

    public void setCdnIP(Boolean cdnIP) {
        this.cdnIP = cdnIP;
    }
}
