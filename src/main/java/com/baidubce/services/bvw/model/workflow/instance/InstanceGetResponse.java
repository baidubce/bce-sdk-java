/*
 * Copyright 2019-2020 Baidu, Inc.
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

import java.util.Map;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import com.baidubce.services.bvw.model.workflow.StageType;
import com.baidubce.services.bvw.model.workflow.task.TaskParamModel;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Get instance response.
 */
public class InstanceGetResponse extends AbstractBceResponse {

    /**
     * The instance id.
     */
    private String instanceId;
    /**
     * The instance status.
     */
    private RunnableStatus status;
    /**
     * The instance running error msg.
     */
    private String errorMsg;
    /**
     * The using workflow id.
     */
    private String workflowId;
    /**
     * The using workflow name.
     */
    private String workflowName;
    /**
     * The processing media id.
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
     * The stages in this instance.
     * Structure: <name, stageTask>.
     */
    private Map<String, StageTask> stages;

    /**
     * The internal class for stage task.
     */
    public static class StageTask {

        /**
         * The task id.
         */
        private String taskId;
        /**
         * The instance id.
         */
        private String instanceId;
        /**
         * The task running status.
         */
        private RunnableStatus status;
        /**
         * The stage id.
         */
        private String stageId;
        /**
         * The stage name.
         */
        private String name;
        /**
         * The stage task param.
         */
        private TaskParamModel param;
        /**
         * The stage type.
         */
        private StageType type;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

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

        public String getStageId() {
            return stageId;
        }

        public void setStageId(String stageId) {
            this.stageId = stageId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TaskParamModel getParam() {
            return param;
        }

        public void setParam(TaskParamModel param) {
            this.param = param;
        }

        public StageType getType() {
            return type;
        }

        public void setType(StageType type) {
            this.type = type;
        }

    }

    public InstanceGetResponse() {
        this.metadata = new BvwResponseMetadata();
    }

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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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

    public Map<String, StageTask> getStages() {
        return stages;
    }

    public void setStages(Map<String, StageTask> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("instanceId", instanceId)
                .append("status", status)
                .append("errorMsg", errorMsg)
                .append("workflowId", workflowId)
                .append("workflowName", workflowName)
                .append("mediaId", mediaId)
                .append("sourceBucket", sourceBucket)
                .append("sourceKey", sourceKey)
                .append("stages", stages)
                .toString();
    }

}
