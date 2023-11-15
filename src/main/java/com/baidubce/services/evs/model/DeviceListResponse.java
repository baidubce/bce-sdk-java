package com.baidubce.services.evs.model;

import java.io.Serializable;
import java.util.List;

public class DeviceListResponse implements Serializable {

    private static final long serialVersionUID = -4521760057472611844L;

    private Long deviceId;

    private String deviceName;

    private String spaceName;

    private String type;

    private String platform;

    private String manufacturer;

    private Integer channelCount;

    private String status;

    private String description;

    private String deviceStreamId;

    private DeviceGis gis;

    private List<DomainGetResponse> domainList;

    public DeviceGis getGis() {
        return gis;
    }

    public void setGis(DeviceGis gis) {
        this.gis = gis;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(Integer channelCount) {
        this.channelCount = channelCount;
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

    public String getDeviceStreamId() {
        return deviceStreamId;
    }

    public void setDeviceStreamId(String deviceStreamId) {
        this.deviceStreamId = deviceStreamId;
    }

    public List<DomainGetResponse> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<DomainGetResponse> domainList) {
        this.domainList = domainList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceListResponse that = (DeviceListResponse) o;

        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) {
            return false;
        }
        if (deviceName != null ? !deviceName.equals(that.deviceName) : that.deviceName != null) {
            return false;
        }
        if (spaceName != null ? !spaceName.equals(that.spaceName) : that.spaceName != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (platform != null ? !platform.equals(that.platform) : that.platform != null) {
            return false;
        }
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) {
            return false;
        }
        if (channelCount != null ? !channelCount.equals(that.channelCount) : that.channelCount != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (deviceStreamId != null ? !deviceStreamId.equals(that.deviceStreamId) : that.deviceStreamId != null) {
            return false;
        }
        if (gis != null ? !gis.equals(that.gis) : that.gis != null) {
            return false;
        }
        return domainList != null ? domainList.equals(that.domainList) : that.domainList == null;
    }

    @Override
    public int hashCode() {
        int result = deviceId != null ? deviceId.hashCode() : 0;
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (gis != null ? gis.hashCode() : 0);
        result = 31 * result + (spaceName != null ? spaceName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (channelCount != null ? channelCount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (deviceStreamId != null ? deviceStreamId.hashCode() : 0);
        result = 31 * result + (domainList != null ? domainList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceListResponse{" +
                "deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", type='" + type + '\'' +
                ", platform='" + platform + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", channelCount=" + channelCount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", deviceStreamId='" + deviceStreamId + '\'' +
                ", gis=" + gis +
                ", domainList=" + domainList +
                '}';
    }
}
