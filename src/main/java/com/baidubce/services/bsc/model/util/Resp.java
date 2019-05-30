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
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * information in Resp
 * @param <T>
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resp<T> {

    /**
     * 响应状态码
     */
    private int status = RespEnum.SUCCESS.getStatus();

    /**
     * 响应消息
     */
    private String msg = RespEnum.SUCCESS.getMsg();

    /**
     * 响应数据
     */
    private T data;

    public Resp() {
    }

    public Resp(final int status, final String msg) {
        this(status, msg, null);
    }

    public static <T> Resp<T> get(final int status, final String msg) {
        return Resp.get(status, msg, null);
    }

    public static <T> Resp<T> get(final RespEnum respEnum) {
        return Resp.get(respEnum.getStatus(), respEnum.getMsg(), null);
    }

    public static <T> Resp<T> get(final RespEnum respEnum, final T data) {
        return Resp.get(respEnum.getStatus(), respEnum.getMsg(), data);
    }

    public static <T> Resp<T> get(final int status, final String msg, final T data) {
        return new Resp<T>(status, msg, data);
    }

    public static <T> Resp<T> success() {
        return Resp.get(RespEnum.SUCCESS);
    }

    public static <T> Resp<T> success(T data) {
        return Resp.get(RespEnum.SUCCESS, data);
    }
}
