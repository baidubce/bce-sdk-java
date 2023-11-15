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
package com.baidubce.services.localdns.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePrivateZoneRequest extends BaseBceRequest {
    /**
     * Zone名称,由两个及其以上的字母或者数字组成,最大长度不能超过240
     */
    private String zoneName;

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    @Override
    public String toString() {
        return "CreatePrivateZoneRequest{"
                + "zoneName=" + zoneName + "\n"
                + "}";
    }

}