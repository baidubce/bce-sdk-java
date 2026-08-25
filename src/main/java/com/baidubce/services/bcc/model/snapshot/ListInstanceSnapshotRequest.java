/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model.snapshot;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * The request for listing the instance snapshots(consistent snapshot groups) owned by the authenticated user.
 * <p>
 * All the parameters are carried by the json payload instead of the query string, so this request does not extend
 * {@link com.baidubce.model.ListRequest} to avoid sending the default {@code maxKeys} in the payload.
 */
public class ListInstanceSnapshotRequest extends AbstractBceRequest {

    /**
     * The optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     */
    private String marker;

    /**
     * The optional parameter to specify the max number of list result to return.
     */
    private Integer maxKeys;

    /**
     * The optional parameter to filter the result by the ids of instances.
     */
    private List<String> instanceIds;

    /**
     * The optional parameter to filter the result by the ids of instance snapshots.
     */
    private List<String> instanceSnapshotIds;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * Configure marker for the request.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListInstanceSnapshotRequest with marker.
     */
    public ListInstanceSnapshotRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    /**
     * Configure maxKeys for the request.
     *
     * @param maxKeys The optional parameter to specify the max number of list result to return.
     * @return ListInstanceSnapshotRequest with maxKeys.
     */
    public ListInstanceSnapshotRequest withMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
        return this;
    }

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    /**
     * Configure instanceIds for the request.
     *
     * @param instanceIds The ids of instances used to filter the result.
     * @return ListInstanceSnapshotRequest with instanceIds.
     */
    public ListInstanceSnapshotRequest withInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
        return this;
    }

    public List<String> getInstanceSnapshotIds() {
        return instanceSnapshotIds;
    }

    public void setInstanceSnapshotIds(List<String> instanceSnapshotIds) {
        this.instanceSnapshotIds = instanceSnapshotIds;
    }

    /**
     * Configure instanceSnapshotIds for the request.
     *
     * @param instanceSnapshotIds The ids of instance snapshots used to filter the result.
     * @return ListInstanceSnapshotRequest with instanceSnapshotIds.
     */
    public ListInstanceSnapshotRequest withInstanceSnapshotIds(List<String> instanceSnapshotIds) {
        this.instanceSnapshotIds = instanceSnapshotIds;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListInstanceSnapshotRequest with credentials.
     */
    @Override
    public ListInstanceSnapshotRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
