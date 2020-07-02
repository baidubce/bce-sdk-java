/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * request model to query instance list
 */
public class ListInstancesRequest extends ListRequest {
    /**
     * The identified internal ip of instance.
     */
    private String internalIp;

    /**
     * specified id of dedicated host which instance be placed
     */
    private String dedicatedHostId;

    /**
     * the name of available zone
     */
    private String zoneName;

    /**
     * The id of the keypair
     */
    private String keypairId;

    /**
     * whether the instance is auto renew or not
     */
    private boolean autoRenew;

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    public String getDedicatedHostId() {
        return dedicatedHostId;
    }

    public void setDedicatedHostId(String dedicatedHostId) {
        this.dedicatedHostId = dedicatedHostId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public ListInstancesRequest withDedicatedHostId(String dedicatedHostId) {
        this.dedicatedHostId = dedicatedHostId;
        return this;
    }

    public ListInstancesRequest withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    /**
     * Configure internalIp for the request.
     *
     * @param internalIp The internal ip address for accessing the instance.
     * @return ListInstancesRequest with internalIp.
     */
    public ListInstancesRequest withInternalIp(String internalIp) {
        this.internalIp = internalIp;
        return this;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListInstancesRequest with specified marker.
     */
    @Override
    public ListInstancesRequest withMarker(String marker) {
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
    public ListInstancesRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    public String getKeypairId() {
        return keypairId;
    }

    public void setKeypairId(String keypairId) {
        this.keypairId = keypairId;
    }

    /**
     * Configure the request with specified keypairId.
     * @param keypairId The specified keypair id.
     * @return ListInstancesRequest with specified keypairId.
     */
    public ListInstancesRequest withKeypairId(String keypairId) {
        this.keypairId = keypairId;
        return this;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    /**
     * Configure the request with specified autoRenew.
     * @param autoRenew Whether the instance is auto renew or not. If <code>true<code/>, it means the instance
     *                  is auto renew.
     * @return ListInstancesRequest with specified autoRenew.
     */
    public ListInstancesRequest withAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListInstancesRequest with credentials.
     */
    @Override
    public ListInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
