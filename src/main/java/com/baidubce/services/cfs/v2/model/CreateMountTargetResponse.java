/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.cfs.v2.model;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMountTargetResponse extends BaseBceResponse {
    /**
     * mount点的ID
     */
    private String mountId;

    /**
     * 分配的服务dns，通过此dns执行文件系统挂载，即可访问服务
     */
    private String domain;

    public void setMountId(String mountId) {
        this.mountId = mountId;
    }

    public String getMountId() {
        return this.mountId;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }

    @Override
    public String toString() {
        return "CreateMountTargetResponse{"
                + "mountId=" + mountId + "\n"
                + "domain=" + domain + "\n"
                + "}";
    }

}