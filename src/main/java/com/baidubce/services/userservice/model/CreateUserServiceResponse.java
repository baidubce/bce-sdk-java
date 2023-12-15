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
package com.baidubce.services.userservice.model;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserServiceResponse extends BaseBceResponse {
    /**
     * 服务发布点的域名，使用此域名绑定服务网卡
     */
    private String service;

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return this.service;
    }

    @Override
    public String toString() {
        return "CreateUserServiceResponse{"
                + "service=" + service + "\n"
                + "}";
    }

}