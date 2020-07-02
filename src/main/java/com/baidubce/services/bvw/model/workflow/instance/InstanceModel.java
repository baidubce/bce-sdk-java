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
package com.baidubce.services.bvw.model.workflow.instance;

import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The instance of processing media and workflow.
 */
public class InstanceModel {

    /**
     * The instance id.
     */
    private String instanceId;
    /**
     * The instance status.
     */
    private RunnableStatus status;
    /**
     * The user id.
     */
    private String userId;
    /**
     * The workflow id.
     */
    private String workflowId;
    /**
     * The media id.
     */
    private String mediaId;
    /**
     * The source bucket of media.
     */
    private String sourceBucket;
    /**
     * Th source key.
     */
    private String sourceKey;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public RunnableStatus getStatus() {
        return status;
    }

    public void setStatus(RunnableStatus status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("instanceId", instanceId)
                .append("status", status)
                .append("userId", userId)
                .append("workflowId", workflowId)
                .append("mediaId", mediaId)
                .append("sourceBucket", sourceBucket)
                .append("sourceKey", sourceKey)
                .toString();
    }

}
