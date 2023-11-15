package com.baidubce.services.evs.model;

public class DeviceUpdateRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -7575466388901180279L;

    protected String deviceName;

    protected String description;

    protected DeviceGbConfig gbConfig;

    protected Recording recording;

    protected Thumbnail thumbnail;

    protected DeviceGis gis;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceGbConfig getGbConfig() {
        return gbConfig;
    }

    public void setGbConfig(DeviceGbConfig gbConfig) {
        this.gbConfig = gbConfig;
    }

    public Recording getRecording() {
        return recording;
    }

    public void setRecording(Recording recording) {
        this.recording = recording;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
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

        DeviceUpdateRequest request = (DeviceUpdateRequest) o;

        if (deviceName != null ? !deviceName.equals(request.deviceName) : request.deviceName != null) {
            return false;
        }
        if (description != null ? !description.equals(request.description) : request.description != null) {
            return false;
        }
        if (gbConfig != null ? !gbConfig.equals(request.gbConfig) : request.gbConfig != null) {
            return false;
        }
        if (recording != null ? !recording.equals(request.recording) : request.recording != null) {
            return false;
        }
        if (thumbnail != null ? !thumbnail.equals(request.thumbnail) : request.thumbnail != null) {
            return false;
        }
        return gis != null ? gis.equals(request.gis) : request.gis == null;
    }

    @Override
    public int hashCode() {
        int result = deviceName != null ? deviceName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (gbConfig != null ? gbConfig.hashCode() : 0);
        result = 31 * result + (recording != null ? recording.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (gis != null ? gis.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceUpdateRequest{" +
                "deviceName='" + deviceName + '\'' +
                ", description='" + description + '\'' +
                ", gbConfig=" + gbConfig +
                ", recording=" + recording +
                ", thumbnail=" + thumbnail +
                ", gis=" + gis +
                "} " + super.toString();
    }


}
