/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide options for adding steps to the target cluster.
 *
 * The essential option is the ID of cluster, and the List of steps can be constructed by
 * calling the methods of StepConfig.
 */
public class AddStepsRequest extends AbstractBceRequest {
    private String clusterId;
    private List<StepConfig> steps;
    private String clientToken;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public List<StepConfig> getSteps() {
        return steps;
    }

    public void setSteps(List<StepConfig> steps) {
        this.steps = steps;
    }

    /**
     * Configure the ID of the cluster.
     *
     * @param clusterId The ID of the cluster.
     * @return AddStepsRequest
     */
    public AddStepsRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    /**
     * Configure the step to be added.
     *
     * @param step a StepConfig object to be added.
     * @return AddStepsRequest
     */
    public AddStepsRequest withStep(StepConfig step) {
        if (this.steps == null) {
            this.steps = new ArrayList<StepConfig>();
        }
        this.steps.add(step);
        return this;
    }

    /**
     * Configure the steps to be added. This method will replace the AddStepsRequest instance's
     * steps by the @param steps totally, thus it should be invoked ahead of withStep method
     * if both of them are used for the same AddStepsRequest instance.
     *
     * @param steps a List of StepConfig instances to be added.
     * @return AddStepsRequest
     */
    public AddStepsRequest withSteps(List<StepConfig> steps) {
        this.setSteps(steps);
        return this;
    }

    /**
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     *
     * @param clientToken An ASCII string whose length is less than 64.
     * @return AddStepsRequest
     */
    public AddStepsRequest withClientToken(String clientToken) {
        this.setClientToken(clientToken);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return AddStepsRequest
     */
    public AddStepsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
