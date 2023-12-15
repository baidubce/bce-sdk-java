package com.baidubce.services.et.model;

import lombok.ToString;

/**
 * The request for creating a newly ET channel route rule.
 */
@ToString
public class CreateEtChannelRouteRuleRequest extends EtChannelIdRequest {
    /**
     * IP version. IPv4's version is 4; IPv6's version is 6. Deafault value is 4.
     */
    private int ipVersion = 4;

    /**
     * The target network segment
     */
    private String destAddress;

    /**
     * Route type. The ET gateway type is "etGateway"; the ET channel type is "etChannel".
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
}
