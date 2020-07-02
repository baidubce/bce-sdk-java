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

/**
 * Update notification request.
 */
public class NotificationUpdateRequest extends NotificationBaseRequest {

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
    private AuthType authType;

    /**
     * Construct a update request with specified parameters.
     *
     * @param name      The notification name
     * @param endpoint  The notification endpoint
     * @return A updating notification request
     */
    public static NotificationUpdateRequest of(String name, String endpoint) {
        return of(name, endpoint, null, null);
    }

    public static NotificationUpdateRequest of(String name, String endpoint, String token, AuthType authType) {
        NotificationUpdateRequest updateRequest = new NotificationUpdateRequest();
        updateRequest.setName(name);
        updateRequest.setEndpoint(endpoint);
        updateRequest.setToken(token);
        updateRequest.setAuthType(authType);
        return updateRequest;
    }

    @Override
    public NotificationUpdateRequest withRequestCredentials(BceCredentials credentials) {
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
