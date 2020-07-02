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
import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import com.baidubce.services.bvw.model.common.UtcTime;
import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * List instances of media response.
 */
public class MediaInstanceListResponse extends AbstractBceResponse {

    /**
     * The instance list.
     */
    private List<MediaInstance> instances;

    public MediaInstanceListResponse() {
        this.metadata = new BvwResponseMetadata();
    }

    public List<MediaInstance> getInstances() {
        return instances;
    }

    public void setInstances(
            List<MediaInstance> instances) {
        this.instances = instances;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("instances", instances)
                .toString();
    }

    /**
     * The instances of a media.
     */
    public static class MediaInstance {
        /**
         * The instance id.
         */
        private String instanceId;
        /**
         * The instance status.
         */
        private RunnableStatus status;
        /**
         * The workflow id.
         */
        private String workflowId;
        /**
         * The workflow name.
         */
        private String workflowName;
        /**
         * The media id.
         */
        private String mediaId;
        /**
         * The media source bucket name.
         */
        private String sourceBucket;
        /**
         * The media source key.
         */
        private String sourceKey;
        /**
         * The instance create time.
         */
        @UtcTime
        private Date createTime;

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

        public String getWorkflowId() {
            return workflowId;
        }

        public void setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
        }

        public String getWorkflowName() {
            return workflowName;
        }

        public void setWorkflowName(String workflowName) {
            this.workflowName = workflowName;
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

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("instanceId", instanceId)
                    .append("status", status)
                    .append("workflowId", workflowId)
                    .append("workflowName", workflowName)
                    .append("mediaId", mediaId)
                    .append("sourceBucket", sourceBucket)
                    .append("sourceKey", sourceKey)
                    .append("createTime", createTime)
                    .toString();
        }

    }

}
