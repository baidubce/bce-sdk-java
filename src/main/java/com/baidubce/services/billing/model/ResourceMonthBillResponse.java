/*
 * Copyright (c) 2014-2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.billing.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * the detail info of resource month bills
 */
public class ResourceMonthBillResponse extends AbstractBceResponse {
    /**
     * the month of bill
     */
    String billMonth;

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
    List<ResourceMonthInstanceBill> bills = new ArrayList<ResourceMonthInstanceBill>();

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSubAccountId() {
        return subAccountId;
    }

    public void setSubAccountId(String subAccountId) {
        this.subAccountId = subAccountId;
    }

    public String getSubLoginName() {
        return subLoginName;
    }

    public void setSubLoginName(String subLoginName) {
        this.subLoginName = subLoginName;
    }

    public String getOuName() {
        return ouName;
    }

    public void setOuName(String ouName) {
        this.ouName = ouName;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<ResourceMonthInstanceBill> getBills() {
        return bills;
    }

    public void setBills(List<ResourceMonthInstanceBill> bills) {
        this.bills = bills;
    }
}
