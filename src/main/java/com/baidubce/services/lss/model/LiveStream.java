package com.baidubce.services.lss.model;

/**
 * Created by wuyafei on 16/10/14.
 */
public class LiveStream {
    private String sessionId = null;

    private String playDomain = null;

    private String app = null;

    private String description = null;

    private String createTime = null;

    private String status = null;

    private String streamingStatus = null;

    private LivePublish publish = null;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPlayDomain() {
        return playDomain;
    }

    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LivePublish getPublish() {
        return publish;
    }

    public void setPublish(LivePublish publish) {
        this.publish = publish;
    }

    public String getStreamingStatus() {
        return streamingStatus;
    }

    public void setStreamingStatus(String streamingStatus) {
        this.streamingStatus = streamingStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveSession {\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("    playDomain: ").append(playDomain).append("\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("    streamingStatus: ").append(streamingStatus).append("\n");
        sb.append("    publish: ").append(publish).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
