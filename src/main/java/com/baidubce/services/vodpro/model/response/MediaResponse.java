package com.baidubce.services.vodpro.model.response;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.vodpro.model.common.MediaMeta;
import com.baidubce.services.vodpro.model.common.Path;
import com.baidubce.services.vodpro.model.common.TriggerStatus;
import com.baidubce.services.vodpro.model.common.UtcTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created on 17/8/24
 *
 * @author liumin08
 */
public class MediaResponse extends AbstractBceResponse {

    private Path path;

    @JsonIgnore
    private Long spaceId;

    @JsonIgnore
    private Long projectId;

    @JsonIgnore
    private Long mediaId;

    @JsonIgnore
    private Long userId;

    @UtcTime
    private Date createTime;

    private MediaMeta meta;

    private String triggerName;

    private String description;

    private TriggerStatus triggerStatus;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MediaMeta getMeta() {
        return meta;
    }

    public void setMeta(MediaMeta meta) {
        this.meta = meta;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TriggerStatus getTriggerStatus() {
        return triggerStatus;
    }

    public void setTriggerStatus(TriggerStatus triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    @Override
    public String toString() {
        return "CreateMediaResponse{"
                + "path=" + path
                + ", spaceId=" + spaceId
                + ", projectId=" + projectId
                + ", mediaId=" + mediaId
                + ", userId=" + userId
                + ", createTime=" + createTime
                + ", meta=" + meta
                + ", triggerName='" + triggerName + '\''
                + ", description='" + description + '\''
                + ", triggerStatus=" + triggerStatus
                + '}';
    }
}
