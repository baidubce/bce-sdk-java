/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp.model.product.evs;

import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsRecordingConfig;
import com.baidubce.services.iotdmp.model.device.evs.EvsSpaceType;
import com.baidubce.services.iotdmp.model.device.evs.EvsThumbnailConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateEvsSpaceRequest extends GenericAccountRequest {

    private String spaceName;
    private String description;
    @NonNull
    @Builder.Default
    private EvsSpaceType type = EvsSpaceType.RTMP;
    private List<EvsSpaceDomain> domains;
    private CommonEvsAuthInfo upstreamAuth;
    private CommonEvsAuthInfo downstreamAuth;
    private EvsRecordingConfig recording;
    private EvsThumbnailConfig thumbnail;
    private EvsTimeShiftConfig timeShift;
    private EvsCallbackConfig callback;
    private EvsGbProperties gbProperties;
    private Long edgeId;

    public void setBucket(String bucket) {
        if (recording != null && recording.getEnabled()) {
            recording.setBucket(bucket);
        }

        if (thumbnail != null && thumbnail.getEnabled()) {
            thumbnail.setBucket(bucket);
        }
    }

    public CreateEvsSpaceRequest(EvsSpaceType type) {
        this.type = type;
    }
}
