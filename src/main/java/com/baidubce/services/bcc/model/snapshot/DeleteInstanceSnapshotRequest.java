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
 * The request for deleting the specified instance snapshots(consistent snapshot groups).
 */
public class DeleteInstanceSnapshotRequest extends AbstractBceRequest {

    /**
     * The ids of the instance snapshots which will be deleted.
     */
    private List<String> deleteInsnapIds;

    public List<String> getDeleteInsnapIds() {
        return deleteInsnapIds;
    }

    public void setDeleteInsnapIds(List<String> deleteInsnapIds) {
        this.deleteInsnapIds = deleteInsnapIds;
    }

    /**
     * Configure deleteInsnapIds for the request.
     *
     * @param deleteInsnapIds The ids of the instance snapshots which will be deleted.
     * @return DeleteInstanceSnapshotRequest with deleteInsnapIds.
     */
    public DeleteInstanceSnapshotRequest withDeleteInsnapIds(List<String> deleteInsnapIds) {
        this.deleteInsnapIds = deleteInsnapIds;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return DeleteInstanceSnapshotRequest with credentials.
     */
    @Override
    public DeleteInstanceSnapshotRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
