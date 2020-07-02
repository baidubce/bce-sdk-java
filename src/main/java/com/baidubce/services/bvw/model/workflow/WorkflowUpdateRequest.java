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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Update workflow request.
 */
public class WorkflowUpdateRequest extends AbstractBceRequest {

    /**
     * The workflow name.
     */
    private String name;
    /**
     * The workflow dag expression.
     */
    private DagModel expression;

    @Override
    public WorkflowUpdateRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Construct a updating workflow request with specified dag expression.
     * @param dag The workflow dag expression
     * @return A updating workflow request
     */
    public static WorkflowUpdateRequest of(String name, DagModel dag) {
        WorkflowUpdateRequest updateRequest = new WorkflowUpdateRequest();
        updateRequest.setName(name);
        updateRequest.setExpression(dag);
        return updateRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DagModel getExpression() {
        return expression;
    }

    public void setExpression(DagModel expression) {
        this.expression = expression;
    }

}
