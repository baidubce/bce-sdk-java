package com.baidubce.services.scs.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Request instance list
 */
public class InstanceListRequest extends AbstractBceRequest {

    private String marker;
    private Integer maxKeys;
    private String instanceIds;
    private String vnetIp;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(String instanceIds) {
        this.instanceIds = instanceIds;
    }

    public String getVnetIp() {
        return vnetIp;
    }

    public void setVnetIp(String vnetIp) {
        this.vnetIp = vnetIp;
    }

    @Override
    public InstanceListRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
