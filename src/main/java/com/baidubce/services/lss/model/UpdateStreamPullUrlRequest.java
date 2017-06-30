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
 * Represents request for updating a stream's pull url
 */
public class UpdateStreamPullUrlRequest extends AbstractBceRequest {

    private String domain;

    private String app;

    private String stream;

    private String pullUrl;

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
    public UpdateStreamPullUrlRequest withDomain(String domain) {
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
    public UpdateStreamPullUrlRequest withApp(String app) {
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
    public UpdateStreamPullUrlRequest withStream(String stream) {
        this.stream = stream;
        return this;
    }

    /**
     * Returns pullUrl
     * @return pullUrl
     */
    public String getPullUrl() {
        return pullUrl;
    }

    /**
     * Sets pullUrl
     * @param pullUrl
     */
    public void setPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
    }

    /**
     * Sets pullUrl
     * @return returns this object for method chaining
     */
    public UpdateStreamPullUrlRequest withPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
        return this;
    }

    /**
     * Sets credentials
     * @return returns this object for method chaining
     */
    @Override
    public UpdateStreamPullUrlRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return "UpdateStreamPullUrlRequest{"
                + "domain='" + domain + '\''
                + ", app='" + app + '\''
                + ", stream='" + stream + '\''
                + ", pullUrl='" + pullUrl + '\''
                + '}';
    }
}
