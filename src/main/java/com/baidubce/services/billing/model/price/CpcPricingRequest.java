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
package com.baidubce.services.billing.model.price;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

/**
 * request model for query price
 */
@Data
public class CpcPricingRequest extends AbstractBceRequest {

    /**
     * the type of service
     */
    private String serviceType;

    /**
     * the sale type
     */
    private String productType;

    /**
     * the region of the price object
     */
    private String region;

    /**
     * the configurations of price object
     */
    private Flavor flavor;

    /**
     * the count of price object
     */
    private int count;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return BindTagsRequest with credentials.
     */
    @Override
    public CpcPricingRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
