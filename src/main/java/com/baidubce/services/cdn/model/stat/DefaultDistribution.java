package com.baidubce.services.cdn.model.stat;

public class DefaultDistribution {

    /**
     * 客户端所在省份、地区
     */
    private String location;

    /**
     * 客户端所属运营商
     */
    private String isp;

    public DefaultDistribution() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
