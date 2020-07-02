/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.common;

import java.util.Map;

import com.baidubce.http.HttpMethodName;

/**
 * Api info for v2 client to create request
 *
 * @author zhangquan07
 */
public class ApiInfo {
    /**
     * Api method name
     */
    private HttpMethodName method;
    /**
     * Api path, use [] to represents path variable.
     */
    private ApiPath path;
    /**
     * Api query variables.
     */
    private Map<String, String> queries;
    /**
     * Api header variables.
     */
    private Map<String, String> headers;

    public HttpMethodName getMethod() {
        return method;
    }

    public ApiPath getPath() {
        return path;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public ApiInfo(HttpMethodName method, ApiPath path, Map<String, String> queries,
                   Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.queries = queries;
        this.headers = headers;
    }

    public ApiInfo(ApiInfo other) {
        this.method = other.getMethod();
        this.path = other.getPath();
        this.queries = other.getQueries();
        this.headers = other.getHeaders();
    }
}
