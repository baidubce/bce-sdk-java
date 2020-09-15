/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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

/**
 * the detail list info of resource bills
 */
@Data
public class ResourceBillListQueryResponse extends AbstractBceResponse {

    /**
     * the month of bill
     */
    String billMonth;

    /**
     * the begin time of bill, query by day
     */
    private String beginTime;

    /**
     * the end time of bill, query by day
     */
    private String endTime;

    /**
     * the account id
     */
    String accountId;

    /**
     * the login name of the account id
     */
    String loginName;

    /**
     * the sub account id
     */
    String subAccountId;

    /**
     * the login name of sub account id
     */
    String subLoginName;

    /**
     * the unit name of the sub account
     */
    String ouName;

    /**
     * page number
     */
    Integer pageNo;

    /**
     * page size
     */
    Integer pageSize;

    /**
     * the total count of the bills
     */
    Integer totalCount;

    /**
     * resource month instance bill
     */
    List<ResourceBill> bills = new ArrayList<ResourceBill>();

}
