/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.eni.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * The request for attach/detach Elastic Network Interface Card instance.
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EniInstanceOperateRequest extends EniOperateRequest {

    /**
     * The cloud host ID to be operated
     */
    private String instanceId;

    @Builder(builderMethodName = "EniInstanceOperateRequestBuilder")
    public EniInstanceOperateRequest(String clientToken, String action, String eniId, String instanceId) {
        super(clientToken, action, eniId);
        this.instanceId = instanceId;
    }

}
