/*
 * Copyright (c) 2019-2020 Baidu.com, Inc. All Rights Reserved
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
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * The request for detaching autoSnapshotPolicy.
 */
public class DetachAspRequest extends AbstractBceRequest {
    /**
     * The id of asp to detach.
     */
    private String aspId;

    /**
     * ids of volumes which will be detach.
     */
    private List<String> volumeIds;

    public String getAspId() {
        return aspId;
    }

    public void setAspId(String aspId) {
        this.aspId = aspId;
    }

    public List<String> getVolumeIds() {
        return volumeIds;
    }

    public void setVolumeIds(List<String> volumeIds) {
        this.volumeIds = volumeIds;
    }

    public DetachAspRequest withAspId(String aspId) {
        this.aspId = aspId;
        return this;
    }

    public DetachAspRequest withVolumeIds(List<String> volumeIds) {
        this.volumeIds = volumeIds;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return AttachAspRequest with credentials.
     */
    @Override
    public DetachAspRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
