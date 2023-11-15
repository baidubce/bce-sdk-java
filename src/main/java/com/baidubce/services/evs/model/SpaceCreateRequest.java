package com.baidubce.services.evs.model;

import java.util.List;

public class SpaceCreateRequest extends SpaceUpdateRequest {

    private static final long serialVersionUID = -3008218172463593110L;

    /**
     * Support: RTMP、GB28181、BVCP
     */
    private String type;

    // bvcp空间设备型号
    private String deviceMode = "";

    private List<DomainCreateRequest> domains;

    private Long edgeId;

    /**
     * Support: BANDWIDTH、TRAFFIC
     */
    private String evsChargeType = "TRAFFIC";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DomainCreateRequest> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainCreateRequest> domains) {
        this.domains = domains;
    }

    public Long getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Long edgeId) {
        this.edgeId = edgeId;
    }

    public String getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(String deviceMode) {
        this.deviceMode = deviceMode;
    }

    public String getEvsChargeType() {
        return evsChargeType;
    }

    public void setEvsChargeType(String evsChargeType) {
        this.evsChargeType = evsChargeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        SpaceCreateRequest that = (SpaceCreateRequest) o;

        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (domains != null ? !domains.equals(that.domains) : that.domains != null) {
            return false;
        }
        if (edgeId != null ? !edgeId.equals(that.edgeId) : that.edgeId != null) {
            return false;
        }
        if (deviceMode != null ? !deviceMode.equals(that.deviceMode) : that.deviceMode != null) {
            return false;
        }
        return evsChargeType != null ? evsChargeType.equals(that.evsChargeType) : that.evsChargeType == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        result = 31 * result + (edgeId != null ? edgeId.hashCode() : 0);
        result = 31 * result + (deviceMode != null ? deviceMode.hashCode() : 0);
        result = 31 * result + (evsChargeType != null ? evsChargeType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpaceCreateRequest{" +
                "type='" + type + '\'' +
                ", deviceMode='" + deviceMode + '\'' +
                ", domains=" + domains +
                ", edgeId=" + edgeId +
                ", evsChargeType='" + evsChargeType + '\'' +
                '}';
    }
}
