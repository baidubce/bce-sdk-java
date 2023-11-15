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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;
import lombok.ToString;

/**
 * the request of query order list
 */
@Data
@ToString
public class OrderListRequest extends AbstractBceRequest {

    /**
     * the unique id of order
     */
    private String uuid;

    /**
     * the account id you want to query
     * tips: only the master account of organization group can set the parameter to query the sub account's order
     */
    private String queryAccountId;

    /**
     * the type of order
     */
    private String orderType;

    /**
     * the status of order
     */
    private String status;

    /**
     * the type of service. BCC, CDS, EIP, BCC__CDS, etc.
     */
    private String serviceType;

    /**
     * the type of billing. prepay/postpay/postpay__prepay
     */
    private String productType;

    /**
     * the start time of the create time of order
     */
    private String startTime;

    /**
     * the end time of the create time of order
     */
    private String endTime;

    /**
     * whether the order is payed automatically
     */
    private Boolean autoPayed;

    /**
     * whether the order is created by auto-renew service
     */
    private Boolean autoRenewOrder;

    /**
     * page number, begin with 1
     */
    private Integer pageNo;

    /**
     * page size , max(100)
     */
    private Integer pageSize;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return BindTagsRequest with credentials.
     */
    @Override
    public OrderListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
