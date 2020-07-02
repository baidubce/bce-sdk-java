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
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * the model of payment method
 */
@Data
@ToString
public class PaymentMethod {

    /**
     * the rate of discount
     */
    private Integer discountRate = 100;

    /**
     * the coupon list
     */
    private List<CouponInfo> coupons = new ArrayList<CouponInfo>();

    /**
     * the discount coupon list
     */
    private List<CouponInfo> discountCoupons = new ArrayList<CouponInfo>();

    @Data
    public static class CouponInfo {
        /**
         * the id of the coupon
         */
        private String couponId;

        /**
         * the amount of the amount
         */
        private BigDecimal amount;
    }

}
