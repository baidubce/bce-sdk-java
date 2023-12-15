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
public class CreatePropagationRequest extends BaseBceRequest {
    /**
     * 网络实例在云智能网中的身份的ID
     */
    private String attachId;

    /**
     * 描述
     */
    private String description;

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getAttachId() {
        return this.attachId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "CreatePropagationRequest{"
                + "attachId=" + attachId + "\n"
                + "description=" + description + "\n"
                + "}";
    }

}