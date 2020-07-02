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
 * the model of order
 */
@Data
@ToString
public class Order {
    /**
     * the unique id of order
     */
    private String uuid;

    /**
     * the type of order
     */
    private String type;

    /**
     * user id
     */
    private String accountId;

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
    private String subProductType;

    /**
     * the order item list
     */
    private List<OrderItem> orderItems;

    /**
     * the fee of the order
     */
    private Fee orderFee;

    /**
     * the price of the order
     */
    private BigDecimal price;

    /**
     * the status of the order
     */
    private String status;

    /**
     * the create time of order
     */
    private String createTime;

    /**
     * the time of paying the order
     */
    private String purchaseTime;

    /**
     * the last time of updating the order
     */
    private String updateTime;

    /**
     * the active time of the order for SHIFT_CHARGE order
     */
    private String activeTime;

    /**
     * the list of resource id
     */
    private List<String> resourceIds;

    /**
     * the list of resource short id
     */
    private List<String> shortIds;

    /**
     * the channel of paying the order
     */
    private String payChannel;

}
