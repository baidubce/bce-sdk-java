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

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;
import lombok.ToString;

/**
 * the response of query order list
 */
@Data
@ToString
public class OrderListResponse extends AbstractBceResponse {

    /**
     * page number, min(1)
     */
    Integer pageNo;

    /**
     * page number, begin with 1
     */
    Integer pageSize;

    /**
     * the total count of the query
     */
    Integer totalCount;

    /**
     * the order list
     */
    private List<Order> orders = new ArrayList<Order>();

}
