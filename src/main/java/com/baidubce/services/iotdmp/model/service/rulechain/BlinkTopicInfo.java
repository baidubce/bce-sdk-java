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
package com.baidubce.services.iotdmp.model.service.rulechain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BlinkTopicInfo {
    PROPERTY_POST,
    PROPERTY_BATCH,
    PROPERTY_UPLOAD,
    PROPERTY_DESIRED,
    PROPERTY_GET,
    PROPERTY_SNAPSHOT,
    PROPERTY_DELTA,
    PROPERTY_DOCUMENT,
    PROPERTY_DELETE,
    EVENT_POST,
    EVENT_BATCH,
    EVENT_UPLOAD,
    PROPERTY_INVOKE,
    COMMAND_INVOKE,
    SERVICE_INVOKE,
    CLOUD_TO_DEVICE,
    DEVICE_TO_CLOUD,
    LIFECYCLE_UPDATE,
    LIFECYCLE_POST,
    RAW_D2C,
    RAW_C2D
}
