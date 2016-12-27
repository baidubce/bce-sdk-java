/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.util.DateUtils;
import com.google.common.collect.Maps;

/**
 * Represents an HTTP response returned by a BCE service in response to a service request.
 */
public class BceHttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(BceHttpResponse.class);

    private CloseableHttpResponse httpResponse;

    private InputStream content;

    public BceHttpResponse(CloseableHttpResponse httpResponse) throws IOException {
        this.httpResponse = httpResponse;
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && entity.isStreaming()) {
            this.content = entity.getContent();
        }
    }

    public String getHeader(String name) {
        Header header = this.httpResponse.getFirstHeader(name);
        if (header == null) {
            return null;
        }
        return header.getValue();
    }

    public long getHeaderAsLong(String name) {
        String value = this.getHeader(name);
        if (value == null) {
            return -1;
        }
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            logger.warn("Invalid " + name + ":" + value, e);
            return -1;
        }
    }

    public Date getHeaderAsRfc822Date(String name) {
        String value = this.getHeader(name);
        if (value == null) {
            return null;
        }
        try {
            return DateUtils.parseRfc822Date(value);
        } catch (Exception e) {
            logger.warn("Invalid " + name + ":" + value, e);
            return null;
        }
    }

    public InputStream getContent() {
        return this.content;
    }

    public String getStatusText() {
        return this.httpResponse.getStatusLine().getReasonPhrase();
    }

    public int getStatusCode() {
        return this.httpResponse.getStatusLine().getStatusCode();
    }

    public CloseableHttpResponse getHttpResponse() {
        return this.httpResponse;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        for (Header header : this.httpResponse.getAllHeaders()) {
            headers.put(header.getName(), header.getValue());
        }
        return headers;
    }

}
