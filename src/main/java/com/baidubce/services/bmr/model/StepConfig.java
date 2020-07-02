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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represent configuration for a BMR step.
 * The derived class of StepConfig are used for configuration.
 */
public class StepConfig {
    private String actionOnFailure;
    private String type;
    private Map<String, String> properties;
    private String name;
    private List<AdditionalFile> additionalFiles;

    public StepConfig withActionOnFailure(String actionOnFailure) {
        this.setActionOnFailure(actionOnFailure);
        return this;
    }

    public StepConfig withType(String type) {
        this.setType(type);
        return this;
    }

    public StepConfig withProperties(Map<String, String> properties) {
        this.setProperties(properties);
        return this;
    }

    public StepConfig withName(String name) {
        this.setName(name);
        return this;
    }

    public StepConfig withAdditionalFiles(List<AdditionalFile> additionalFiles) {
        this.setAdditionalFiles(additionalFiles);
        return this;
    }

    public String getActionOnFailure() {
        return actionOnFailure;
    }

    public void setActionOnFailure(String actionOnFailure) {
        this.actionOnFailure = actionOnFailure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public void addProperty(String key, String value) {
        if (this.properties == null) {
            this.properties = new HashMap<String, String>();
        }
        this.properties.put(key, value);
    }

    public List<AdditionalFile> getAdditionalFiles() {
        return additionalFiles;
    }

    public void setAdditionalFiles(List<AdditionalFile> additionalFiles) {
        this.additionalFiles = additionalFiles;
    }

    public void addAdditionalFile(String remote, String local) {
        if (this.additionalFiles == null) {
            this.additionalFiles = new LinkedList<AdditionalFile>();
        }
        this.additionalFiles.add(new AdditionalFile().withRemote(remote).withLocal(local));
    }
}
