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
package com.baidubce.services.bcc.model.volume;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for Rolling back the specified volume.
 */
public class RollbackVolumeRequest extends AbstractBceRequest {

    /**
     * The id of volume.
     */
    @JsonIgnore
    private String volumeId;

    /**
     * The id which specify where the volume will be rolled back from.
     * If you want to rollback the volume from a customized volume,a id of the volume will be set.
     * If you want to rollback the volume from a system volume,a id of the instance will be set.
     */
    private String snapshotId;

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    /**
     * Configure volumeId for the request.
     *
     * @param volumeId The id of volume which will be rolled back.
     * @return RollbackVolumeRequest with volumeId.
     */
    public RollbackVolumeRequest withVolumeId(String volumeId) {
        this.volumeId = volumeId;
        return this;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * Configure snapshotId for the request.
     *
     * @param snapshotId The id which specify where the volume will be rolled back from.
     *                   If you want to rollback the volume from a customized volume,a id of the volume will be set.
     *                   If you want to rollback the volume from a system volume,a id of the instance will be set.
     * @return RollbackVolumeRequest with snapshotId.
     */
    public RollbackVolumeRequest withSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return RollbackVolumeRequest with credentials.
     */
    @Override
    public RollbackVolumeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
