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
 * The request for renaming the specified instance snapshots(consistent snapshot groups).
 */
public class RenameInstanceSnapshotRequest extends AbstractBceRequest {

    /**
     * The ids of the instance snapshots which will be renamed.
     */
    private List<String> insnapIds;

    /**
     * The new name for the instance snapshots.
     */
    private String name;

    public List<String> getInsnapIds() {
        return insnapIds;
    }

    public void setInsnapIds(List<String> insnapIds) {
        this.insnapIds = insnapIds;
    }

    /**
     * Configure insnapIds for the request.
     *
     * @param insnapIds The ids of the instance snapshots which will be renamed.
     * @return RenameInstanceSnapshotRequest with insnapIds.
     */
    public RenameInstanceSnapshotRequest withInsnapIds(List<String> insnapIds) {
        this.insnapIds = insnapIds;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Configure name for the request.
     *
     * @param name The new name for the instance snapshots.
     * @return RenameInstanceSnapshotRequest with name.
     */
    public RenameInstanceSnapshotRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return RenameInstanceSnapshotRequest with credentials.
     */
    @Override
    public RenameInstanceSnapshotRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
