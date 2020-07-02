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
 * Base notification request.
 */
public class NotificationBaseRequest extends AbstractBceRequest {

    /**
     * The notification name.
     */
    private String name;

    /**
     * Construct a base notification request with specified parameters.
     *
     * @param name The notification name
     * @return A base notification request
     */
    public static NotificationBaseRequest of(String name) {
        NotificationBaseRequest baseRequest = new NotificationBaseRequest();
        baseRequest.setName(name);
        return baseRequest;
    }

    @Override
    public NotificationBaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
