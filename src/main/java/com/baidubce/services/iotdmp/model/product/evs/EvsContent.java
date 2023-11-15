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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvsContent {
    private String type;
    private UpstreamAuth upstreamAuth;
    private String description;
    private Recording recording;
    private DownstreamAuth downstreamAuth;
    private String status;
    private Edge edge;
    private String createTime;
    private TimeShift timeShift;
    private Long deviceId;
    private Long spaceId;
    private List<Domains> domains;
    private String spaceName;
    private String deviceStreamId;
    private String deviceName;
    private Thumbnail thumbnail;
}
