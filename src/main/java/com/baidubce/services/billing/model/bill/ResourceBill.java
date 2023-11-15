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

import java.util.List;

import lombok.Data;

/**
 * the bill of resource
 */
@Data
public class ResourceBill extends AmountDetail {

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
     * the id of the resource
     */
    private String instanceId;

    /**
     * the short id of the resource
     */
    private String shortId;

    /**
     * the region of the resource
     */
    private String region;

    /**
     * the tag info of the resource
     */
    private String tag;

    /**
     * the detail info if the resource is a postpay resource
     */
    private List<ResourceBillPostpayDetail> postpayDetails;

    /**
     * the detail info if the resource is a pretpay resource
     */
    private List<ResourceBillPrepayDetail> prepayDetails;

}
