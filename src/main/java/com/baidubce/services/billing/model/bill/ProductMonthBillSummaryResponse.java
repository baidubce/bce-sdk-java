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

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;
import lombok.ToString;

/**
 * product month bill summary response
 */
@Data
@ToString
public class ProductMonthBillSummaryResponse extends AbstractBceResponse {

    /**
     * 账单查询起止时间
     * 按月查询
     */
    private String billBeginTime;

    /**
     * 账单查询起止时间
     */
    private String billEndTime;

    /**
     * 当前账户不是财务圈子账户时，返回当前账户accountId
     * 是财务圈子账户时，返回当前账户主账户的accountId
     */
    private String accountId;

    /**
     * 账户登录名，若查询子账户，则为主账户登录名
     */
    private String loginName;

    /**
     * 当前账户不是财务圈子账户时，返回"/"
     * 是财务圈子账户时，返回当前账户的accountId
     */
    private String subAccountId;

    /**
     * 当前账户不是财务圈子账户时，返回"/"
     * 是财务圈子账户时，返回当前账户的登录名
     */
    private String subLoginName;

    /**
     * 当前账户不是财务圈子账户时，返回"/"
     * 是财务圈子账户时，返回当前账户所在组织单元的单元名
     */
    private String ouName;

    /**
     * 产品项详情
     */
    private ProductMonthBillItem data;
}
