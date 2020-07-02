/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
 * The request fot getting the list of no-charge instance.
 */
public class ListGetInstanceNoChargeRequest extends ListRequest {

    /**
     * The identified internal ip of instance.
     */
    private String internalIp;

    /**
     * The id of the keypair
     */
    private String keypairId;

    /**
     * the name of available zone
     */
    private String zoneName;

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    /**
     * Configure internalIp for the request.
     *
     * @param internalIp The internal ip address for accessing the instance.
     * @return ListGetInstanceNoChargeRequest with internalIp.
     */
    public ListGetInstanceNoChargeRequest withInternalIp(String internalIp) {
        this.internalIp = internalIp;
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
     *
     * @param keypairId The specified keypair id.
     * @return ListGetInstanceNoChargeRequest with specified keypairId.
     */
    public ListGetInstanceNoChargeRequest withKeypairId(String keypairId) {
        this.keypairId = keypairId;
        return this;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * Configure the request with specified zoneName.
     *
     * @param zoneName The specified zoneName.
     * @return ListGetInstanceNoChargeRequest with specified zoneName.
     */
    public ListGetInstanceNoChargeRequest withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListGetInstanceNoChargeRequest with specified marker.
     */
    @Override
    public ListGetInstanceNoChargeRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListGetInstanceNoChargeRequest with specified maxKeys.
     */
    @Override
    public ListGetInstanceNoChargeRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListGetInstanceNoChargeRequest with credentials.
     */
    @Override
    public ListGetInstanceNoChargeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
