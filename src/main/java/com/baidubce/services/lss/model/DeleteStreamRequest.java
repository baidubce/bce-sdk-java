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
 * Represent request for deleting a stream
 */
public class DeleteStreamRequest extends AbstractBceRequest {

    private String domain;

    private String app;

    private String stream;

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
    public DeleteStreamRequest withDomain(String domain) {
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
    public DeleteStreamRequest withApp(String app) {
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
    public DeleteStreamRequest withStream(String stream) {
        this.stream = stream;
        return this;
    }

    /**
     * Sets credentials
     * @return returns this object for method chaining
     */
    @Override
    public DeleteStreamRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return "DeleteStreamRequest{"
                + "domain='" + domain + '\''
                + ", app='" + app + '\''
                + ", stream='" + stream + '\''
                + '}';
    }
}
