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

import lombok.Data;

/**
 * share bill object
 */
@Data
public class ShareBill {

    /**
     * 账单时间，yyyy-MM
     * 按月查询
     */
    private String billMonth;

    /**
     * 账单时间，yyyy-MM-dd
     * 按天查询
     */
    private String billDay;

    /**
     * 产品付费类型
     */
    private String productType;

    /**
     * 产品付费类型中文
     */
    private String productTypeName;

    /**
     * 产品名称
     */
    private String serviceType;

    /**
     * 产品展示名称，产品中心后续都用展示名
     */
    private String serviceTypeName;

    /**
     * 实例信息
     */
    private String instanceId;

    /**
     * shortId
     */
    private String shortId;

    /**
     * 产品中文配置，部分后付费有
     * e.m. ["类型：快照","size：1G"]
     * /n分隔符拼接成字符串
     */
    private String configurationCH;

    /**
     * 计费项英文名，仅后付费才有
     */
    private String chargeItem;

    /**
     * 计费项中文名字，后付费才有
     */
    private String chargeItemDesc;

    /**
     * 资源tag信息，根据instanceId查询资源绑定的tag
     */
    private String tag;

    /**
     * 区域，bj
     */
    private String region;

    /**
     * 区域中文名称，华北-北京等
     */
    private String regionName;

    /**
     * 订单ID (后付费为空)
     */
    private String orderId;

    /**
     * 订单类型 (后付费为空)
     */
    private String orderType;

    /**
     * 订单类型，中文表示 (后付费为空)
     */
    private String orderTypeDesc;

    /**
     * 订单创建时间，utc时间 (后付费为空)
     */
    private String createTime;

    /**
     * 订单服务开始时间，utc时间 （后付费取资源账单开始时间）
     */
    private String serviceStartTime;

    /**
     * 订单服务结束时间，utc时间（后付费取资源账单结束时间）
     */
    private String serviceEndTime;

    /**
     * 订单资源服务时长 （只有CPT2有）
     */
    private String serviceTimeSpan;

    /**
     * 用量包总量（只有存储包、用量包有）
     */
    private String capacity;

    // 总量费用信息，8个金额
    /**
     * 账单原始金额 （后付费与分摊一致）
     */
    private BigDecimal price;

    /**
     * 原始应付金额（后付费与分摊一致）
     */
    private BigDecimal financePrice;

    /**
     * 原始目录金额（后付费与分摊一致）
     */
    private BigDecimal catalogPrice;

    /**
     * 代金券金额（后付费与分摊一致）
     */
    private BigDecimal couponPrice;

    /**
     * 折扣券金额（后付费与分摊一致）
     */
    private BigDecimal discountCouponPrice;

    /**
     * 现金券金额（后付费与分摊一致）
     */
    private BigDecimal cashEquivalentCouponPrice;

    /**
     * 折扣金额（后付费与分摊一致）
     */
    private BigDecimal discountPrice;

    /**
     * 销账金额（后付费与分摊一致）
     */
    private BigDecimal sysGoldPrice;

    //  本月分摊信息，8个金额加用量加时长
    /**
     * 本月（天）分摊价格
     */
    private BigDecimal sharePrice;

    /**
     * 本月（天）分摊应付价格
     */
    private BigDecimal shareFinancePrice;

    /**
     * 本月（天）分摊目录价格
     */
    private BigDecimal shareCatalogPrice;

    /**
     * 本月（天）分摊代金券
     */
    private BigDecimal shareCouponPrice;

    /**
     * 本月（天）分摊折扣券
     */
    private BigDecimal shareDiscountCouponPrice;

    /**
     * 本月（天）分摊现金券
     */
    private BigDecimal shareCashEquivalentCouponPrice;

    /**
     * 本月（天）分摊折扣金额
     */
    private BigDecimal shareDiscountPrice;

    /**
     * 本月（天）分摊销账金额
     */
    private BigDecimal shareSysGoldPrice;

    /**
     * 本月（天）分摊用量（包括量包和所有后付费计费项）
     */
    private BigDecimal shareAmount;

    /**
     * 用量单位
     */
    private String amountUnit;

    /**
     * 本月（天）分摊时长(天) （只用于CPT2、Project计费项）
     */
    private Integer shareDays;
}
