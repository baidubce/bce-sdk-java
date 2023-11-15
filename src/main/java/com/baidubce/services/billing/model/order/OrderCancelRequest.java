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

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;
import lombok.ToString;

/**
 * The request of cancel orders.
 * @author Liu Mengbo(liumengbo@baidu.com).
 */
@Data
@ToString
public class OrderCancelRequest extends AbstractBceRequest {

    /**
     * The account id you want to cancel orders.
     * tips: only the master account of organization group can set the parameter to cancel the sub account's order
     */
    private String queryAccountId;

    /**
     * the unique id of orders.
     */
    private List<String> orderIds;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return BindTagsRequest with credentials.
     */
    @Override
    public OrderCancelRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
