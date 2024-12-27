/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.billing.model.bill;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

/**
 * Created by shizhan@baidu.com
 */
@Data
public class CostSplitBillRequest extends AbstractBceRequest {
    /**
     * 选传，账单月份，yyyy-MM
     * 最早为2020-04
     * month 和 【startDay，endTime】组合二选一，优先使用 month
     */
    private String month;

    /**
     * 选传，账单起始日，yyyy-MM-dd
     * 【startDay，endTime】需要同时选传
     */
    private String startDay;

    /**
     * 选传，账单截止日，yyyy-MM-dd
     * 【startDay，endTime】需要同时选传
     */
    private String endDay;

    /**
     * 计费类型：all/prepay/postpay，分别表示全量/预付费/后付费
     * 缺省时为 all
     */
    private String productType;

    /**
     * 产品名称
     * 缺省为所有产品
     */
    private String serviceType;

    /**
     * 非必传，查询账户ID，默认为登录账户
     * 若非登录账户，则必须是主账户的子账户，且该子账户加入了财务圈
     */
    private String queryAccountId;

    private String instanceId;

    /**
     * 分页查询的页数，从1开始计数
     * 缺省值为1
     */
    private Integer pageNo = 1;

    /**
     * 每页包含的最大数量，最大数量通常不超过1000
     * 缺省值为100
     */
    private Integer pageSize = 100;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }
}
