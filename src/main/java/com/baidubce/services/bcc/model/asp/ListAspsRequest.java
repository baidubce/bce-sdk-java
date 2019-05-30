/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.asp;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * The request for listing autoSnapshotPolicies.
 */
public class ListAspsRequest extends ListRequest {
    /**
     * The optional parameter to filter asp to list
     */
    private String aspName;

    /**
     * The optional parameter to filter asp to list
     */
    private String volumeName;

    public String getAspName() {
        return aspName;
    }

    public void setAspName(String aspName) {
        this.aspName = aspName;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    /**
     * Configure the request with specified aspName.
     *
     * @param aspName The optional parameter to filter asp to list
     * @return ListAspsRequest with specified aspName.
     */
    public ListAspsRequest withAspName(String aspName) {
        this.aspName = aspName;
        return this;
    }

    /**
     * Configure the request with specified aspName.
     *
     * @param volumeName The optional parameter to filter asp to list
     * @return ListAspsRequest with specified volumeName.
     */
    public ListAspsRequest withVolumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListAspsRequest with specified marker.
     */
    @Override
    public ListAspsRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListAspsRequest with specified maxKeys.
     */
    @Override
    public ListAspsRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListAspsRequest with credentials.
     */
    @Override
    public ListAspsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
