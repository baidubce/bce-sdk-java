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

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response for ListInstanceSnapshotRequest.
 */
public class ListInstanceSnapshotResponse extends AbstractBceResponse {

    /**
     * The optional parameter marker specified in the original request to specify
     * where in the results to begin listing.
     */
    private String marker;

    /**
     * Indicates if the listing is truncated, and additional requests need to be made to get more results.
     */
    private Boolean isTruncated;

    /**
     * If this listing is truncated, this is the next marker that should be used in the next request
     * to get the next page of results.
     */
    private String nextMarker;

    /**
     * The optional parameter in the original request to specify the max number of list result to return.
     */
    private Integer maxKeys;

    /**
     * List of instance snapshot detail model.
     */
    private List<InstanceSnapshotModel> instanceSnapshots;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * Returning <code>false</code> to indicate that there is not more result in the next page,
     * otherwise returning <code>true</code> meaning there is more result in next page.
     * <p>
     * NOTE: the getter/setter are named after the field itself on purpose, so that the json property keeps
     * being <code>isTruncated</code> instead of being shortened to <code>truncated</code> by jackson.
     *
     * @return Whether there is more result in the next page.
     */
    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public List<InstanceSnapshotModel> getInstanceSnapshots() {
        return instanceSnapshots;
    }

    public void setInstanceSnapshots(List<InstanceSnapshotModel> instanceSnapshots) {
        this.instanceSnapshots = instanceSnapshots;
    }

    @Override
    public String toString() {
        return "ListInstanceSnapshotResponse{"
                + "marker='" + marker + '\''
                + ", isTruncated=" + isTruncated
                + ", nextMarker='" + nextMarker + '\''
                + ", maxKeys=" + maxKeys
                + ", instanceSnapshots=" + instanceSnapshots
                + '}';
    }
}
