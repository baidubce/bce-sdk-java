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

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRouteRuleRequest extends BaseBceRequest {
    /**
     * 网络实例在云智能网中的身份的ID
     */
    private String attachId;

    /**
     * 路由的目的地址，当前只支持0.0.0.0/0
     */
    private String destAddress;

    /**
     * 路由类型，当前只支持custom
     */
    private String routeType;

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getAttachId() {
        return this.attachId;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getDestAddress() {
        return this.destAddress;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getRouteType() {
        return this.routeType;
    }

    @Override
    public String toString() {
        return "CreateRouteRuleRequest{"
                + "attachId=" + attachId + "\n"
                + "destAddress=" + destAddress + "\n"
                + "routeType=" + routeType + "\n"
                + "}";
    }

}