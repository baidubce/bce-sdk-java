/*
 * Copyright 2017 Baidu, Inc.
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
package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represents request for updating a stream's watermark
 */
public class UpdateStreamWatermarkRequest extends AbstractBceRequest {

    private String domain;

    private String app;

    private String stream;

    private Watermarks watermarks;

    /**
     * Returns domain
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets domain
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Sets domain
     * @return returns this object for method chaining
     */
    public UpdateStreamWatermarkRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Returns app
     * @return app
     */
    public String getApp() {
        return app;
    }

    /**
     * Sets app
     * @param app
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * Sets app
     * @return returns this object for method chaining
     */
    public UpdateStreamWatermarkRequest withApp(String app) {
        this.app = app;
        return this;
    }

    /**
     * Returns stream
     * @return stream
     */
    public String getStream() {
        return stream;
    }

    /**
     * Sets stream
     * @param stream
     */
    public void setStream(String stream) {
        this.stream = stream;
    }

    /**
     * Sets stream
     * @return returns this object for method chaining
     */
    public UpdateStreamWatermarkRequest withStream(String stream) {
        this.stream = stream;
        return this;
    }

    /**
     * Returns watermarks
     * @return watermarks
     */
    public Watermarks getWatermarks() {
        return watermarks;
    }

    /**
     * Sets watermarks
     * @param watermarks
     */
    public void setWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
    }

    /**
     * Sets watermarks
     * @return returns this object for method chaining
     */
    public UpdateStreamWatermarkRequest withWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
        return this;
    }

    /**
     * Sets credentials
     * @return returns this object for method chaining
     */
    @Override
    public UpdateStreamWatermarkRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return "UpdateStreamWatermarkRequest{"
                + "domain='" + domain + '\''
                + ", app='" + app + '\''
                + ", stream='" + stream + '\''
                + ", watermarks=" + watermarks
                + '}';
    }
}
