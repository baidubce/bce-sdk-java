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

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 产品月账单详情
 */
@Data
public class ProductMonthBillItem {
    /**
     * 产品名
     */
    private String serviceType;
    /**
     * 产品中文名
     */
    private String serviceTypeName;
    /**
     * 付费类型
     */
    private String productType;
    /**
     * 账单金额
     */
    private BigDecimal originPrice = BigDecimal.ZERO;
    /**
     * 应付金额（现金等价物部分金额）应付金额=现金+返点+账期待还+账期退款+欠费
     */
    private BigDecimal financePrice = BigDecimal.ZERO;
    /**
     * 现金支付
     */
    private BigDecimal cash = BigDecimal.ZERO;
    /**
     * 返点支付
     */
    private BigDecimal rebate = BigDecimal.ZERO;
    /**
     * 账期未还
     */
    private BigDecimal creditCost = BigDecimal.ZERO;
    /**
     * 账期退款
     */
    private BigDecimal creditRefund = BigDecimal.ZERO;
    /**
     * 欠费金额
     */
    private BigDecimal debt = BigDecimal.ZERO;
    /**
     * 优惠金额 优惠金额=代金券+折扣券+折扣金额+销账
     */
    private BigDecimal noPaidPrice = BigDecimal.ZERO;
    /**
     * 代金券支付
     */
    private BigDecimal couponPrice = BigDecimal.ZERO;
    /**
     * 折扣券支付
     */
    private BigDecimal discountCouponPrice = BigDecimal.ZERO;
    /**
     * 现金等额券支付
     */
    private BigDecimal cashEquivalentCouponPrice = BigDecimal.ZERO;
    /**
     * 折扣金额
     */
    private BigDecimal discountPrice = BigDecimal.ZERO;
    /**
     * 销账金额
     */
    private BigDecimal sysGold = BigDecimal.ZERO;

    /**
     * 待确认金额
     */
    private BigDecimal unconfirmPrice = BigDecimal.ZERO;

    private BigDecimal creditRefundDeduct = BigDecimal.ZERO;

    private BigDecimal deductPrice = BigDecimal.ZERO;

    private BigDecimal deductCash = BigDecimal.ZERO;

    private BigDecimal deductRebate = BigDecimal.ZERO;

    private BigDecimal deductCreditCost = BigDecimal.ZERO;

    private BigDecimal deductCouponPrice = BigDecimal.ZERO;

    private BigDecimal deductDiscountCouponPrice = BigDecimal.ZERO;

    private BigDecimal deductDiscountPrice = BigDecimal.ZERO;

    private BigDecimal deductCashEquivalentCouponPrice = BigDecimal.ZERO;

    private List<ProductMonthBillItem> items;
}
