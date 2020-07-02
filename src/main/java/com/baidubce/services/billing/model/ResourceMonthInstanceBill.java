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

import java.math.BigDecimal;

import lombok.ToString;

/**
 * the detail info of resource month bill
 */
@ToString
public class ResourceMonthInstanceBill {

    /**
     * service provider
     */
    private String vendor;

    /**
     * user id
     */
    private String accountId;

    /**
     * the name of service
     */
    private String serviceType;

    /**
     * chinese name of service
     */
    private String serviceTypeName;

    /**
     * the pay type
     */
    private String productType;

    /**
     * the region of the resource
     */
    private String region;

    /**
     * the id of the resource
     */
    private String instanceId;

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
     * the time of purchase the order
     */
    private String orderPurchaseTime;

    /**
     * the start time of the bill
     */
    private String startTime;

    /**
     * the end time of the bill
     */
    private String endTime;

    /**
     * the configuration of the resource
     */
    private String configurationCH;

    /**
     * the tag info of the resource
     */
    private String tag;

    /**
     * the prepay service time
     */
    private String duration = "";

    /**
     * the charge item of the resource
     */
    private String chargeItem = "";

    /**
     * the charge item of the resource in chinese
     */
    private String chargeItemDesc = "";

    /**
     * the usage amount of the charge item
     */
    private String amount;

    /**
     * the unit of the usage amout
     */
    private String amountUnit = "";

    /**
     * the usage amount of discount
     */
    private String discountAmount;

    /**
     * the unit price of the charge item
     */
    private String unitPrice = "";

    /**
     * the price unit of the charge item
     */
    private String pricingUnit = "";

    /**
     * the discounts of the bill
     */
    private String discountUnit = "";

    /**
     * the tex of the resource
     */
    private BigDecimal tex;

    /**
     * amount of bill
     */
    private BigDecimal originPrice;

    /**
     * amount need to pay
     */
    private BigDecimal financePrice;

    /**
     * amount of cash
     */
    private BigDecimal cash;

    /**
     * amount of rebase
     */
    private BigDecimal rebate;

    /**
     * amount of credit cost
     */
    private BigDecimal creditCost;

    /**
     * amount of redit refund
     */
    private BigDecimal creditRefund;

    /**
     * amount of debt
     */
    private BigDecimal debt;

    /**
     * amount not need to pay
     */
    private BigDecimal noPaidPrice;

    /**
     * amount of coupon
     */
    private BigDecimal couponPrice;

    /**
     * amount of discount conpon
     */
    private BigDecimal discountCouponPrice;

    /**
     * amount of discount
     */
    private BigDecimal discountPrice;

    /**
     * amount of sales
     */
    private BigDecimal sysGold;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeDesc() {
        return orderTypeDesc;
    }

    public void setOrderTypeDesc(String orderTypeDesc) {
        this.orderTypeDesc = orderTypeDesc;
    }

    public String getOrderPurchaseTime() {
        return orderPurchaseTime;
    }

    public void setOrderPurchaseTime(String orderPurchaseTime) {
        this.orderPurchaseTime = orderPurchaseTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getConfigurationCH() {
        return configurationCH;
    }

    public void setConfigurationCH(String configurationCH) {
        this.configurationCH = configurationCH;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getChargeItem() {
        return chargeItem;
    }

    public void setChargeItem(String chargeItem) {
        this.chargeItem = chargeItem;
    }

    public String getChargeItemDesc() {
        return chargeItemDesc;
    }

    public void setChargeItemDesc(String chargeItemDesc) {
        this.chargeItemDesc = chargeItemDesc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPricingUnit() {
        return pricingUnit;
    }

    public void setPricingUnit(String pricingUnit) {
        this.pricingUnit = pricingUnit;
    }

    public String getDiscountUnit() {
        return discountUnit;
    }

    public void setDiscountUnit(String discountUnit) {
        this.discountUnit = discountUnit;
    }

    public BigDecimal getTex() {
        return tex;
    }

    public void setTex(BigDecimal tex) {
        this.tex = tex;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getFinancePrice() {
        return financePrice;
    }

    public void setFinancePrice(BigDecimal financePrice) {
        this.financePrice = financePrice;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public BigDecimal getCreditCost() {
        return creditCost;
    }

    public void setCreditCost(BigDecimal creditCost) {
        this.creditCost = creditCost;
    }

    public BigDecimal getCreditRefund() {
        return creditRefund;
    }

    public void setCreditRefund(BigDecimal creditRefund) {
        this.creditRefund = creditRefund;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    public BigDecimal getNoPaidPrice() {
        return noPaidPrice;
    }

    public void setNoPaidPrice(BigDecimal noPaidPrice) {
        this.noPaidPrice = noPaidPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getDiscountCouponPrice() {
        return discountCouponPrice;
    }

    public void setDiscountCouponPrice(BigDecimal discountCouponPrice) {
        this.discountCouponPrice = discountCouponPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getSysGold() {
        return sysGold;
    }

    public void setSysGold(BigDecimal sysGold) {
        this.sysGold = sysGold;
    }
}
