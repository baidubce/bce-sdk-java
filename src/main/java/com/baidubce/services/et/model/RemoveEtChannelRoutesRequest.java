package com.baidubce.services.et.model;

import java.util.List;
import lombok.ToString;

@ToString
public class RemoveEtChannelRoutesRequest extends EtChannelIdRequest {
    private List<String> networks;
    private List<String> ipv6Networks;
    private String routeType;

    public List<String> getNetworks() {
        return networks;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }

    public List<String> getIpv6Networks() {
        return ipv6Networks;
    }

    public void setIpv6Networks(List<String> ipv6Networks) {
        this.ipv6Networks = ipv6Networks;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }
}
