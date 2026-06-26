package com.baidubce.services.scs.model;

/**
 * The node of scs proxy list
 */
public class ScsProxyNode {

    private String uuid;
    private String nodeShowId;
    private String availabilityZone;
    private String nodeId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNodeShowId() {
        return nodeShowId;
    }

    public void setNodeShowId(String nodeShowId) {
        this.nodeShowId = nodeShowId;
    }

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
