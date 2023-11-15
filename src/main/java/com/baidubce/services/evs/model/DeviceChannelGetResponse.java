package com.baidubce.services.evs.model;

public class DeviceChannelGetResponse extends EvsBaseResponse {

    private static final long serialVersionUID = 1055112249058272679L;

    private Long channelId;

    private String channelName;

    private String channelGbId;

    private String channelStatus;

    private String manufacturer;

    private String appStream;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelGbId() {
        return channelGbId;
    }

    public void setChannelGbId(String channelGbId) {
        this.channelGbId = channelGbId;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAppStream() {
        return appStream;
    }

    public void setAppStream(String appStream) {
        this.appStream = appStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceChannelGetResponse that = (DeviceChannelGetResponse) o;

        if (channelId != null ? !channelId.equals(that.channelId) : that.channelId != null) {
            return false;
        }
        if (channelName != null ? !channelName.equals(that.channelName) : that.channelName != null) {
            return false;
        }
        if (channelGbId != null ? !channelGbId.equals(that.channelGbId) : that.channelGbId != null) {
            return false;
        }
        if (channelStatus != null ? !channelStatus.equals(that.channelStatus) : that.channelStatus != null) {
            return false;
        }
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) {
            return false;
        }
        return appStream != null ? appStream.equals(that.appStream) : that.appStream == null;
    }

    @Override
    public int hashCode() {
        int result = channelId != null ? channelId.hashCode() : 0;
        result = 31 * result + (channelName != null ? channelName.hashCode() : 0);
        result = 31 * result + (channelGbId != null ? channelGbId.hashCode() : 0);
        result = 31 * result + (channelStatus != null ? channelStatus.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (appStream != null ? appStream.hashCode() : 0);
        return result;
    }
}
