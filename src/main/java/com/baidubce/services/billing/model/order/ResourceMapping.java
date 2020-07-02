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

import lombok.Data;
import lombok.ToString;

/**
 * the model of resource information related to the order item
 */
@Data
@ToString
public class ResourceMapping {

    /**
     * the unique key of the resource
     */
    private String key;

    /**
     * the id of resource instance
     */
    private String id;

    /**
     * the short id of the resource instance
     */
    private String shortId;

    /**
     * the status of resource instance
     */
    private String status;

    /**
     * the expire time of resource instance
     */
    private String expiredTime;

}
