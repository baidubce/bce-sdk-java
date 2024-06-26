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

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.VolumeAttachmentModel;
import lombok.Data;

import java.util.List;

/**
 * The response for AttachVolumeRequest.
 */
@Data
public class AttachVolumeResponse extends AbstractBceResponse {

    /**
     * The detail model which describe where the volume will attach to.
     */
    private VolumeAttachmentModel volumeAttachment;

    private List<String> warningList;

    public VolumeAttachmentModel getVolumeAttachment() {
        return volumeAttachment;
    }

    public void setVolumeAttachment(VolumeAttachmentModel volumeAttachment) {
        this.volumeAttachment = volumeAttachment;
    }

    @Override
    public String toString() {
        return "AttachVolumeResponse{"
                + "volumeAttachment=" + volumeAttachment
                + '}';
    }
}
