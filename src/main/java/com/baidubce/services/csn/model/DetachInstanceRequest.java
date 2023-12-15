/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.csn.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetachInstanceRequest extends BaseBceRequest {
    /**
     * 加载的实例类型，取值 [ vpc | channel | bec_vpc ]，分别表示私有网络、专线通道、边缘网络
     */
    private String instanceType;

    /**
     * 加载的实例ID
     */
    private String instanceId;

    /**
     * 加载的实例所属的region
     */
    private String instanceRegion;

    /**
     * 跨账号加载网络实例场景下，网络实例所属账号的ID
     */
    private String instanceAccountId;

}