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

import java.math.BigDecimal;

import lombok.Data;

/**
 * the detail info of prepay share bill
 */
@Data
public class PrepayShareBill {

    /**
     * the month of bill
     */
    private String billMonth;

    /**
     * the type of service
     */
    private String serviceType;

    /**
     * chinese name of service
     */
    private String serviceTypeName;

    /**
     * the id of the resource
     */
    private String instanceId;

    /**
     * the short id of the resource
     */
    private String shortId;

    /**
     * the configuration of the resource
     */
    private String configurationCH;

    /**
     * the tag info of the resource
     */
    private String tag;

    /**
     * the region of the resource
     */
    private String region;

    /**
     * the chinese name of the region
     */
    private String regionName;

    /**
     * the id of the order which is related to the resource
     */
    private String orderId;

    /**
     * the type of order
     */
    private String orderType;

    /**
     * the chinese name of the order type
     */
    private String orderTypeDesc;

    /**
     * the create time of the order
     */
    private String createTime;

    /**
     * the start time of the order
     */
    private String serviceStartTime;

    /**
     * the end time of the order
     */
    private String serviceEndTime;

    /**
     * the time span of the order
     */
    private String serviceTimeSpan;

    /**
     * the capacity of the order(for usage package)
     */
    private String capacity;

    /**
     * the price of the order
     */
    private BigDecimal price;

    /**
     * the finance price
     */
    private BigDecimal financePrice;

    /**
     * the coupon price
     */
    private BigDecimal couponPrice;

    /**
     * the discount coupon price
     */
    private BigDecimal discountCouponPrice;

    /**
     * the cash coupon price
     */
    private BigDecimal cashEquivalentCouponPrice;

    /**
     * the discount price
     */
    private BigDecimal discountPrice;

    /**
     * the price shared in this month
     */
    private BigDecimal sharePrice;

    /**
     * the finance price shared in this month
     */
    private BigDecimal shareFinancePrice;

    /**
     * the coupon price shared in this month
     */
    private BigDecimal shareCouponPrice;

    /**
     * the discount coupon price shared in this month
     */
    private BigDecimal shareDiscountCouponPrice;

    /**
     * the cash coupon price shared in this month
     */
    private BigDecimal shareCashEquivalentCouponPrice;

    /**
     * the discount price shared in this month
     */
    private BigDecimal shareDiscountPrice;

    /**
     * the deduct finance price shared in this month
     */
    private BigDecimal deductShareFinancePrice;

    /**
     * the deduct coupon price shared in this month
     */
    private BigDecimal deductShareCouponPrice;

    /**
     * the deduct discount coupon price shared in this month
     */
    private BigDecimal deductShareDiscountCouponPrice;

    /**
     * the deduct cash coupon price shared in this month
     */
    private BigDecimal deductShareCashEquivalentCouponPrice;

    /**
     * the deduct discount price shared in this month
     */
    private BigDecimal deductShareDiscountPrice;

    /**
     * the controversial flag
     */
    private int controversialItem;

    /**
     * the op time
     */
    private String opTime;

    /**
     * the time of this bill is confirmed
     */
    private String confirmTime;

    /**
     * the amount shared in this month
     */
    private BigDecimal shareAmount;

    /**
     * the time span shared in this month
     */
    private Integer shareDays;

}
