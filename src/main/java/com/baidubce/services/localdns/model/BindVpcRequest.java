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

import java.util.List;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BindVpcRequest extends BaseBceRequest {
    /**
     * region
     */
    private String region;

    /**
     * vpcIds
     */
    private List<String> vpcIds;

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return this.region;
    }

    public void setVpcIds(List<String> vpcIds) {
        this.vpcIds = vpcIds;
    }

    public List<String> getVpcIds() {
        return this.vpcIds;
    }

    @Override
    public String toString() {
        return "BindVpcRequest{"
                + "region=" + region + "\n"
                + "vpcIds=" + vpcIds + "\n"
                + "}";
    }

}