/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

import java.util.Map;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class InsertCuePointInnerRequest extends AbstractBceRequest {
    private String callback = null;

    private Map<String, String> arguments = null;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public InsertCuePointInnerRequest withCallback(String callback) {
        this.callback = callback;
        return this;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public InsertCuePointInnerRequest withArguments(Map<String, String> arguments) {
        this.arguments = arguments;
        return this;
    }

    public InsertCuePointInnerRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class InsertCuePointInnerRequest {\n");
        sb.append("    callback: ").append(callback).append("\n");
        sb.append("    arguments: ").append(arguments).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
