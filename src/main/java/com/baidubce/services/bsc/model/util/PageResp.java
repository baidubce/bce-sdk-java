/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bsc.model.util;

import com.baidubce.services.bsc.model.constant.RespEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * information in pageResp
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResp<T> {
    /**
     * 响应状态码
     */
    private int status = RespEnum.SUCCESS.getStatus();
    /**
     * 响应消息
     */
    private String msg = RespEnum.SUCCESS.getMsg();
    /**
     * 当前页码
     */
    private int page;
    /**
     * 每页展示数量
     */
    private int size;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 总页数
     */
    private long pageTotal;
    /**
     * 响应带回的具体数据；
     */
    private List<T> items;

    public PageResp() {
    }
    public PageResp(RespEnum respEnum) {
        this.status = respEnum.getStatus();
        this.msg = respEnum.getMsg();
    }
}
