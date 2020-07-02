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

import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * The request for getting notification
 */
@Data
public class GetNotificationRequest extends AbstractBceRequest {

    private String name = null;


    public GetNotificationRequest withName(String name) {
        checkStringNotEmpty(name, "The parameter name should NOT be null or empty string.");
        this.name = name;
        return this;
    }

    public GetNotificationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetNotificationRequest {\n");

        sb.append("name: ").append(name).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
