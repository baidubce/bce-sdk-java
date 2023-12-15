package com.baidubce.services.et.model;

import lombok.ToString;

/**
 * The model for describing ET channel route rule.
 */
@ToString
public class EtChannelRouteRule {
    /**
     * ET channel route rule ID
     */
    private String routeRuleId;

    /**
     * IP version. IPv4's version is 4; IPv6's version is 6. Deafault value is 4.
     */
    private int ipVersion = 4;

    /**
     * The target network segment
     */
    private String destAddress;

    /**
     * Route type. Its value range is etGateway/etChannel/csn.
     */
    private String nexthopType;

    /**
     * Next hop id
     */
    private String nexthopId;

    /**
     * Description
     */
    private String description;

    /**
     * Route protocal. Its value range is static-route/bgp.
     */
    private String routeProto;

    /**
     * As-Path. Only when “routeType” is "bgp", the as-path is available.
     */
    private String asPaths;

    /**
     * LocalPreference. Only when “routeType” is "bgp", the as-path is available.
     */
    private Integer localPreference;

    /**
     * Med. Only when “routeType” is "bgp", the as-path is available.
     */
    private Integer med;

    /**
     * Origin. Only when “routeType” is "bgp", the as-path is available.
     */
    private String origin;

    public String getRouteRuleId() {
        return routeRuleId;
    }

    public void setRouteRuleId(String routeRuleId) {
        this.routeRuleId = routeRuleId;
    }

    public int getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(int ipVersion) {
        this.ipVersion = ipVersion;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getNexthopType() {
        return nexthopType;
    }

    public void setNexthopType(String nexthopType) {
        this.nexthopType = nexthopType;
    }

    public String getNexthopId() {
        return nexthopId;
    }

    public void setNexthopId(String nexthopId) {
        this.nexthopId = nexthopId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRouteProto() {
        return routeProto;
    }

    public void setRouteProto(String routeProto) {
        this.routeProto = routeProto;
    }

    public String getAsPaths() {
        return asPaths;
    }

    public void setAsPaths(String asPaths) {
        this.asPaths = asPaths;
    }

    public Integer getLocalPreference() {
        return localPreference;
    }

    public void setLocalPreference(Integer localPreference) {
        this.localPreference = localPreference;
    }

    public Integer getMed() {
        return med;
    }

    public void setMed(Integer med) {
        this.med = med;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
