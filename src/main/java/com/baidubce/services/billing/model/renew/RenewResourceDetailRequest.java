/*
 * Copyright (c) 2022 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.billing.model.renew;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;
import lombok.ToString;

/**
 * request model for renew resource detail list
 */
@Data
@ToString
public class RenewResourceDetailRequest extends AbstractBceRequest {
    /**
     * 最近多少天过期，<0 为不限制
     */
    @Min(-1)
    @Max(90)
    private int daysToExpiration;

    /**
     * 区域
     */
    @NotNull
    private String region;

    /**
     * 产品名称
     */
    @NotNull
    private String serviceType;

    /**
     * 待查询实例的 ID（可能是长 id，也可能是短 id）
     */
    private String instanceId;

    /**
     * 实例名称
     */
    private String instanceName;

    /**
     * 账户id
     */
    private String accountId;

    /**
     * 过滤已开通自动续费的规则
     */
    private String autoRenewStatus;

    /**
     * 页码
     */
    @Min(1)
    private Integer pageNo = 1;

    /**
     * 页大小
     */
    @Min(1)
    @Max(100)
    private Integer pageSize = 100;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return BindTagsRequest with credentials.
     */
    @Override
    public RenewResourceDetailRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
