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
package com.baidubce.services.bvw.model.workflow;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import com.baidubce.services.bvw.model.common.UtcTime;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Get workflow response.
 */
public class WorkflowGetResponse extends AbstractBceResponse {

    /**
     * The workflow id.
     */
    private String workflowId;
    /**
     * The workflow name.
     */
    private String name;
    /**
     * The workflow dag expression.
     */
    private String expression;
    /**
     * The workflow status.
     */
    private WorkflowStatus status;
    /**
     * The workflow create time.
     */
    @UtcTime
    private Date createTime;
    /**
     * The workflow update time.
     */
    @UtcTime
    private Date updateTime;

    public WorkflowGetResponse() {
        this.metadata = new BvwResponseMetadata();
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public WorkflowStatus getStatus() {
        return status;
    }

    public void setStatus(WorkflowStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("workflowId", workflowId)
                .append("name", name)
                .append("expression", expression)
                .append("status", status)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }

}
