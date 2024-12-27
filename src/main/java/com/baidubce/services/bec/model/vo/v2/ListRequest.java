/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vo.v2;

import lombok.Data;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-20 14:22
 * @Version v1.0
 * <p>
 * ListRequest.
 */
@Data
public class ListRequest {

    /**
     * The selection start position.
     */
    private String marker;

    /**
     * A page can contain the maximum value, 1-1000, default 1000.
     */
    private Integer maxKeys;
}