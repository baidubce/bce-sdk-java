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
package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Map;

/**
 * Represent the response of GetStepRequest.
 *
 * The response contains the properties of the target step, such as:
 * id, actionOnFailure, name, type, properties and status.
 */
public class GetStepResponse extends AbstractBceResponse {
    private String id;
    private String actionOnFailure;
    private String name;
    private String type;
    private Map<String, String> properties;
    private StepStatus status;
    private String logUri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StepStatus getStatus() {
        return status;
    }

    public void setStatus(StepStatus status) {
        this.status = status;
    }

    public String getActionOnFailure() {
        return actionOnFailure;
    }

    public void setActionOnFailure(String actionOnFailure) {
        this.actionOnFailure = actionOnFailure;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getLogUri() {
        return this.logUri;
    }

    public void setLogUri(String logUri) {
        this.logUri = logUri;
    }
}
