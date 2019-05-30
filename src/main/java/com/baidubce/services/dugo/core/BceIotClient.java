/*
 * Copyright 2018 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.core;

import com.baidubce.http.HttpMethodName;

/**
 *
 * the dugo bce client and build the request
 * Created by liuzhenxing01 on 2018/10/18.
 */
public class BceIotClient {

    private String uri;

    private String ak;

    private String sk;
    private HttpMethodName methodName;

    public BceIotClient(String uri, String ak, String sk, HttpMethodName methodName) {
        if (uri == null || ak == null || sk == null || methodName == null) {
            throw new IllegalArgumentException("uri, ak, sk, methodName should not be null");
        }
        this.uri = uri;
        this.methodName = methodName;
        this.ak = ak;
        this.sk = sk;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpMethodName getMethodName() {
        return methodName;
    }

    public void setMethodName(HttpMethodName methodName) {
        this.methodName = methodName;
    }
}
