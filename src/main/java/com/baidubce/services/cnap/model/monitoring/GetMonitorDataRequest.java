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
package com.baidubce.services.cnap.model.monitoring;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for get monitor data.
 */
public class GetMonitorDataRequest extends AbstractBceRequest {

    /**
     * The PromQL query.
     */
    private String query;

    /**
     * The start timestamp (second).
     */
    private long start = -1;

    /**
     * The end timestamp (second).
     */
    private long end = -1;

    /**
     * The query point time step.
     */
    private int step = -1;

    /**
     * The timeout info.
     */
    private int timeout = -1;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public GetMonitorDataRequest withQuery(String query) {
        this.setQuery(query);
        return this;
    }

    public GetMonitorDataRequest withStart(long start) {
        this.setStart(start);
        return this;
    }

    public GetMonitorDataRequest withEnd(long end) {
        this.setEnd(end);
        return this;
    }

    public GetMonitorDataRequest withStep(int step) {
        this.setStep(step);
        return this;
    }

    public GetMonitorDataRequest withTimeout(int timeout) {
        this.setTimeout(timeout);
        return this;
    }


    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public GetMonitorDataRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
