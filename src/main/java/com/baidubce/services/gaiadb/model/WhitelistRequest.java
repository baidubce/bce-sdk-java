package com.baidubce.services.gaiadb.model;

import java.util.List;

public class WhitelistRequest extends GenericGaiadbRequest {
    private String clusterId;
    private List<String> authIps;
    private List<String> bns;
    private String etag;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public List<String> getAuthIps() {
        return authIps;
    }

    public void setAuthIps(List<String> authIps) {
        this.authIps = authIps;
    }

    public List<String> getBns() {
        return bns;
    }

    public void setBns(List<String> bns) {
        this.bns = bns;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
