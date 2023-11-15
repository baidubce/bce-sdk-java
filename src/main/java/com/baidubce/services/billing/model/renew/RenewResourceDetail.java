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

import lombok.Data;

/**
 *  renew resource detail model
 */
@Data
public class RenewResourceDetail {
    /**
     * 区域
     */
    private String region;

    /**
     * 产品名称
     */
    private String serviceType;

    /**
     * 资源 name
     */
    private String resourceName;

    /**
     * 资源短 id
     */
    private String shortId;

    /**
     * 资源过期时间
     */
    private String expiredTime;

    /**
     * 实例名称，即客户自定义名称
     */
    private String instanceName;

    /**
     * 下次自动续费时长
     */
    private Integer renewTime;

    /**
     * 下次自动续费时长单位：MONTH、YEAR、DAY
     */
    private String renewTimeUnit;

    /**
     * 是否允许续费
     */
    private Boolean enableRenew;

    /**
     * 自动续费id
     */
    private String autoRenewUuid;

    /**
     * 禁止续费的原因
     * 如，已存在未完成订单，禁止续费、该资源已封禁、禁止续费
     */
    private String disableRenewReason;
}
