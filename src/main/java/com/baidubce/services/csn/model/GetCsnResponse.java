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

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCsnResponse extends BaseBceResponse {
    /**
     * 云智能网的名称
     */
    private String name;

    /**
     * 云智能网的描述
     */
    private String description;

    /**
     * 云智能网的ID
     */
    private String csnId;

    /**
     * 云智能网的状态
     */
    private String status;

    /**
     * 云智能网加载的网络实例数量
     */
    private Integer instanceNum;

    /**
     * 云智能网绑定的带宽包数量
     */
    private Integer csnBpNum;

    /**
     * 云智能网的标签列表
     */
    private List<Tag> tags;
}