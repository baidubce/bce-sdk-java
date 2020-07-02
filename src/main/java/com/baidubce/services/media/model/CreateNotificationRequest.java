/*
 * Copyright 2020 Baidu, Inc.
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
 
package com.baidubce.services.media.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import static com.baidubce.services.media.MediaClient.ENDPOINT_MESSAGE_KEY;
import static com.baidubce.services.media.MediaClient.NAME_MESSAGE_KEY;
import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * The request for creating notification
 */
@Data
public class CreateNotificationRequest extends AbstractBceRequest {

    private String name = null;
    private String endpoint  = null;


    public CreateNotificationRequest withName(String name) {
        checkStringNotEmpty(name, checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        this.name = name;
        return this;
    }

    public CreateNotificationRequest withEndpoint(String endpoint) {
        checkStringNotEmpty(endpoint, checkEmptyExceptionMessageFormat(ENDPOINT_MESSAGE_KEY));
        this.endpoint = endpoint;
        return this;
    }

    public CreateNotificationRequest withRequestCredentials(
            BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateNotificationRequest {\n");

        sb.append("name: ").append(name).append("\n");
        sb.append("endpoint: ").append(endpoint).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
