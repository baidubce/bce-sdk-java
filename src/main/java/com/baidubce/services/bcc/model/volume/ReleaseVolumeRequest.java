/*
 * Copyright (c) 2014-2019 Baidu.com, Inc. All Rights Reserved
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

/**
 * The request for Releasing volume.
 */
public class ReleaseVolumeRequest extends AbstractBceRequest {

    /**
     * The id of volume.
     */
    private String volumeId;

    /**
     * whether release auto created snapshot of the volume
     */
    private String autoSnapshot;

    /**
     * whether release manually created snapshot of the volume
     */
    private String manualSnapshot;

    /**
     * whether recycle the volume, value can be on, off. default on
     */
    private String recycle;

    public String getAutoSnapshot() {
        return autoSnapshot;
    }

    public void setAutoSnapshot(String autoSnapshot) {
        this.autoSnapshot = autoSnapshot;
    }

    public String getManualSnapshot() {
        return manualSnapshot;
    }

    public void setManualSnapshot(String manualSnapshot) {
        this.manualSnapshot = manualSnapshot;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public ReleaseVolumeRequest withVolumeId(String volumeId) {
        this.volumeId = volumeId;
        return this;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public ReleaseVolumeRequest withRecycle(String recycle) {
        if (!"on".equals(recycle) && !"off".equals(recycle)) {
            throw new IllegalArgumentException("recycle can only be set to 'on' or 'off'");
        }
        this.recycle = recycle;
        return this;
    }

    public ReleaseVolumeRequest withAutoSnapshot(String autoSnapshot) {
        if (!"on".equals(autoSnapshot) && !"off".equals(autoSnapshot)) {
            throw new IllegalArgumentException("autoSnapshot can only be set to 'on' or 'off'");
        }
        this.autoSnapshot = autoSnapshot;
        return this;
    }

    public ReleaseVolumeRequest withManualSnapshot(String manualSnapshot) {
        if (!"on".equals(manualSnapshot) && !"off".equals(manualSnapshot)) {
            throw new IllegalArgumentException("manualSnapshot can only be set to 'on' or 'off'");
        }
        this.manualSnapshot = manualSnapshot;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ReleaseVolumeRequest with credentials.
     */
    @Override
    public ReleaseVolumeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
