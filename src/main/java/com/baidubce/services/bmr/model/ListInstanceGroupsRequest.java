/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

/**
 * Provide options for getting the instance groups of the target cluster.
 *
 * The essential option is the ID of cluster.
 */
public class ListInstanceGroupsRequest extends AbstractBceRequest {
    private String clusterId;

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterId() {
        return this.clusterId;
    }

    /**
     * Configure the cluster ID for the request.
     *
     * @param clusterId The ID of the target cluster.
     * @return ListInstanceGroupsRequest
     */
    public ListInstanceGroupsRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListInstanceGroupsRequest
     */
    public ListInstanceGroupsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
