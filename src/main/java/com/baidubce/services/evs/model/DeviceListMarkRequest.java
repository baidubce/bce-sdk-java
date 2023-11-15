package com.baidubce.services.evs.model;

public class DeviceListMarkRequest extends EvsBaseRequest {

    private static final long serialVersionUID = 1462348539574354040L;

    private Long spaceId;

    private Long marker;

    private int maxSize;

    private String deviceName;

    /**
     * Support：
     * Rtmp：ONLINE、RUNNING、OFFLINE
     * GB28181：UNREGISTERED、ONLINE、OFFLINE
     */
    private String status;

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceListMarkRequest request = (DeviceListMarkRequest) o;

        if (maxSize != request.maxSize) {
            return false;
        }
        if (spaceId != null ? !spaceId.equals(request.spaceId) : request.spaceId != null) {
            return false;
        }
        if (marker != null ? !marker.equals(request.marker) : request.marker != null) {
            return false;
        }
        if (deviceName != null ? !deviceName.equals(request.deviceName) : request.deviceName != null) {
            return false;
        }
        return status != null ? status.equals(request.status) : request.status == null;
    }

    @Override
    public int hashCode() {
        int result = spaceId != null ? spaceId.hashCode() : 0;
        result = 31 * result + (marker != null ? marker.hashCode() : 0);
        result = 31 * result + maxSize;
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceListMarkRequest{" +
                "spaceId=" + spaceId +
                ", marker=" + marker +
                ", maxSize=" + maxSize +
                ", deviceName='" + deviceName + '\'' +
                ", status=" + status +
                "} " + super.toString();
    }
}
