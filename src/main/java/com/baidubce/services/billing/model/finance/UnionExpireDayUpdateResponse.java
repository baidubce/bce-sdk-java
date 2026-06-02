// Copyright (C) 2025 Baidu Inc. All rights reserved.

package com.baidubce.services.billing.model.finance;

import com.baidubce.model.AbstractBceResponse;

/**
 * @author Sun Qiming(sunqiming01@baidu.com).
 */
public class UnionExpireDayUpdateResponse extends AbstractBceResponse {
    /**
     * 账户ID，若查询子账户，则为主账户ID
     */
    private String accountId;

    /**
     * 账户登录名，若查询子账户，则为主账户登录名
     */
    private String loginName;

    /**
     * 子账户ID
     */
    private String subAccountId;

    /**
     * 子账户登录名
     */
    private String subLoginName;

    /**
     * 子账户所在组织单元名
     */
    private String ouName;

    /**
     * 更新续费统一到期日是否成功
     */
    private Boolean success;

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
