package com.baidubce.services.evs.model;



public class BindDeviceRequest extends EvsBaseRequest {

    private static final long serialVersionUID = 7590066621249900565L;
    private Long spaceId;

    private String snCode;

    private String deviceName;

    private String deviceMode;

    private String type ;

    protected String description;

    protected DeviceGis gis;

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(String deviceMode) {
        this.deviceMode = deviceMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceGis getGis() {
        return gis;
    }

    public void setGis(DeviceGis gis) {
        this.gis = gis;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BindDeviceRequest that = (BindDeviceRequest) o;

        if (spaceId != null ? !spaceId.equals(that.spaceId) : that.spaceId != null) {
            return false;
        }
        if (snCode != null ? !snCode.equals(that.snCode) : that.snCode != null) {
            return false;
        }
        if (deviceName != null ? !deviceName.equals(that.deviceName) : that.deviceName != null) {
            return false;
        }
        if (deviceMode != null ? !deviceMode.equals(that.deviceMode) : that.deviceMode != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (gis != null ? !gis.equals(that.gis) : that.gis != null) {
            return false;
        }
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = spaceId != null ? spaceId.hashCode() : 0;
        result = 31 * result + (snCode != null ? snCode.hashCode() : 0);
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (deviceMode != null ? deviceMode.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (gis != null ? gis.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BindDeviceRequest{" +
                "spaceId=" + spaceId +
                ", snCode='" + snCode + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceMode='" + deviceMode + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", gis=" + gis +
                '}';
    }
}
