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
package com.baidubce;

import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.BceCredentialsProvider;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.auth.StaticCredentialsProvider;

/**
 * Abstract builder for BCE clients.
 *
 * @param <T> The builder type.
 * @param <C> The client type.
 */
public abstract class BceClientBuilder<T extends BceClientBuilder<T, C>, C> {

    protected BceClientConfiguration config = new BceClientConfiguration();

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public T withCredentials(String accessKeyId, String secretAccessKey) {
        return withCredentials(new DefaultBceCredentials(accessKeyId, secretAccessKey));
    }

    public T withCredentials(BceCredentials credentials) {
        return withCredentialsProvider(new StaticCredentialsProvider(credentials));
    }

    public T withCredentialsProvider(BceCredentialsProvider credentialsProvider) {
        this.config.setCredentialsProvider(credentialsProvider);
        return self();
    }

    public T withEndpoint(String endpoint) {
        this.config.setEndpoint(endpoint);
        return self();
    }

    public T withRegion(Region region) {
        this.config.setRegion(region);
        return self();
    }

    public T withProtocol(Protocol protocol) {
        this.config.setProtocol(protocol);
        return self();
    }

    /**
     * Builds the client with the current configuration.
     *
     * @return The built client.
     */
    public abstract C build();
}
