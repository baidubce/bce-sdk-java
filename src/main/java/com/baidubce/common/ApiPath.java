/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.common;

/**
 * Api path for v2 client to create request
 *
 * @author zhangquan07
 */
public class ApiPath {
    private String path;

    public ApiPath(String path) {
        this.path = path;
    }

    public String get() {
        return path;
    }

    public ApiPath withPathParameter(String key, String value) {
        return new ApiPath(path.replace("[" + key + "]", value));
    }
}
