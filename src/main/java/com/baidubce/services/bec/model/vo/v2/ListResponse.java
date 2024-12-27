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

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-21 19:21
 * @Version v1.0
 * <p>
 * ListResponse
 */
@Data
public class ListResponse<T> extends AbstractBceResponse {

    /**
     * The selection start position.
     */
    private String marker;

    /**
     * The next selection start position. true represent that there are other more data， false represent
     * that there are no data already.
     */
    private Boolean isTruncated;

    /**
     * The next selection start position.
     */
    private String nextMarker;

    /**
     * A page can contain the maximum value, 1-1000, default 1000.
     */
    private Integer maxKeys;

    /**
     * Selection result.
     */
    @JsonIgnore
    private List<T> result;
}