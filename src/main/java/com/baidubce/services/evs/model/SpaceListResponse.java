package com.baidubce.services.evs.model;

public class SpaceListResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -5018672399868262333L;

    private Long spaceId;

    private String spaceName;

    private String type;

    private String status;

    private String deviceMode;

    private String description;

    private Integer deviceCount;

    private Long edgeId;

    private String edgeName;

    public String getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(String deviceMode) {
        this.deviceMode = deviceMode;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Long getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Long edgeId) {
        this.edgeId = edgeId;
    }

    public String getEdgeName() {
        return edgeName;
    }

    public void setEdgeName(String edgeName) {
        this.edgeName = edgeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpaceListResponse that = (SpaceListResponse) o;

        if (spaceId != null ? !spaceId.equals(that.spaceId) : that.spaceId != null) {
            return false;
        }
        if (spaceName != null ? !spaceName.equals(that.spaceName) : that.spaceName != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (deviceMode != null ? !deviceMode.equals(that.deviceMode) : that.deviceMode != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (deviceCount != null ? !deviceCount.equals(that.deviceCount) : that.deviceCount != null) {
            return false;
        }
        if (edgeId != null ? !edgeId.equals(that.edgeId) : that.edgeId != null) {
            return false;
        }
        return edgeName != null ? edgeName.equals(that.edgeName) : that.edgeName == null;
    }

    @Override
    public int hashCode() {
        int result = spaceId != null ? spaceId.hashCode() : 0;
        result = 31 * result + (spaceName != null ? spaceName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (deviceMode != null ? deviceMode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (deviceCount != null ? deviceCount.hashCode() : 0);
        result = 31 * result + (edgeId != null ? edgeId.hashCode() : 0);
        result = 31 * result + (edgeName != null ? edgeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpaceListResponse{" +
                "spaceId=" + spaceId +
                ", spaceName='" + spaceName + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", deviceMode='" + deviceMode + '\'' +
                ", description='" + description + '\'' +
                ", deviceCount=" + deviceCount +
                ", edgeId=" + edgeId +
                ", edgeName='" + edgeName + '\'' +
                '}';
    }
}
