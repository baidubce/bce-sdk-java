// Copyright (C) 2025 Baidu Inc. All rights reserved.

package com.baidubce.services.billing.model.finance;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

/**
 * @author Sun Qiming(sunqiming01@baidu.com).
 */
@Data
public class UnionExpireDayQueryResponse extends AbstractBceResponse {
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
     * 统一到期日
     */
    private int unionExpireDay;
}
