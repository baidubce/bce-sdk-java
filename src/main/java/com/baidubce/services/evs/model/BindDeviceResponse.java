package com.baidubce.services.evs.model;

public class BindDeviceResponse extends EvsBaseResponse{

    private static final long serialVersionUID = 911116340410567274L;

    private Long deviceId;
    private String snCode;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BindDeviceResponse that = (BindDeviceResponse) o;

        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) {
            return false;
        }
        return snCode != null ? snCode.equals(that.snCode) : that.snCode == null;
    }

    @Override
    public int hashCode() {
        int result = deviceId != null ? deviceId.hashCode() : 0;
        result = 31 * result + (snCode != null ? snCode.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "BindDeviceResponse{" +
                "deviceId=" + deviceId +
                ", snCode='" + snCode + '\'' +
                '}';
    }

}
