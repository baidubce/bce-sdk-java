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

public class ListInstancesRequest extends AbstractBceRequest {
    private String clusterId;
    private String instanceGroupId;

    public String getClusterId() {
        return this.clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getInstanceGroupId() {
        return this.instanceGroupId;
    }

    public void setInstanceGroupId(String instanceGroupId) {
        this.instanceGroupId = instanceGroupId;
    }

    /**
     * Configure the ID of the target cluster for the request.
     *
     * @param clusterId The ID of the target cluster.
     *
     * @return ListInstancesRequest
     */
    public ListInstancesRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    /**
     * Configure the ID of the target instance group for the request.
     *
     * @param instanceGroupId The ID of the instance group.
     *
     * @return ListInstancesRequest
     */
    public ListInstancesRequest withInstanceGroupId(String instanceGroupId) {
        this.setInstanceGroupId(instanceGroupId);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     *
     * @return ListInstancesRequest
     */
    public ListInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
