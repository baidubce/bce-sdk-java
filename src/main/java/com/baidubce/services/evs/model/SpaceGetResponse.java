package com.baidubce.services.evs.model;

import java.util.Date;
import java.util.List;

public class SpaceGetResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -40346532593807291L;

    private Long spaceId;

    private String spaceName;

    private String description;

    private String type;

    private String status;

    private List<DomainGetResponse> domains;

    private Recording recording;

    private Thumbnail thumbnail;

    private TimeShift timeShift;

    private CallbackGetResponse callback;

    private SpaceGbProperties gbProperties;

    private EdgeGetResponse edge;

    private String serialId;

    private Integer deviceCount;

    private Date createTime;

    private String rtcAppId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public TimeShift getTimeShift() {
        return timeShift;
    }

    public void setTimeShift(TimeShift timeShift) {
        this.timeShift = timeShift;
    }

    public CallbackGetResponse getCallback() {
        return callback;
    }

    public void setCallback(CallbackGetResponse callback) {
        this.callback = callback;
    }

    public SpaceGbProperties getGbProperties() {
        return gbProperties;
    }

    public void setGbProperties(SpaceGbProperties gbProperties) {
        this.gbProperties = gbProperties;
    }

    public EdgeGetResponse getEdge() {
        return edge;
    }

    public void setEdge(EdgeGetResponse edge) {
        this.edge = edge;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRtcAppId() {
        return rtcAppId;
    }

    public void setRtcAppId(String rtcAppId) {
        this.rtcAppId = rtcAppId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpaceGetResponse response = (SpaceGetResponse) o;

        if (spaceId != null ? !spaceId.equals(response.spaceId) : response.spaceId != null) {
            return false;
        }
        if (spaceName != null ? !spaceName.equals(response.spaceName) : response.spaceName != null) {
            return false;
        }
        if (description != null ? !description.equals(response.description) : response.description != null) {
            return false;
        }
        if (type != response.type) {
            return false;
        }
        if (status != response.status) {
            return false;
        }
        if (domains != null ? !domains.equals(response.domains) : response.domains != null) {
            return false;
        }
        if (recording != null ? !recording.equals(response.recording) : response.recording != null) {
            return false;
        }
        if (thumbnail != null ? !thumbnail.equals(response.thumbnail) : response.thumbnail != null) {
            return false;
        }
        if (timeShift != null ? !timeShift.equals(response.timeShift) : response.timeShift != null) {
            return false;
        }
        if (callback != null ? !callback.equals(response.callback) : response.callback != null) {
            return false;
        }
        if (gbProperties != null ? !gbProperties.equals(response.gbProperties) : response.gbProperties != null) {
            return false;
        }
        if (edge != null ? !edge.equals(response.edge) : response.edge != null) {
            return false;
        }
        if (deviceCount != null ? !deviceCount.equals(response.deviceCount) : response.deviceCount != null) {
            return false;
        }
        return createTime != null ? createTime.equals(response.createTime) : response.createTime == null;
    }

    @Override
    public int hashCode() {
        int result = spaceId != null ? spaceId.hashCode() : 0;
        result = 31 * result + (spaceName != null ? spaceName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        result = 31 * result + (recording != null ? recording.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (timeShift != null ? timeShift.hashCode() : 0);
        result = 31 * result + (callback != null ? callback.hashCode() : 0);
        result = 31 * result + (gbProperties != null ? gbProperties.hashCode() : 0);
        result = 31 * result + (edge != null ? edge.hashCode() : 0);
        result = 31 * result + (deviceCount != null ? deviceCount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpaceGetResponse{" +
                "spaceId=" + spaceId +
                ", spaceName='" + spaceName + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", domains=" + domains +
                ", recording=" + recording +
                ", thumbnail=" + thumbnail +
                ", timeShift=" + timeShift +
                ", callback=" + callback +
                ", gbProperties=" + gbProperties +
                ", edge=" + edge +
                ", deviceCount=" + deviceCount +
                ", createTime=" + createTime +
                "} " + super.toString();
    }
}
