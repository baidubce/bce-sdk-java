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
package com.baidubce.services.bvw.model.workflow.task;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import com.baidubce.services.bvw.model.workflow.StageType;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Stage task response
 */
public class TaskGetResponse extends AbstractBceResponse {

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

    public TaskGetResponse() {
        this.metadata = new BvwResponseMetadata();
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskId", taskId)
                .append("instanceId", instanceId)
                .append("status", status)
                .append("stageId", stageId)
                .append("name", name)
                .append("param", param)
                .append("type", type)
                .toString();
    }

}
