package com.baidubce.services.et.model;

import java.util.List;

import com.baidubce.model.ListResponse;

/**
 * The response for listing ET channel route rules.
 */
public class ListEtChannelRouteRulesResponse extends ListResponse {
    private List<EtChannelRouteRule> routeRules;

    public List<EtChannelRouteRule> getRouteRules() {
        return routeRules;
    }

    public void setRouteRules(List<EtChannelRouteRule> routeRules) {
        this.routeRules = routeRules;
    }

    @Override
    public String toString() {
        return "ListEtChannelRouteRulesResponse{" +
                "marker=" + getMarker() + "," +
                "maxKeys=" + getMaxKeys() + "," +
                "isTruncated=" + getIsTruncated() + "," +
                "nextMarker=" + getNextMarker() + "," +
                "routeRules=" + routeRules +
                '}';
    }
}
