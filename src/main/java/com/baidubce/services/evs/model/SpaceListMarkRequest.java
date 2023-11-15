package com.baidubce.services.evs.model;

public class SpaceListMarkRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -7205390311831772716L;

    private Long marker;

    private int maxSize;

    private String spaceName;

    /**
     * Support：OPERATING、RUNNING、STOPPED
     */
    private String status;

    private String type;

    public Long getMarker() {
        return marker;
    }

    public void setMarker(Long marker) {
        this.marker = marker;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpaceListMarkRequest that = (SpaceListMarkRequest) o;

        if (maxSize != that.maxSize) {
            return false;
        }
        if (marker != null ? !marker.equals(that.marker) : that.marker != null) {
            return false;
        }
        if (spaceName != null ? !spaceName.equals(that.spaceName) : that.spaceName != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = marker != null ? marker.hashCode() : 0;
        result = 31 * result + maxSize;
        result = 31 * result + (spaceName != null ? spaceName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpaceListMarkRequest{" +
                "marker=" + marker +
                ", maxSize=" + maxSize +
                ", spaceName='" + spaceName + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
