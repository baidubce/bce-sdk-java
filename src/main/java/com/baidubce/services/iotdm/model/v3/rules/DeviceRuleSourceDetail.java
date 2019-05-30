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
package com.baidubce.services.iotdm.model.v3.rules;

public class DeviceRuleSourceDetail extends DeviceRuleSource {
    /**
     * Schema name to be display .
     */
    private String displayName;
    /**
     * Unit of Schema .
     */
    private String unit;
    /**
     * Default value of Schema .
     */
    private String defaultValue;
    /**
     * Last saved time of Device Rule source .
     */
    private Long lastSaveTime;


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }


    public Long getLastSaveTime() {
        return lastSaveTime;
    }

    public void setLastSaveTime(Long lastSaveTime) {
        this.lastSaveTime = lastSaveTime;
    }
}
