package com.baidubce.services.as.model.asgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xucongyang on 2018/06/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EipInfo {
    private Boolean ifBindEip = false;
    private int bandwidthInMbps;
    private String eipProductType = "";
    // eip线路
    private String purchaseType = "";

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Boolean getIfBindEip() {
        return ifBindEip;
    }

    public void setIfBindEip(Boolean ifBindEip) {
        this.ifBindEip = ifBindEip;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getEipProductType() {
        return eipProductType;
    }

    public void setEipProductType(String eipProductType) {
        this.eipProductType = eipProductType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EipInfo{");
        sb.append("ifBindEip=").append(ifBindEip);
        sb.append(", bandwidthInMbps=").append(bandwidthInMbps);
        sb.append(", eipProductType='").append(eipProductType).append('\'');
        sb.append(", purchaseType='").append(purchaseType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
