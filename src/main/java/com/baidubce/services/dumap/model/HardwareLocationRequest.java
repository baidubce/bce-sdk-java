/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dumap.model;

import java.util.List;
import java.util.Map;

import com.baidubce.model.GenericAccountRequest;

import lombok.Builder;
import lombok.Data;

/**
 * Represent the request parameters of locating intelligent hardware query.
 */
@Data
@Builder
public class HardwareLocationRequest extends GenericAccountRequest {

    /**
     * Manufacturer identification (eg. baidu).
     */
    private String src;

    /**
     * Product line name (eg. baiduwatch).
     */
    private String prod;

    /**
     * Service version (eg. 1.0).
     */
    private String ver;

    /**
     * Whether to enable trace or not. (default value is false)
     */
    private boolean trace;

    /**
     * The request info body which contains the hardware details.
     */
    private List<Map<String, Object>> body;
}
