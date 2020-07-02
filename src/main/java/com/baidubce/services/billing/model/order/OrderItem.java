/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.billing.model.order;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * the model of order item
 */
@Data
@ToString
public class OrderItem {

    /**
     * the type of service. BCC, CDS, EIP, BCC__CDS, etc.
     */
    private String serviceType;

    /**
     * the type of billing. prepay/postpay/postpay__prepay
     */
    private String productType;

    /**
     * the type of sub product
     * project(*), bandwidth, network,etc.
     * generally for postpay, but project is for prepay
     */
    private String subProductType = "";

    /**
     * the region of the order item
     */
    private String region;

    /**
     * Identification used to distinguish different order items
     */
    private String key;

    /**
     * the configurations of the order item
     */
    private List<String> configurations;

    /**
     * the resource number of the order item
     */
    private int count;

    /**
     * the time length of resources purchased
     */
    private BigDecimal time = BigDecimal.ZERO;

    /**
     * the unit of time
     */
    private String timeUnit;

    /**
     * the model of pricing detail
     */
    private PricingDetail pricingDetail;

    /**
     * the model of payment method
     */
    private PaymentMethod paymentMethod;

    /**
     * the release time of the resource
     */
    private String releaseTime;

    /**
     * the active time of the resource
     */
    private String resourceActiveTime;

    /**
     * the service type of merger purchase order
     */
    private String combinedServiceType;

    /**
     * the fee of the order item
     */
    private Fee itemFee;

    /**
     * the resource ids
     */
    private List<String> resourceIds;

    /**
     * the list of resource short id
     */
    private List<String> shortIds;

    /**
     * the resources' id and fee
     */
    private List<ResourceIdAndFee> resourceIdAndFees;

    /**
     * the start time of the resource
     */
    private String resourceStartTime;

    /**
     * the end time of the resource
     */
    private String resourceEndTime;

    /**
     * the catalog price
     */
    private BigDecimal catalogPrice;

    /**
     * the status of the order item
     */
    private String status;

    /**
     * the information of the related resources
     */
    private List<ResourceMapping> resourceMappings;

}
