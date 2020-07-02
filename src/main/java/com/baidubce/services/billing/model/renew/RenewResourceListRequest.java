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

package com.baidubce.services.billing.model.renew;

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;
import lombok.ToString;

/**
 * the request to query auto renew resource list
 */
@Data
@ToString
public class RenewResourceListRequest extends AbstractBceRequest {

    /**
     * the service type
     */
    private String serviceType;

    /**
     * region
     */
    private String region;

    /**
     * the days that the resource will expired
     */
    private Integer expiredDays;

    /**
     * the list of instance id or short id
     */
    private List<String> shortOrInstanceIds;

    /**
     * the account id you want to query
     * tips: only the master account of organization group can set the parameter to query the sub account's bill
     */
    private String queryAccountId;

    /**
     * page number
     */
    private Integer pageNo;

    /**
     * page size
     */
    private Integer pageSize;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return BindTagsRequest with credentials.
     */
    @Override
    public RenewResourceListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
