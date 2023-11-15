package com.baidubce.services.evs.model;

public class DeviceCreateResponse extends EvsBaseResponse {

    private static final long serialVersionUID = 7846741888943929527L;

    private Long deviceId;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceCreateResponse that = (DeviceCreateResponse) o;

        return deviceId != null ? deviceId.equals(that.deviceId) : that.deviceId == null;
    }

    @Override
    public int hashCode() {
        return deviceId != null ? deviceId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DeviceCreateResponse{" +
                "deviceId=" + deviceId +
                "} " + super.toString();
    }
}
