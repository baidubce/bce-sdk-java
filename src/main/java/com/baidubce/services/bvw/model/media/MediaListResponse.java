/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bvw.model.media;

import java.util.Date;

import com.baidubce.services.bvw.model.common.UtcTime;
import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Media list response.
 */
public class MediaListResponse {

    /**
     * The media id.
     */
    private String mediaId;
    /**
     * The media title.
     */
    private String title;
    /**
     * The media status.
     */
    private MediaStatus status;
    /**
     * The latest instance id of the processing media.
     */
    private String latestInstanceId;
    /**
     * The latest instance status of the processing media.
     */
    private RunnableStatus instanceStatus;
    /**
     * The latest using workflow id.
     */
    private String latestWorkflowId;
    /**
     * The latest using workflow name.
     */
    private String latestWorkflowName;
    /**
     * The media source bucket name.
     */
    private String sourceBucket;
    /**
     * The media source key.
     */
    private String sourceKey;
    /**
     * The create time of media.
     */
    @UtcTime
    private Date createTime;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaStatus getStatus() {
        return status;
    }

    public void setStatus(MediaStatus status) {
        this.status = status;
    }

    public String getLatestInstanceId() {
        return latestInstanceId;
    }

    public void setLatestInstanceId(String latestInstanceId) {
        this.latestInstanceId = latestInstanceId;
    }

    public RunnableStatus getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(RunnableStatus instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getLatestWorkflowId() {
        return latestWorkflowId;
    }

    public void setLatestWorkflowId(String latestWorkflowId) {
        this.latestWorkflowId = latestWorkflowId;
    }

    public String getLatestWorkflowName() {
        return latestWorkflowName;
    }

    public void setLatestWorkflowName(String latestWorkflowName) {
        this.latestWorkflowName = latestWorkflowName;
    }

    public String getSourceBucket() {
        return sourceBucket;
    }

    public void setSourceBucket(String sourceBucket) {
        this.sourceBucket = sourceBucket;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mediaId", mediaId)
                .append("title", title)
                .append("status", status)
                .append("latestInstanceId", latestInstanceId)
                .append("instanceStatus", instanceStatus)
                .append("latestWorkflowId", latestWorkflowId)
                .append("latestWorkflowName", latestWorkflowName)
                .append("sourceBucket", sourceBucket)
                .append("sourceKey", sourceKey)
                .append("createTime", createTime)
                .toString();
    }

}
