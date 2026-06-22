package com.baidubce.services.gaiadb.model;

public class GaiadbInterfaceUpdateDnsNameRequest extends GenericGaiadbRequest {
    private String clusterId;
    private String interfaceId;
    private String dnsName;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }
}
