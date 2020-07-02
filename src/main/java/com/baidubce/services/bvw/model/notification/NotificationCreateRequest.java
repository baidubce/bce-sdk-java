/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.notification;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Create notification request.
 */
public class NotificationCreateRequest extends NotificationBaseRequest {

    /**
     * The notification endpoint.
     */
    private String endpoint;

    /**
     * The authentication token.
     */
    private String token;

    /**
     * The auth type.
     */
    private AuthType authType = AuthType.NONE;

    /**
     * Construct a creating notification request with specified parameters.
     * @param name      The notification name
     * @param endpoint  The notification endpoint
     * @return A creating notification request
     */
    public static NotificationCreateRequest of(String name, String endpoint) {
        return of(name, endpoint, null, AuthType.NONE);
    }

    public static NotificationCreateRequest of(String name, String endpoint, String token, AuthType authType) {
        NotificationCreateRequest createRequest = new NotificationCreateRequest();
        createRequest.setName(name);
        createRequest.setEndpoint(endpoint);
        createRequest.setToken(token);
        createRequest.setAuthType(authType);
        return createRequest;
    }

    @Override
    public NotificationCreateRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

}
