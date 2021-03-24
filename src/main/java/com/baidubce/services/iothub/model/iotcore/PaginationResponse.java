/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iothub.model.iotcore;

import com.baidubce.services.iothub.model.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Represent the response to pagination data.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginationResponse<T> extends BaseResponse {
    private List<T> data;
    private long total;
    private long pageNo;
    private int pageSize;
    private String order;
    private String orderBy;
}
