package com.baidubce.services.evs.model;

public class SpaceUpdateRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -1113013505386652759L;

    protected String spaceName;

    protected String description;

    protected UpstreamAuth upstreamAuth;

    protected DownstreamAuth downstreamAuth;

    protected Recording recording;

    protected Thumbnail thumbnail;

    protected TimeShift timeShift;

    protected CallbackUpdateRequest callback;

    protected SpaceGbProperties gbProperties;

    protected AiConfigRequest aiConfig;

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

    public CallbackUpdateRequest getCallback() {
        return callback;
    }

    public void setCallback(CallbackUpdateRequest callback) {
        this.callback = callback;
    }

    public AiConfigRequest getAiConfig() {
        return aiConfig;
    }

    public void setAiConfig(AiConfigRequest aiConfig) {
        this.aiConfig = aiConfig;
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

    public SpaceGbProperties getGbProperties() {
        return gbProperties;
    }

    public void setGbProperties(SpaceGbProperties gbProperties) {
        this.gbProperties = gbProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpaceUpdateRequest request = (SpaceUpdateRequest) o;

        if (spaceName != null ? !spaceName.equals(request.spaceName) : request.spaceName != null) {
            return false;
        }
        if (description != null ? !description.equals(request.description) : request.description != null) {
            return false;
        }
        if (upstreamAuth != null ? !upstreamAuth.equals(request.upstreamAuth) : request.upstreamAuth != null) {
            return false;
        }
        if (downstreamAuth != null ? !downstreamAuth.equals(request.downstreamAuth) : request.downstreamAuth != null) {
            return false;
        }
        if (recording != null ? !recording.equals(request.recording) : request.recording != null) {
            return false;
        }
        if (thumbnail != null ? !thumbnail.equals(request.thumbnail) : request.thumbnail != null) {
            return false;
        }
        if (timeShift != null ? !timeShift.equals(request.timeShift) : request.timeShift != null) {
            return false;
        }
        if (callback != null ? !callback.equals(request.callback) : request.callback != null) {
            return false;
        }
        return aiConfig != null ? aiConfig.equals(request.aiConfig) : request.aiConfig == null;
    }

    @Override
    public int hashCode() {
        int result = spaceName != null ? spaceName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (upstreamAuth != null ? upstreamAuth.hashCode() : 0);
        result = 31 * result + (downstreamAuth != null ? downstreamAuth.hashCode() : 0);
        result = 31 * result + (recording != null ? recording.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (timeShift != null ? timeShift.hashCode() : 0);
        result = 31 * result + (callback != null ? callback.hashCode() : 0);
        result = 31 * result + (aiConfig != null ? aiConfig.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpaceUpdateRequest{" +
                "spaceName='" + spaceName + '\'' +
                ", description='" + description + '\'' +
                ", upstreamAuth=" + upstreamAuth +
                ", downstreamAuth=" + downstreamAuth +
                ", recording=" + recording +
                ", thumbnail=" + thumbnail +
                ", timeShift=" + timeShift +
                ", callback=" + callback +
                ", aiConfig=" + aiConfig +
                "} " + super.toString();
    }
}
