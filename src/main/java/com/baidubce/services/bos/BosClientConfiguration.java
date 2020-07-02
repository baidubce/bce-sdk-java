/*
 * Copyright 2014-2019 Baidu, Inc.
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
package com.baidubce.services.bos;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.Region;
import com.baidubce.auth.BceCredentials;
import com.baidubce.http.RetryPolicy;

import java.net.InetAddress;

import static com.google.common.base.Preconditions.checkArgument;

public class BosClientConfiguration extends BceClientConfiguration {
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 5 * 1024 * 1024;
    private int streamBufferSize = DEFAULT_STREAM_BUFFER_SIZE;

    /**
     * whether to enable using cname to visit bos resource. If user use custom domain as the endpoint, the cnameEnabled
     * should be true.
     */
    private boolean cnameEnabled;

    /**
     * whether to enable using nio to http async put, default value is true for async put
     */
    private boolean enableHttpAsyncPut = true;

    public int getStreamBufferSize() {
        return this.streamBufferSize;
    }

    public BosClientConfiguration() {
        super();
    }
    
    public BosClientConfiguration(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, null);
    }
    
    public BosClientConfiguration(BceClientConfiguration clientConfiguration, String bosEndpoint) {
        super(clientConfiguration, bosEndpoint);
    }
    
    public void setStreamBufferSize(int streamBufferSize) {
        checkArgument(streamBufferSize > 0, "streamBufferSize should be positive.");
        this.streamBufferSize = streamBufferSize;
    }

    public BosClientConfiguration withStreamBufferSize(int streamBufferSize) {
        this.setStreamBufferSize(streamBufferSize);
        return this;
    }


    /**
     * Returns whether to enable using cname to visit bos resource.
     *
     * @return whether to enable using cname to visit bos resource.
     */
    public boolean isCnameEnabled() {
        return this.cnameEnabled;
    }

    /**
     * Sets whether to enable using cname to visit bos resource.
     *
     * @param cnameEnabled whether to enable using cname to visit bos resource.
     */
    public void setCnameEnabled(boolean cnameEnabled) {
        this.cnameEnabled = cnameEnabled;
    }

    public BosClientConfiguration withCnameEnabled(boolean cnameEnabled) {
        this.setCnameEnabled(cnameEnabled);
        return this;
    }

    /**
     * Returns whether to enable http async put
     * @return whether to enable http async put
     */
    public boolean isEnableHttpAsyncPut() {
        return enableHttpAsyncPut;
    }

    /**
     * Set whether to enable http async put
     * @param enableHttpAsyncPut whether to enable http async put
     */
    public void setEnableHttpAsyncPut(boolean enableHttpAsyncPut) {
        this.enableHttpAsyncPut = enableHttpAsyncPut;
    }

    public BosClientConfiguration withEnableHttpAsyncPut(boolean enableHttpAsyncPut) {
        this.setEnableHttpAsyncPut(enableHttpAsyncPut);
        return this;
    }

    @Override
    public BosClientConfiguration withProtocol(Protocol protocol) {
        this.setProtocol(protocol);
        return this;
    }

    @Override
    public BosClientConfiguration withMaxConnections(int maxConnections) {
        this.setMaxConnections(maxConnections);
        return this;
    }

    @Override
    public BosClientConfiguration withUserAgent(String userAgent) {
        this.setUserAgent(userAgent);
        return this;
    }

    @Override
    public BosClientConfiguration withLocalAddress(InetAddress localAddress) {
        this.setLocalAddress(localAddress);
        return this;
    }

    @Override
    public BosClientConfiguration withProxyHost(String proxyHost) {
        this.setProxyHost(proxyHost);
        return this;
    }

    @Override
    public BosClientConfiguration withProxyPort(int proxyPort) {
        this.setProxyPort(proxyPort);
        return this;
    }

    @Override
    public BosClientConfiguration withProxyUsername(String proxyUsername) {
        this.setProxyUsername(proxyUsername);
        return this;
    }

    @Override
    public BosClientConfiguration withProxyPassword(String proxyPassword) {
        this.setProxyPassword(proxyPassword);
        return this;
    }

    @Override
    public BosClientConfiguration withProxyDomain(String proxyDomain) {
        this.setProxyDomain(proxyDomain);
        return this;
    }

    @Override
    public BosClientConfiguration withProxyWorkstation(String proxyWorkstation) {
        this.setProxyWorkstation(proxyWorkstation);
        return this;
    }

    @Override
    public BosClientConfiguration withRetryPolicy(RetryPolicy retryPolicy) {
        this.setRetryPolicy(retryPolicy);
        return this;
    }

    @Override
    public BosClientConfiguration withSocketTimeoutInMillis(int socketTimeoutInMillis) {
        this.setSocketTimeoutInMillis(socketTimeoutInMillis);
        return this;
    }

    @Override
    public BosClientConfiguration withConnectionTimeoutInMillis(int connectionTimeoutInMillis) {
        this.setConnectionTimeoutInMillis(connectionTimeoutInMillis);
        return this;
    }

    @Override
    public BosClientConfiguration withSocketBufferSizeInBytes(int socketBufferSizeInBytes) {
        this.setSocketBufferSizeInBytes(socketBufferSizeInBytes);
        return this;
    }

    @Override
    public BceClientConfiguration withProxyPreemptiveAuthenticationEnabled(
            boolean proxyPreemptiveAuthenticationEnabled) {
        this.setProxyPreemptiveAuthenticationEnabled(proxyPreemptiveAuthenticationEnabled);
        return this;
    }

    @Override
    public BosClientConfiguration withEndpoint(String endpoint) {
        this.setEndpoint(endpoint);
        return this;
    }

    @Override
    public BosClientConfiguration withRegion(Region region) {
        this.setRegion(region);
        return this;
    }

    @Override
    public BosClientConfiguration withCredentials(BceCredentials credentials) {
        this.setCredentials(credentials);
        return this;
    }

}
