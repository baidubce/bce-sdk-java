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

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;
import lombok.ToString;

/**
 * the response to query auto renew resource list
 */
@Data
@ToString
public class RenewResourceResponse extends AbstractBceResponse {

    /**
     * the account id
     */
    private String accountId;

    /**
     * the login name of the account id
     */
    private String loginName;

    /**
     * the sub account id
     */
    private String subAccountId;

    /**
     * the login name of sub account id
     */
    private String subLoginName;

    /**
     * the unit name of the sub account
     */
    private String ouName;

    /**
     * page number
     */
    private Integer pageNo;

    /**
     * page size
     */
    private Integer pageSize;

    /**
     * the total count of the bills
     */
    private Integer totalCount;

    /**
     * auto renew resource list
     */
    private List<RenewResource> resources = new ArrayList<RenewResource>();

}
