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

package com.baidubce.services.billing.model.bill;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;
import lombok.ToString;

/**
 * Created by shizhan@baidu.com
 */
@Data
@ToString
public class CostSplitBillResponse extends AbstractBceResponse {
    /**
     * 账户ID，若查询子账户，则为主账户ID
     */
    protected String accountId;

    /**
     * 账户登录名，若查询子账户，则为主账户登录名
     */
    protected String loginName;

    /**
     * 子账户ID
     */
    protected String subAccountId;

    /**
     * 子账户登录名
     */
    protected String subLoginName;

    /**
     * 子账户所在组织单元名
     */
    protected String ouName;

    /**
     * 账单时间，YYYY-MM
     * 按月查询
     */
    private String billMonth;

    /**
     * 页码，默认值为1
     */
    private Integer pageNo;

    /**
     * 每页数量，默认值：20，最大值：100
     */
    private Integer pageSize;

    /**
     * 当前查询条件总数
     */
    private Integer totalCount;

    /**
     * 预付费分摊账单详情，包含用量包
     */
    private List<ShareBill> bills = new ArrayList<>();
}
