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
package com.baidubce.services.iotdmp.model.ota.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Basic {
    private Integer tid;
    private String tname;
    private String comment;
    private Integer priority;
    private String type;
    private String creator;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("update_at")
    private String updateAt;
    private String status;
    @JsonProperty("test_status")
    private String testStatus;
    @JsonProperty("issueding_at")
    private String issuedingAt;
    @JsonProperty("complete_at")
    private String completeAt;
    @JsonProperty("deleted_at")
    private String deletedAt;
    @JsonProperty("parent_id")
    private String parentId;
}
