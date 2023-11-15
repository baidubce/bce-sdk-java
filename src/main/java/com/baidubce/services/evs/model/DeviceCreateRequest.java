package com.baidubce.services.evs.model;

public class DeviceCreateRequest extends DeviceUpdateRequest {

    private static final long serialVersionUID = 7653894659392858508L;

    /**
     * Support：RTMP、GB28181
     */
    protected String type;

    protected String deviceStreamId;

    protected Long spaceId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceStreamId() {
        return deviceStreamId;
    }

    public void setDeviceStreamId(String deviceStreamId) {
        this.deviceStreamId = deviceStreamId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
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

        DeviceCreateRequest request = (DeviceCreateRequest) o;

        if (type != null ? !type.equals(request.type) : request.type != null) {
            return false;
        }
        if (deviceStreamId != null ? !deviceStreamId.equals(request.deviceStreamId) : request.deviceStreamId != null) {
            return false;
        }
        return spaceId != null ? spaceId.equals(request.spaceId) : request.spaceId == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (deviceStreamId != null ? deviceStreamId.hashCode() : 0);
        result = 31 * result + (spaceId != null ? spaceId.hashCode() : 0);
        return result;
    }
}
