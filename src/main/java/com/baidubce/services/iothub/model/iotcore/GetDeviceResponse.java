/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iothub.model.iotcore;

import com.baidubce.services.iothub.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Represent the response to get device.
 */
@Getter
@Setter
@ToString
public class GetDeviceResponse extends BaseResponse {

    private String name;

    private String desc;

    private Long createTs;

    private Long updateTs;

    private String authType;

    private String templateId;

    private String groupKey;

    private Map<String, String> tags;

}
