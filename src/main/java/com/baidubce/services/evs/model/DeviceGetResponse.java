package com.baidubce.services.evs.model;

import java.util.Date;
import java.util.List;

public class DeviceGetResponse extends EvsBaseResponse {

    private static final long serialVersionUID = 7081701229876538996L;

    private Long deviceId;

    private String deviceName;

    private String type;

    private String deviceStreamId;

    private String status;

    private String description;

    private UpstreamAuth upstreamAuth;

    private DownstreamAuth downstreamAuth;

    private List<DomainGetResponse> domains;

    private Recording recording;

    private Thumbnail thumbnail;

    private EdgeGetResponse edge;

    private Long spaceId;

    private String spaceName;

    private Integer channelCount;

    private Date createTime;

    protected DeviceGis gis;

    private String snCode;

    private String deviceMode;

    public DeviceGis getGis() {
        return gis;
    }

    public void setGis(DeviceGis gis) {
        this.gis = gis;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(String deviceMode) {
        this.deviceMode = deviceMode;
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

    public UpstreamAuth getUpstreamAuth() {
        return upstreamAuth;
    }

    public void setUpstreamAuth(UpstreamAuth upstreamAuth) {
        this.upstreamAuth = upstreamAuth;
    }

    public DownstreamAuth getDownstreamAuth() {
        return downstreamAuth;
    }

    public void setDownstreamAuth(DownstreamAuth downstreamAuth) {
        this.downstreamAuth = downstreamAuth;
    }

    public List<DomainGetResponse> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainGetResponse> domains) {
        this.domains = domains;
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

    public EdgeGetResponse getEdge() {
        return edge;
    }

    public void setEdge(EdgeGetResponse edge) {
        this.edge = edge;
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

    public Integer getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(Integer channelCount) {
        this.channelCount = channelCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceGetResponse that = (DeviceGetResponse) o;

        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) {
            return false;
        }
        if (gis != null ? !gis.equals(that.gis) : that.gis != null) {
            return false;
        }
        if (snCode != null ? !snCode.equals(that.snCode) : that.snCode != null) {
            return false;
        }
        if (deviceMode != null ? !deviceMode.equals(that.deviceMode) : that.deviceMode != null) {
            return false;
        }
        if (deviceName != null ? !deviceName.equals(that.deviceName) : that.deviceName != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (deviceStreamId != null ? !deviceStreamId.equals(that.deviceStreamId) : that.deviceStreamId != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (upstreamAuth != null ? !upstreamAuth.equals(that.upstreamAuth) : that.upstreamAuth != null) {
            return false;
        }
        if (downstreamAuth != null ? !downstreamAuth.equals(that.downstreamAuth) : that.downstreamAuth != null) {
            return false;
        }
        if (domains != null ? !domains.equals(that.domains) : that.domains != null) {
            return false;
        }
        if (recording != null ? !recording.equals(that.recording) : that.recording != null) {
            return false;
        }
        if (thumbnail != null ? !thumbnail.equals(that.thumbnail) : that.thumbnail != null) {
            return false;
        }
        if (edge != null ? !edge.equals(that.edge) : that.edge != null) {
            return false;
        }
        if (spaceId != null ? !spaceId.equals(that.spaceId) : that.spaceId != null) {
            return false;
        }
        if (spaceName != null ? !spaceName.equals(that.spaceName) : that.spaceName != null) {
            return false;
        }
        if (channelCount != null ? !channelCount.equals(that.channelCount) : that.channelCount != null) {
            return false;
        }
        return createTime != null ? createTime.equals(that.createTime) : that.createTime == null;
    }

    @Override
    public int hashCode() {
        int result = deviceId != null ? deviceId.hashCode() : 0;
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (deviceStreamId != null ? deviceStreamId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (upstreamAuth != null ? upstreamAuth.hashCode() : 0);
        result = 31 * result + (downstreamAuth != null ? downstreamAuth.hashCode() : 0);
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        result = 31 * result + (recording != null ? recording.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (edge != null ? edge.hashCode() : 0);
        result = 31 * result + (spaceId != null ? spaceId.hashCode() : 0);
        result = 31 * result + (spaceName != null ? spaceName.hashCode() : 0);
        result = 31 * result + (channelCount != null ? channelCount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (deviceMode != null ? deviceMode.hashCode() : 0);
        result = 31 * result + (snCode != null ? snCode.hashCode() : 0);
        result = 31 * result + (gis != null ? gis.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceGetResponse{" +
                "deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", type='" + type + '\'' +
                ", deviceStreamId='" + deviceStreamId + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", upstreamAuth=" + upstreamAuth +
                ", downstreamAuth=" + downstreamAuth +
                ", domains=" + domains +
                ", recording=" + recording +
                ", thumbnail=" + thumbnail +
                ", edge=" + edge +
                ", spaceId=" + spaceId +
                ", spaceName='" + spaceName + '\'' +
                ", channelCount=" + channelCount +
                ", createTime=" + createTime +
                ", gis=" + gis +
                ", snCode='" + snCode + '\'' +
                ", deviceMode='" + deviceMode + '\'' +
                '}';
    }
}
