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
package com.baidubce.services.billing.model.order;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;
import lombok.ToString;

/**
 * The response of pay order.
 * @author Liu Mengbo(liumengbo@baidu.com).
 */
@Data
@ToString
public class OrderPaymentResponse extends AbstractBceResponse {

    /**
     * Whether the order is payment successfully.
     */
    private Boolean success;
}