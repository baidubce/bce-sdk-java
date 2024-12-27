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
package com.baidubce.services.bec.model.vm;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * paged api list, for brief info
 */
@Data
public class LogicPageResultResponse<T> extends AbstractBceResponse {

    /**
     * Paged api list, for brief info.
     */
    private List<T> result;

    /**
     * The order list.
     */
    private List<OrderModel> orders;

    /**
     * The order by info.
     */
    private String orderBy;

    /**
     * The order info, eg asc, desc.
     */
    private String order;

    /**
     * page number
     */
    private int pageNo;

    /**
     * Page size.
     */
    private int pageSize;

    /**
     * Total number of results.
     */
    private Integer totalCount;

    /**
     * The order information.
     */
    @Data
    public class OrderModel {

        /**
         * The order info.
         */
        private String order;

        /**
         * The order by info.
         */
        private String orderBy;
    }
}
