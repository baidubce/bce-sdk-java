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
package com.baidubce.services.sms;

import java.net.InetAddress;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.Region;
import com.baidubce.auth.BceCredentials;
import com.baidubce.http.RetryPolicy;

/**
 * All client configurations for SMS clients.
 * <p>
 * Default configurations inherit super class {@link com.baidubce.BceClientConfiguration}.
 * <p>
 */
public class SmsClientConfiguration extends BceClientConfiguration {
    @Override
    public SmsClientConfiguration withCredentials(BceCredentials credentials) {
        this.setCredentials(credentials);
        return this;
    }

    @Override
    public SmsClientConfiguration withEndpoint(String endpoint) {
        this.setEndpoint(endpoint);
        return this;
    }

    @Override
    public SmsClientConfiguration withProtocol(Protocol protocol) {
        this.setProtocol(protocol);
        return this;
    }

    @Override
    public SmsClientConfiguration withMaxConnections(int maxConnections) {
        this.setMaxConnections(maxConnections);
        return this;
    }

    @Override
    public SmsClientConfiguration withUserAgent(String userAgent) {
        this.setUserAgent(userAgent);
        return this;
    }

    @Override
    public SmsClientConfiguration withLocalAddress(InetAddress localAddress) {
        this.setLocalAddress(localAddress);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyHost(String proxyHost) {
        this.setProxyHost(proxyHost);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyPort(int proxyPort) {
        this.setProxyPort(proxyPort);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyUsername(String proxyUsername) {
        this.setProxyUsername(proxyUsername);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyPassword(String proxyPassword) {
        this.setProxyPassword(proxyPassword);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyDomain(String proxyDomain) {
        this.setProxyDomain(proxyDomain);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyWorkstation(String proxyWorkstation) {
        this.setProxyWorkstation(proxyWorkstation);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyPreemptiveAuthenticationEnabled(
            boolean proxyPreemptiveAuthenticationEnabled) {
        this.setProxyPreemptiveAuthenticationEnabled(proxyPreemptiveAuthenticationEnabled);
        return this;
    }

    @Override
    public SmsClientConfiguration withRetryPolicy(RetryPolicy retryPolicy) {
        this.setRetryPolicy(retryPolicy);
        return this;
    }

    @Override
    public SmsClientConfiguration withSocketTimeoutInMillis(int socketTimeoutInMillis) {
        this.setSocketTimeoutInMillis(socketTimeoutInMillis);
        return this;
    }

    @Override
    public SmsClientConfiguration withConnectionTimeoutInMillis(int connectionTimeoutInMillis) {
        this.setConnectionTimeoutInMillis(connectionTimeoutInMillis);
        return this;
    }

    @Override
    public SmsClientConfiguration withSocketBufferSizeInBytes(int socketBufferSizeInBytes) {
        this.setSocketBufferSizeInBytes(socketBufferSizeInBytes);
        return this;
    }

    @Override
    public SmsClientConfiguration withRegion(Region region) {
        this.setRegion(region);
        return this;
    }
}
