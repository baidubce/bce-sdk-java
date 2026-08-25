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
 * The response for CreateInstanceSnapshotRequest.
 */
public class CreateInstanceSnapshotResponse extends AbstractBceResponse {

    /**
     * The id of the instance snapshot(consistent snapshot group) newly created.
     */
    private String inSnapshotId;

    /**
     * The ids of the sub snapshots inside the instance snapshot.
     */
    private List<String> snapshotIds;

    public String getInSnapshotId() {
        return inSnapshotId;
    }

    public void setInSnapshotId(String inSnapshotId) {
        this.inSnapshotId = inSnapshotId;
    }

    public List<String> getSnapshotIds() {
        return snapshotIds;
    }

    public void setSnapshotIds(List<String> snapshotIds) {
        this.snapshotIds = snapshotIds;
    }

    @Override
    public String toString() {
        return "CreateInstanceSnapshotResponse{"
                + "inSnapshotId='" + inSnapshotId + '\''
                + ", snapshotIds=" + snapshotIds
                + '}';
    }
}
