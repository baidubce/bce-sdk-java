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
package com.baidubce.services.bcc.model.securitygroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * The request for listing the SecurityGroup owned by the user.
 */
public class ListSecurityGroupsRequest extends ListRequest {

    /**
     * The id of instance.
     * The optional parameter to list the SecurityGroup.
     * If it's specified,only the SecurityGroup related to the specified instance will be listed.
     */
    private String instanceId;

    /**
     * filter by vpcId, optional parameter
     */
    private String vpcId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Configure the request with specified instanceId.
     * @param instanceId The id of instance.
     */
    public ListSecurityGroupsRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public ListSecurityGroupsRequest withVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListSecurityGroupsRequest with specified marker.
     */
    @Override
    public ListSecurityGroupsRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListInstancesRequest with specified maxKeys.
     */
    @Override
    public ListSecurityGroupsRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListSecurityGroupsRequest with credentials.
     */
    @Override
    public ListSecurityGroupsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
