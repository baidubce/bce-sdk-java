/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm.model.v3.schema;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represent the model of Schema Property.
 */
public class SchemaProperty {

    private String name;

    private PropertyType type;

    private String displayName;

    private String defaultValue;

    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchemaProperty withName(String name) {
        setName(name);
        return this;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public SchemaProperty withType(PropertyType type) {
        setType(type);
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public SchemaProperty withDisplayName(String displayName) {
        setDisplayName(displayName);
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public SchemaProperty withDefaultValue(String defaultValue) {
        setDefaultValue(defaultValue);
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public SchemaProperty withUnit(String unit) {
        setUnit(unit);
        return this;
    }

    public enum PropertyType {
        STRING("string"),
        NUMBER("number"),
        BOOL("bool"),
        OBJECT("object"),
        ARRAY("array");

        private String dataType;

        private PropertyType(String dataType) {
            this.dataType = dataType;
        }

        @JsonValue
        public String getDataType() {
            return dataType;
        }

    }
}
